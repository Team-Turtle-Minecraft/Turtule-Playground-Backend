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
@Schema(name = "UserFullAttendanceRewardResponseDto")
public class UserFullAttendanceRewardResponseDto {

    private String message;

    public static UserFullAttendanceRewardResponseDto fromDto(UserFullAttendanceRewardDto dto) {
        return UserFullAttendanceRewardResponseDto.builder()
                .message(dto.getMessage())
                .build();
    }
}
