package org.turtle.minecraft_service.controller.common;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.turtle.minecraft_service.dto.common.UserCountDto;
import org.turtle.minecraft_service.dto.common.UserCountResponseDto;
import org.turtle.minecraft_service.dto.user.inquiry.UserInfoInquiryResponseDto;
import org.turtle.minecraft_service.service.common.CommonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
@Tag(name = "공용 기능")
public class CommonController {

    private final CommonService commonService;

    @GetMapping("/count/users")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserCountResponseDto.class )))
    public ResponseEntity<UserCountResponseDto> getCountOfUsers(){
        UserCountDto dto = commonService.getCountOfUsers();
        return new ResponseEntity<>(UserCountResponseDto.fromDto(dto), HttpStatus.OK);
    }

}
