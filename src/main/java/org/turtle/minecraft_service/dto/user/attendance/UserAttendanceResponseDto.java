package org.turtle.minecraft_service.dto.user.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserAttendanceResponseDto")
public class UserAttendanceResponseDto {

    @Schema(description = "응답 메시지", example = "출석체크 하였습니다!")
    private String message;

    public static UserAttendanceResponseDto fromDto(UserAttendanceDto dto){
        return UserAttendanceResponseDto.builder()
                .message(dto.getMessage())
                .build();
    }
}
