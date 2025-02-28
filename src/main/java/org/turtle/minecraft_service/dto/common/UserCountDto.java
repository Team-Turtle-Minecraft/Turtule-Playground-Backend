package org.turtle.minecraft_service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCountDto {

    private int countOfUsers;

    public static UserCountDto from(int countOfUsers) {
        return UserCountDto.builder()
                .countOfUsers(countOfUsers)
                .build();
    }
}
