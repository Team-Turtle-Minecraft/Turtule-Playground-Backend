package org.turtle.minecraft_service.controller.community;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.community.create.PostSaveDto;
import org.turtle.minecraft_service.dto.community.create.PostSaveRequestDto;
import org.turtle.minecraft_service.dto.community.create.PostSaveResponseDto;
import org.turtle.minecraft_service.dto.community.delete.PostDeleteResponseDto;
import org.turtle.minecraft_service.dto.community.interaction.*;
import org.turtle.minecraft_service.dto.community.read.detail.PostDetailDto;
import org.turtle.minecraft_service.dto.community.read.detail.PostDetailResponseDto;
import org.turtle.minecraft_service.dto.community.read.list.MyPostListDto;
import org.turtle.minecraft_service.dto.community.read.list.MyPostListResponseDto;
import org.turtle.minecraft_service.dto.community.read.list.PostListDto;
import org.turtle.minecraft_service.dto.community.read.list.PostListResponseDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateRequestDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateResponseDto;
import org.turtle.minecraft_service.service.community.PostService;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExample;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExamples;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "커뮤니티")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 목록 조회")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostListResponseDto.class)))
    @GetMapping()
    public ResponseEntity<PostListResponseDto> getPostList(@AuthenticationPrincipal User user,
                                                           @RequestParam(required = false) String sortType,
                                                           @RequestParam(required = false) String postType,
                                                           @RequestParam(required = false, defaultValue = "1") int page
    ){
        PostListDto dto = postService.getPostList(postType, sortType, page);

        return new ResponseEntity<>(PostListResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "내가 작성한 게시물 목록 조회")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MyPostListResponseDto.class)))
    @GetMapping("/my")
    public ResponseEntity<MyPostListResponseDto> getMyPostList(@AuthenticationPrincipal User user){

        MyPostListDto dto = postService.getMyPostList(user);

        return new ResponseEntity<>(MyPostListResponseDto.fromDto(dto), HttpStatus.OK);
    }


    @Operation(summary = "게시물 검색")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostListResponseDto.class)))
    @GetMapping("/search")
    public ResponseEntity<PostListResponseDto> getSearchedPosts(@AuthenticationPrincipal User user,
                                                                  @RequestParam @Size(min = 2,  message = "검색어는 최소 2글자 이상이어야 합니다") String keyword,
                                                                  @RequestParam(required = false, defaultValue = "1") int page

    ) {
        PostListDto dto = postService.getSearchedPosts(keyword, page);

        return new ResponseEntity<>(PostListResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "게시물 상세 조회")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.PostNotFoundError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostDetailResponseDto.class)))
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponseDto> getPostDetail(@AuthenticationPrincipal User user,
                                                               @PathVariable Long id
    ){
        PostDetailDto dto = postService.getPostDetail(id);

        return new ResponseEntity<>(PostDetailResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "게시물 작성")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.NoImageFileError),
            @ApiErrorCodeExample(value = HttpErrorCode.InvalidImageFileExtension),
            @ApiErrorCodeExample(value = HttpErrorCode.InternalServerError)
    })
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = PostSaveResponseDto.class)))
    @PostMapping("/save")
    public ResponseEntity<PostSaveResponseDto> savePost(@AuthenticationPrincipal User user,
                                                        @Valid @RequestPart(name = "post") PostSaveRequestDto request,
                                                        @RequestPart(name = "imageFile") List<MultipartFile> imageFiles
    ) {

        PostSaveDto dto = postService.savePost(user, request, imageFiles);

        return new ResponseEntity<>(PostSaveResponseDto.fromDto(dto), HttpStatus.CREATED);
    }


    @Operation(summary = "게시물 수정")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.PostNotFoundError),
            @ApiErrorCodeExample(value = HttpErrorCode.NoImageFileError),
            @ApiErrorCodeExample(value = HttpErrorCode.InvalidImageFileExtension),
            @ApiErrorCodeExample(value = HttpErrorCode.InternalServerError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostUpdateResponseDto.class)))
    @PatchMapping("/{id}")
    public ResponseEntity<PostUpdateResponseDto> updatePost(@AuthenticationPrincipal User user,
                                                            @PathVariable Long id,
                                                            @Valid @RequestPart(name = "post") PostUpdateRequestDto request,
                                                            @RequestPart(name = "imageFile") List<MultipartFile> imageFiles
    ){
        PostUpdateDto dto = postService.updatePost(user, id, request, imageFiles);

        return new ResponseEntity<>(PostUpdateResponseDto.fromDto(dto), HttpStatus.OK);

    }

    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.PostNotFoundError)
    })
    @Operation(summary = "게시물 삭제")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostDeleteResponseDto.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDeleteResponseDto> deletePost(@AuthenticationPrincipal User user,
                                                            @PathVariable Long id
    ){
        postService.deletePost(user, id);

        return new ResponseEntity<>(PostDeleteResponseDto.createNewResponse(), HttpStatus.OK);
    }

    @Operation(summary = "게시물 좋아요")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.PostNotFoundError)
    })
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = PostLikeResponseDto.class)))
    @PostMapping("/{id}/like")
    public ResponseEntity<PostLikeResponseDto> likeThePost(@AuthenticationPrincipal User user, @PathVariable Long id) {
        String resultMessage = postService.likeThePost(user, id);

        return new ResponseEntity<>(PostLikeResponseDto.createNewResponse(resultMessage), HttpStatus.CREATED);
    }
    @Operation(summary = "게시물 좋아요 상태 확인")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostLikeStatusResponseDto.class)))
    @GetMapping("/{id}/like")
    public ResponseEntity<PostLikeStatusResponseDto> getPostLikeStatus(@AuthenticationPrincipal User user, @PathVariable Long id) {

        PostLikeStatusDto dto = postService.getPostLikeStatus(user, id);

        return new ResponseEntity<>(PostLikeStatusResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "게시물 조회수 증가")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.PostNotFoundError),
            @ApiErrorCodeExample(value = HttpErrorCode.ViewIsAlreadyIncreased)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostViewResponseDto.class)))
    @PostMapping("/{id}/views")
    public ResponseEntity<PostViewResponseDto> increasePostView(@AuthenticationPrincipal User user,
                                                                @PathVariable Long id
    ){
        PostViewDto dto = postService.increasePostView(user, id);

        return new ResponseEntity<>(PostViewResponseDto.fromDto(dto), HttpStatus.OK);
    }

}
