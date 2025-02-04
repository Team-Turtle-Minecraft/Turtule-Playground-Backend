package org.turtle.minecraft_service.dto.community.read.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostListDto {

    private PostListMetaData metaData;
    private List<PostElement> posts;

    public static PostListDto of(String postImageApiUrlPrefix, Page<Post> postPage){
        return PostListDto.builder()
                .metaData(PostListMetaData.of(postPage.getTotalPages(), postPage.getNumber(), postImageApiUrlPrefix))
                .posts(postPage.map(PostElement::fromEntity).toList())
                .build();
    }

}
