package org.turtle.minecraft_service.dto.ranking.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.primary.community.Post;
import org.turtle.minecraft_service.dto.community.read.list.PostElement;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostRankingDto {

    private String postImageApiUrlPrefix;
    private List<PostElement> posts;

    public static PostRankingDto of(String postImageApiUrlPrefix, List<Post> posts){
        return PostRankingDto.builder()
                .postImageApiUrlPrefix(postImageApiUrlPrefix)
                .posts(posts.stream().map(PostElement::fromEntity).toList())
                .build();
    }

}
