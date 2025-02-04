package org.turtle.minecraft_service.dto.ranking.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.dto.community.read.list.PostElement;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "PostRankingResponseDto")
public class PostRankingResponseDto {

    private String message;

    private String postImageApiUrlPrefix;

    private List<PostElement> posts;

    public static PostRankingResponseDto fromDto(PostRankingDto dto) {
        return PostRankingResponseDto.builder()
                .message("전체 카테고리에서 좋아요가 높은 상위 5위 게시물들을 불러왔습니다.")
                .postImageApiUrlPrefix(dto.getPostImageApiUrlPrefix())
                .posts(dto.getPosts())
                .build();
    }
}
