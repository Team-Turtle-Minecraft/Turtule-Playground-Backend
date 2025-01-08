package org.turtle.minecraft_service.service.community;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.InternalErrorType;
import org.turtle.minecraft_service.domain.primary.community.Post;
import org.turtle.minecraft_service.domain.primary.community.PostImage;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.community.create.PostSaveDto;
import org.turtle.minecraft_service.dto.community.create.PostSaveRequestDto;
import org.turtle.minecraft_service.dto.community.read.detail.PostDetailDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateRequestDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.exception.InternalErrorException;
import org.turtle.minecraft_service.repository.primary.PostImageRepository;
import org.turtle.minecraft_service.repository.primary.PostRepository;
import org.turtle.minecraft_service.service.file.FileService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final FileService fileService;

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    @Value("${file.upload.image.api}")
    private String postImageApiUrlPrefix;


    public PostDetailDto getPostDetail(Long postId){

        Post foundPost = postRepository.findByIdWithImages(postId)
                .orElseThrow(() -> new HttpErrorException(HttpErrorCode.PostNotFoundError));

        List<PostImage> foundPostPostImages = foundPost.getPostImages();

        return PostDetailDto.of(postImageApiUrlPrefix ,foundPost, foundPostPostImages);

    }


    public PostSaveDto savePost(User user, PostSaveRequestDto request, List<MultipartFile> imageFiles){

        List<String> savedImagesInProgress = new ArrayList<>();

        Post newPost = postRepository.save(Post.of(user, request));

        savePostImages(imageFiles, newPost, savedImagesInProgress);

        return PostSaveDto.fromEntity(newPost);

    }

    public PostUpdateDto updatePost(User user, Long postId, PostUpdateRequestDto request, List<MultipartFile> imageFiles){
        List<String> savedImagesInProgress = new ArrayList<>();
        List<String> imageFilesToDelete = new ArrayList<>();

        Post validatedPost = validatePostAndUser(user, postId);

        deleteExistPostImages(postId, imageFilesToDelete);

        savePostImages(imageFiles, validatedPost, savedImagesInProgress);

        validatedPost.update(request);

        return PostUpdateDto.fromEntity(validatedPost);

    }

    public void deletePost(User user, Long postId){

        List<String> imageFilesToDelete = new ArrayList<>();

        Post validatedPost = validatePostAndUser(user, postId);

        deleteExistPostImages(postId, imageFilesToDelete);

        postRepository.delete(validatedPost);

    }


    private void deleteExistPostImages(Long postId, List<String> imageFilesToDelete) {
        List<PostImage> foundPostImages = postImageRepository.findPostImageByPostId(postId);
        for (PostImage postImage : foundPostImages) {
            imageFilesToDelete.add(postImage.getImageName());
            postImageRepository.delete(postImage);
        }

        fileService.deleteImageFiles(imageFilesToDelete);
    }


    private Post validatePostAndUser(User user, Long postId) {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new HttpErrorException(HttpErrorCode.PostNotFoundError));

        if(!user.getNickname().equals(foundPost.getUser().getNickname())){
            throw new HttpErrorException(HttpErrorCode.AccessDeniedError);
        }

        return foundPost;
    }


    private void savePostImages(List<MultipartFile> imageFiles, Post newPost, List<String> savedImagesInProgress) {
        for (MultipartFile imageFile : imageFiles) {

            try {
                String uniqueFileName = fileService.saveImageFile(imageFile);
                PostImage savedPostImage = postImageRepository.save(PostImage.of(uniqueFileName, newPost));
                savedImagesInProgress.add(savedPostImage.getImageName());

            } catch (InternalErrorException e) {
                if(e.getInternalErrorType() == InternalErrorType.EmptyFileError) {
                    throw new HttpErrorException(HttpErrorCode.NoImageFileError);
                }

                if(e.getInternalErrorType() == InternalErrorType.InvalidFileExtension) {
                    throw new HttpErrorException(HttpErrorCode.InvalidImageFileExtension);
                }

                if(e.getInternalErrorType() == InternalErrorType.FileSaveError) {
                    fileService.deleteImageFiles(savedImagesInProgress);
                    throw new HttpErrorException(HttpErrorCode.InternalServerError);
                }
            }
        }
    }
}
