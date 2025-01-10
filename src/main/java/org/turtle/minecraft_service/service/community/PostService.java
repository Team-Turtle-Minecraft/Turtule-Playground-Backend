package org.turtle.minecraft_service.service.community;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.InternalErrorType;
import org.turtle.minecraft_service.constant.PostType;
import org.turtle.minecraft_service.domain.primary.community.Post;
import org.turtle.minecraft_service.domain.primary.community.PostImage;
import org.turtle.minecraft_service.domain.primary.community.PostLike;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.community.create.PostSaveDto;
import org.turtle.minecraft_service.dto.community.create.PostSaveRequestDto;
import org.turtle.minecraft_service.dto.community.interaction.PostViewDto;
import org.turtle.minecraft_service.dto.community.read.detail.PostDetailDto;
import org.turtle.minecraft_service.dto.community.read.list.PostListDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateRequestDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.exception.InternalErrorException;
import org.turtle.minecraft_service.repository.primary.PostImageRepository;
import org.turtle.minecraft_service.repository.primary.PostLikeRepository;
import org.turtle.minecraft_service.repository.primary.PostRepository;
import org.turtle.minecraft_service.service.file.FileService;
import org.turtle.minecraft_service.service.redis.RedisService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final RedisService redisService;
    private final FileService fileService;

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final PostLikeRepository postLikeRepository;

    @Value("${file.upload.image.api}")
    private String postImageApiUrlPrefix;

    private final int PAGE_SIZE = 10;

    public PostListDto getPostList(String postType, String sortType, int page){

        PostType type = (postType != null) ? PostType.valueOf(postType) : null;

        if("likes".equalsIgnoreCase(sortType)){
            Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
            Page<Post> postPage = postRepository.findAllOrderByLikesCount(type, pageable);
            return PostListDto.of(postImageApiUrlPrefix, postPage);
        }

        Sort sort = determineSortType(sortType);

        Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, sort);

        Page<Post> postPage = type == null ?
                postRepository.findAll(pageable) :
                postRepository.findAllByPostType(type, pageable);

        return PostListDto.of(postImageApiUrlPrefix,postPage);

    }


    public PostListDto getSearchedPosts(String keyword, int page){

        Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Post> postPage = postRepository.findByKeyword(keyword, pageable);

        return PostListDto.of(postImageApiUrlPrefix, postPage);

    }


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


    public String likeThePost(User user, Long postId){

        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new HttpErrorException(HttpErrorCode.PostNotFoundError));

       PostLike existPostLike = postLikeRepository.findByPostIdAndUserId(postId, user.getId());

       String message;
        if(existPostLike == null) {
            postLikeRepository.save(PostLike.of(foundPost, user));
            message = "게시물에 좋아요를 누르셨습니다.";
        }else {
            postLikeRepository.delete(existPostLike);
            message = "게시물에 좋아요를 취소하셨습니다.";
        }

        return message;
    }


    public PostViewDto increasePostView(User user, Long postId){

        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new HttpErrorException(HttpErrorCode.PostNotFoundError));

        if(redisService.hasPostView(postId, user.getSnsId())){
            throw new HttpErrorException(HttpErrorCode.ViewIsAlreadyIncreased);
        }

        foundPost.updateViews();
        redisService.savePostView(postId, user.getSnsId());
        return PostViewDto.fromEntity(foundPost);
    }


    /**
    * 아래부터는 검증 및 내부 메서드 입니다.
    * validatePostAndUser : 게시물 수정 및 삭제 과정에서 작성자 본인확인과 게시물 존재여부 검증
    * savePostImages : 요청으로 받은 이미지 파일들을 일괄 저장
    * deleteExistPostImages : 게시문 수정 및 삭제시 기존에 저장된 이미지 파일 및 엔티티 삭제
    * determineSortType : 요청으로 받은 정렬 타입으로 게시물 목록 조회 시 정렬 방식을 결정
    * */
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

    private void deleteExistPostImages(Long postId, List<String> imageFilesToDelete) {
        List<PostImage> foundPostImages = postImageRepository.findPostImageByPostId(postId);
        for (PostImage postImage : foundPostImages) {
            imageFilesToDelete.add(postImage.getImageName());
            postImageRepository.delete(postImage);
        }

        fileService.deleteImageFiles(imageFilesToDelete);
    }

    private Sort determineSortType(String sortType){
        return switch (sortType != null ? sortType : "latest"){
            case "oldest" -> Sort.by(Sort.Direction.ASC, "createdAt");
            case "views" -> Sort.by(Sort.Direction.DESC, "views");
            default -> Sort.by(Sort.Direction.DESC, "createdAt");
        };
    }
}
