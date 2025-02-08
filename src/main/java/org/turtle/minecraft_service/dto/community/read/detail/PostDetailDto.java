package org.turtle.minecraft_service.dto.community.read.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;
import org.turtle.minecraft_service.domain.primary.community.Post;
import org.turtle.minecraft_service.domain.primary.community.PostImage;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailDto {

    private Long postId;
    private PostType postType;
    private String title;
    private String creator;
    private String content;
    private String postImageApiUrlPrefix;
    private List<String> postImages;
    private int views;
    private int likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostDetailDto of(String postImageApiUrlPrefix, Post post, List<PostImage> postImages) {
        return PostDetailDto.builder()
                .postId(post.getId())
                .postType(post.getPostType())
                .title(post.getTitle())
                .creator(post.getUser().getNickname())
                .content(post.getContent())
                .postImageApiUrlPrefix(postImageApiUrlPrefix)
                .postImages(postImages.stream().map(PostImage::getImageName).toList())
                .views(post.getViews())
                .likes(post.getPostLike().size())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();


    }


}
