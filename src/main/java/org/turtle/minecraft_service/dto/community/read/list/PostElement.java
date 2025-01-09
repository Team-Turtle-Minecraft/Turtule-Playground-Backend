package org.turtle.minecraft_service.dto.community.read.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostElement {
    private Long postId;
    private PostType postType;
    private String title;
    private String creator;
    private String content;
    private String imageName;
    private int views;
    private int likes;
    private LocalDateTime createdAt;

    public static PostElement fromEntity(Post post){
        return PostElement.builder()
                .postId(post.getId())
                .postType(post.getPostType())
                .title(post.getTitle())
                .creator(post.getUser().getNickname())
                .content(post.getContent())
                .imageName(post.getPostImages().get(0).getImageName())
                .views(post.getViews())
                .likes(post.getPostLike().size())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
