package org.turtle.minecraft_service.dto.community.interaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostLikeStatusDto {

    private boolean postLikeStatus;

    public static PostLikeStatusDto fromEntity(boolean postLikeStatus) {
        return PostLikeStatusDto.builder()
                .postLikeStatus(postLikeStatus)
                .build();
    }
}
