package org.turtle.minecraft_service.dto.user.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "UserAttendanceHistoryResponseDto")
public class UserAttendanceHistoryResponseDto {

    @Schema(description = "이번달 출석수", example = "15")
    private long attendanceCount;

    @Schema(description = "이번달 출석 기록")
    private List<String> attendanceHistory;

    public static UserAttendanceHistoryResponseDto fromDto(UserAttendanceHistoryDto dto) {
        return UserAttendanceHistoryResponseDto.builder()
                .attendanceCount(dto.getAttendanceCount())
                .attendanceHistory(dto.getAttendanceHistory())
                .build();
    }
}
