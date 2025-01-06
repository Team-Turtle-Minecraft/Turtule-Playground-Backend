package org.turtle.minecraft_service.dto.community.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostUpdateDto {

    private Long postId;
    private String creator;
    private PostType postType;
    private String title;
    private String content;
    private LocalDateTime updatedAt;

    public static PostUpdateDto fromEntity(Post updatedPost){
        return PostUpdateDto.builder()
                .postId(updatedPost.getId())
                .creator(updatedPost.getUser().getNickname())
                .postType(updatedPost.getPostType())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .updatedAt(updatedPost.getUpdatedAt())
                .build();
    }
}
