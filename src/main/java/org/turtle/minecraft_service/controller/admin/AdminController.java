package org.turtle.minecraft_service.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.dto.admin.attendance.AttendanceRestoreDto;
import org.turtle.minecraft_service.dto.admin.attendance.AttendanceRestoreRequestDto;
import org.turtle.minecraft_service.dto.admin.attendance.AttendanceRestoreResponseDto;
import org.turtle.minecraft_service.service.admin.AdminService;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExample;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExamples;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "관리자용 기능")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "출석체크 복구")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.UserNotFoundError)
    })
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = AttendanceRestoreResponseDto.class)))
    @PostMapping("/attendance/restore")
    public ResponseEntity<AttendanceRestoreResponseDto> restoreAttendance(@Valid @RequestBody AttendanceRestoreRequestDto requestDto){

        AttendanceRestoreDto dto = adminService.restoreAttendance(requestDto);

        return new ResponseEntity<>(AttendanceRestoreResponseDto.fromDto(dto), HttpStatus.CREATED);
    }
}
