package org.turtle.minecraft_service.dto.user.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAttendanceDto {

    private String message;

    public static UserAttendanceDto from(String response){
        return UserAttendanceDto.builder()
                .message(response)
                .build();
    }
}
