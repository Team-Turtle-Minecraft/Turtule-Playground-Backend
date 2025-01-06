package org.turtle.minecraft_service.service.community;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.InternalErrorType;
import org.turtle.minecraft_service.domain.primary.community.Post;
import org.turtle.minecraft_service.domain.primary.community.PostImage;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.community.PostSaveDto;
import org.turtle.minecraft_service.dto.community.PostSaveRequestDto;
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

    public PostSaveDto savePost(User user, PostSaveRequestDto request, List<MultipartFile> imageFiles){

        List<String> savedImagesInProgress = new ArrayList<>();

        Post newPost = postRepository.save(Post.of(user, request));

        savePostImages(imageFiles, newPost, savedImagesInProgress);

        return PostSaveDto.fromEntity(newPost);

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
