package org.turtle.minecraft_service.dto.community.read.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "PostDetailResponseDto")
public class PostDetailResponseDto {

    @Schema(description = "응답 메시지", example = "게시물 상세정보를 성공적으로 불러왔습니다!")
    private String message;

    @Schema(description = "게시물 번호", example = "1")
    private Long postId;

    @Schema(description = "게시물 카테고리", example = "Free")
    private PostType postType;

    @Schema(description = "게시물 제목", example = "개쩌는거 발견함!")
    private String title;

    @Schema(description = "게시물 작성자", example = "_appli_")
    private String creator;

    @Schema(description = "게시물 내용", example = "아끼 마크하다가 이거 찾았는데 이거 뭐냐?")
    private String content;

    @Schema(description = "게시물 이미지 저장 경로", example = "http://localhost:8080/images/")
    private String postImageApiUrlPrefix;

    @Schema(description = "게시물 이미지 이름(리스트)", example = "[이미지1, 이미지2, 이미지3]]")
    private List<String> postImages;

    @Schema(description = "게시물 작성 시간")
    private LocalDateTime createdAt;

    @Schema(description = "게시물 수정 시간")
    private LocalDateTime updatedAt;

    public static PostDetailResponseDto fromDto(PostDetailDto dto){
        return PostDetailResponseDto.builder()
                .message("게시물 상세정보를 성공적으로 불러왔습니다!")
                .postId(dto.getPostId())
                .postType(dto.getPostType())
                .title(dto.getTitle())
                .creator(dto.getCreator())
                .content(dto.getContent())
                .postImageApiUrlPrefix(dto.getPostImageApiUrlPrefix())
                .postImages(dto.getPostImages())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }


}
