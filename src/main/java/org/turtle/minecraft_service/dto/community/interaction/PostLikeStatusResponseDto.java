package org.turtle.minecraft_service.dto.community.interaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(name = "PostLikeStatusResponseDto")
public class PostLikeStatusResponseDto {

    @Schema(description = "좋아요 여부", example = "true")
    private boolean postLikeStatus;

    public static PostLikeStatusResponseDto fromDto(PostLikeStatusDto dto) {
        return PostLikeStatusResponseDto.builder()
                .postLikeStatus(dto.isPostLikeStatus())
                .build();
    }
}
