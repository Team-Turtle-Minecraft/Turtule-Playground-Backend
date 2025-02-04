package org.turtle.minecraft_service.dto.user.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserAttendanceRewardHistoryDto {

    private boolean halfAttendanceRewardStatus;
    private boolean fullAttendanceRewardStatus;

    public static UserAttendanceRewardHistoryDto of(boolean halfAttendanceRewardStatus, boolean fullAttendanceRewardStatus) {
        return UserAttendanceRewardHistoryDto.builder()
                .halfAttendanceRewardStatus(halfAttendanceRewardStatus)
                .fullAttendanceRewardStatus(fullAttendanceRewardStatus)
                .build();
    }
}
