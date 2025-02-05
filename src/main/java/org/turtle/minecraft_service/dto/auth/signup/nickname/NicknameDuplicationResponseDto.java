package org.turtle.minecraft_service.dto.auth.signup.nickname;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "NicknameDuplicationResponseDto")
public class NicknameDuplicationResponseDto {

    @Schema(description = "응답 메시지", example = "해당 닉네임으로 소셜 계정을 생성할 수 있습니다!")
    private String message;

    public static NicknameDuplicationResponseDto createNewResponse(){
        return NicknameDuplicationResponseDto.builder()
                .message("해당 닉네임으로 소셜 계정을 생성할 수 있습니다!")
                .build();
    }


}
