package org.turtle.minecraft_service.dto.user.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFullAttendanceRewardDto {

    private String message;

    public static UserFullAttendanceRewardDto from(String response){
        return UserFullAttendanceRewardDto.builder()
                .message(response)
                .build();
    }
}
