package org.turtle.minecraft_service.dto.user.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserHalfAttendanceRewardDto {

    private String message;

    public static UserHalfAttendanceRewardDto from(String response){
        return UserHalfAttendanceRewardDto.builder()
                .message(response)
                .build();
    }
}
