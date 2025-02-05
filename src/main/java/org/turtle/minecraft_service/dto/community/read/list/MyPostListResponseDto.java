package org.turtle.minecraft_service.dto.community.read.list;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "MyPostListResponseDto")
public class MyPostListResponseDto {

    @Schema(description = "찾은 게시물 수", example = "5")
    private int postCount;
    private List<MyPostElement> myPosts;

    public static MyPostListResponseDto fromDto(MyPostListDto dto){
        return MyPostListResponseDto.builder()
                .postCount(dto.getFoundPosts())
                .myPosts(dto.getMyPosts())
                .build();
    }
}
