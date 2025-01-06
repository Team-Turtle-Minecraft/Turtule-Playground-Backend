package org.turtle.minecraft_service.dto.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostSaveDto {

    private String creator;

    private PostType postType;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    public static PostSaveDto fromEntity(Post newPost){
        return PostSaveDto.builder()
                .creator(newPost.getUser().getNickname())
                .postType(newPost.getPostType())
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .createdAt(newPost.getCreatedAt())
                .build();
    }

}
