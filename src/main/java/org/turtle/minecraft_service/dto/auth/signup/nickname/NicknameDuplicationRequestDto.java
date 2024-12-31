package org.turtle.minecraft_service.dto.auth.signup.nickname;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameDuplicationRequestDto {

    @Schema(example = "kkOma_fan", description = "거북이 놀이터에서 사용중인 실제 닉네임")
    @NotNull(message = "닉네임이 필요합니다.")
    private String nickname;
}
