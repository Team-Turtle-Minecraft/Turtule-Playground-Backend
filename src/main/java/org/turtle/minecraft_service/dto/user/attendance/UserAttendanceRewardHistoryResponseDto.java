package org.turtle.minecraft_service.dto.user.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "UserAttendanceRewardHistoryResponseDto")
public class UserAttendanceRewardHistoryResponseDto {

    private boolean halfAttendanceRewardStatus;
    private boolean fullAttendanceRewardStatus;

    public static UserAttendanceRewardHistoryResponseDto fromDto(UserAttendanceRewardHistoryDto dto) {
        return UserAttendanceRewardHistoryResponseDto.builder()
                .halfAttendanceRewardStatus(dto.isHalfAttendanceRewardStatus())
                .fullAttendanceRewardStatus(dto.isFullAttendanceRewardStatus())
                .build();
    }
}
