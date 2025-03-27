package org.turtle.minecraft_service.dto.admin.attendance;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRestoreDto {
    private List<String> processedUsers;

    public static AttendanceRestoreDto of(List<String> processedUsers) {
        return AttendanceRestoreDto.builder()
                .processedUsers(processedUsers)
                .build();
    }
}
