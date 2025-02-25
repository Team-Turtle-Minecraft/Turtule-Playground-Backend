package org.turtle.minecraft_service.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserCountResponseDto" )
public class UserCountResponseDto {

    @Schema(description = "운영진을 제외한 서비스 이용자 수", example = "13")
    private int countOfUsers;

    public static UserCountResponseDto fromDto(UserCountDto dto) {
        return UserCountResponseDto.builder()
                .countOfUsers(dto.getCountOfUsers())
                .build();
    }
}
