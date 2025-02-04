package org.turtle.minecraft_service.dto.user.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserAttendanceHistoryDto {

    private long attendanceCount;
    private List<String> attendanceHistory;

    public static UserAttendanceHistoryDto of(long attendanceCount, List<String> attendanceHistory) {
        return UserAttendanceHistoryDto.builder()
                .attendanceCount(attendanceCount)
                .attendanceHistory(attendanceHistory)
                .build();
    }


}
