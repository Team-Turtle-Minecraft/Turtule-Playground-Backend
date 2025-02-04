package org.turtle.minecraft_service.dto.community.read.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyPostListDto {

    private int foundPosts;
    private List<MyPostElement> myPosts;

    public static MyPostListDto of(List<Post> myPosts){
        return MyPostListDto.builder()
                .foundPosts(myPosts.size())
                .myPosts(myPosts.stream().map(MyPostElement::fromEntity).toList())
                .build();
    }
}
