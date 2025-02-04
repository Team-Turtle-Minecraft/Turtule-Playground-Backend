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
@Schema(name = "UserHalfAttendanceRewardResponseDto")
public class UserHalfAttendanceRewardResponseDto {

    private String message;

    public static UserHalfAttendanceRewardResponseDto fromDto(UserHalfAttendanceRewardDto dto){
        return UserHalfAttendanceRewardResponseDto.builder()
                .message(dto.getMessage())
                .build();
    }
}
