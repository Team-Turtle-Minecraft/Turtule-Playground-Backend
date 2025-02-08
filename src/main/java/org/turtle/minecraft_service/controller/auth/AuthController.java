package org.turtle.minecraft_service.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.dto.auth.login.LoginDto;

import org.turtle.minecraft_service.dto.auth.login.LoginRequestDto;
import org.turtle.minecraft_service.dto.auth.login.LoginResponseDto;
import org.turtle.minecraft_service.dto.auth.logout.LogoutResponseDto;
import org.turtle.minecraft_service.dto.auth.signup.SignupDto;
import org.turtle.minecraft_service.dto.auth.signup.SignupRequestDto;
import org.turtle.minecraft_service.dto.auth.signup.SignupResponseDto;
import org.turtle.minecraft_service.dto.auth.signup.nickname.NicknameDuplicationRequestDto;
import org.turtle.minecraft_service.dto.auth.signup.nickname.NicknameDuplicationResponseDto;
import org.turtle.minecraft_service.dto.auth.tokenReIssue.TokenReIssueDto;
import org.turtle.minecraft_service.dto.auth.tokenReIssue.TokenReIssueResponseDto;
import org.turtle.minecraft_service.service.auth.AuthService;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExample;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExamples;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "인증")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "닉네임 중복확인")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.UserNotFoundErrorInTurtlePlayGround),
            @ApiErrorCodeExample(value = HttpErrorCode.AlreadyExistNicknameError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = NicknameDuplicationResponseDto.class)))
    @PostMapping("/check/nickname")
    public ResponseEntity<NicknameDuplicationResponseDto> checkNickname(@Valid @RequestBody NicknameDuplicationRequestDto requestDto){
        authService.checkNickname(requestDto);
        return new ResponseEntity<>(NicknameDuplicationResponseDto.createNewResponse(), HttpStatus.OK);
    }

    @Operation(summary = "회원 가입")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = LoginResponseDto.class)))
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto){
        SignupDto signup = authService.signup(requestDto);
        return new ResponseEntity<>(SignupResponseDto.fromDto(signup), HttpStatus.CREATED);
    }

    @Operation(summary = "로그인")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.ForbiddenKakaoError),
            @ApiErrorCodeExample(value = HttpErrorCode.ForbiddenNaverError),
            @ApiErrorCodeExample(value = HttpErrorCode.UnauthorizedKakaoError),
            @ApiErrorCodeExample(value = HttpErrorCode.UnauthorizedNaverError),
            @ApiErrorCodeExample(value = HttpErrorCode.UserNotFoundError),
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LoginResponseDto.class)))
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto){
        LoginDto dto = authService.login(requestDto);
        return new ResponseEntity<>(LoginResponseDto.fromDto(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "로그아웃")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.NoSuchRefreshTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError)
    })
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = LogoutResponseDto.class)))
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(
            @Schema(example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3...")
            @RequestHeader("Authorization-refresh") String refreshToken
    ) {
       authService.logout(refreshToken);
       return new ResponseEntity<>(LogoutResponseDto.createNewResponse(), HttpStatus.CREATED);
    }

    @Operation(summary = "토큰 재발행")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidTokenError, description = "토큰이 유효하지 않을 경우 발생하는 에러입니다."),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.NoSuchRefreshTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredRefreshTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.NoSuchAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.UserNotFoundError, description = "AccessToken의 Payload에서 유저 정보를 추출할 수 없을 경우 발생하는 에러입니다.")
    })
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = TokenReIssueResponseDto.class)))
    @PostMapping("/refreshToken")
    public ResponseEntity<TokenReIssueResponseDto> reIssueToken(
            @Schema(example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3...")
            @RequestHeader("Authorization") String accessToken,
            @Schema(example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3...")
            @RequestHeader("Authorization-refresh") String refreshToken
    ){
        TokenReIssueDto dto = authService.reIssueToken(accessToken, refreshToken);
        return new ResponseEntity<>(TokenReIssueResponseDto.fromDto(dto), HttpStatus.CREATED);
    }
}
