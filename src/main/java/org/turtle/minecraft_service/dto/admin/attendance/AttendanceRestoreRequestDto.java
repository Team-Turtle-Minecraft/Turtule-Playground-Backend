package org.turtle.minecraft_service.dto.admin.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AttendanceRestoreRequestDto {

    @Schema(example = "[kkOma_fan, _appli_]", description = "출석기록을 복구할 유저 닉네임 미입력시 전체 유저")
    private List<String> users;

    @Schema(example = "2025-03-23", description = "복구할 출석 날짜")
    @NotNull(message = "날짜는 필수 입력값입니다")
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])",
            message = "날짜는 yyyy-MM-dd 형식이어야 합니다")
    private String targetDate;

}
