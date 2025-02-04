package org.turtle.minecraft_service.dto.community.read.list;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "PostListResponseDto")
public class PostListResponseDto {

    private String message;
    private PostListMetaData metaData;
    private List<PostElement> posts;

    public static PostListResponseDto fromDto(PostListDto dto) {
        return PostListResponseDto.builder()
                .message("성공적으로 게시물 페이지를 불러왔습니다!")
                .metaData(dto.getMetaData())
                .posts(dto.getPosts())
                .build();
    }
}
