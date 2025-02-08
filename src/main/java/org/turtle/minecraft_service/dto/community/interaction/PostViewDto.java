package org.turtle.minecraft_service.dto.community.interaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.turtle.minecraft_service.domain.primary.community.Post;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostViewDto {

    private int currentViews;


    public static PostViewDto fromEntity(Post post){
        return PostViewDto.builder()
                .currentViews(post.getViews())
                .build();
    }
}
