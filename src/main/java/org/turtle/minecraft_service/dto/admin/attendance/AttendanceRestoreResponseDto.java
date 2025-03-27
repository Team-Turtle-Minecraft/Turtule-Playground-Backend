package org.turtle.minecraft_service.dto.admin.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Schema(name = "AttendanceRestoreResponseDto")
public class AttendanceRestoreResponseDto {

    @Schema(description = "응답 메시지", example = "출석체크가 성공적으로 복구되었습니다.")
    private String message;

    @Schema(description = "출석체크가 복구된 유저들", example = "['kkOma_fan', '_appli_']")
    private List<String> processedUsers;

    public static AttendanceRestoreResponseDto fromDto(AttendanceRestoreDto dto){
        return AttendanceRestoreResponseDto.builder()
                .message("출석체크가 성공적으로 복구되었습니다.")
                .processedUsers(dto.getProcessedUsers())
                .build();
    }

}
