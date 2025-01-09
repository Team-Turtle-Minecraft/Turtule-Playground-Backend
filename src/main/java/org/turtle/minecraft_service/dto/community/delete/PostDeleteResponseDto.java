package org.turtle.minecraft_service.dto.community.delete;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "PostDeleteResponseDto")
public class PostDeleteResponseDto {

    @Schema(description = "응답 메시지", example = "게시물이 성공적으로 삭제되었습니다!")
    private String message;

    public static PostDeleteResponseDto createNewResponse(){
        return PostDeleteResponseDto.builder()
                .message("게시물이 성공적으로 삭제되었습니다!")
                .build();
    }
}
