package org.turtle.minecraft_service.controller.community;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.community.create.PostSaveDto;
import org.turtle.minecraft_service.dto.community.create.PostSaveRequestDto;
import org.turtle.minecraft_service.dto.community.create.PostSaveResponseDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateRequestDto;
import org.turtle.minecraft_service.dto.community.update.PostUpdateResponseDto;
import org.turtle.minecraft_service.service.community.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Tag(name = "커뮤니티")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 작성")
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
}
