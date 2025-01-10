package org.turtle.minecraft_service.service.auth;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.SnsType;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.domain.secondary.MinecraftUser;
import org.turtle.minecraft_service.dto.auth.login.LoginDto;
import org.turtle.minecraft_service.dto.auth.login.LoginRequestDto;
import org.turtle.minecraft_service.dto.auth.signup.SignupDto;
import org.turtle.minecraft_service.dto.auth.signup.SignupRequestDto;
import org.turtle.minecraft_service.dto.auth.signup.nickname.NicknameDuplicationRequestDto;
import org.turtle.minecraft_service.dto.auth.oauth.userInfo.OAuthUserInfoDto;
import org.turtle.minecraft_service.dto.auth.tokenReIssue.TokenReIssueDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.provider.JwtTokenProvider;
import org.turtle.minecraft_service.repository.primary.UserRepository;
import org.turtle.minecraft_service.service.redis.RedisService;
import org.turtle.minecraft_service.service.user.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    private final GoogleOAuthService googleOAuthService;
    private final KakaoOAuthService kakaoOAuthService;
    private final NaverOAuthService naverOAuthService;
    private final UserService userService;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public void checkNickname(@Valid NicknameDuplicationRequestDto requestDto){

        if(userService.validateExistUserInTurtlePlayGround(requestDto.getNickname()) == null){
            throw new HttpErrorException(HttpErrorCode.UserNotFoundErrorInTurtlePlayGround);
        }

        if(userRepository.findByNickname(requestDto.getNickname()).isPresent()){
            throw new HttpErrorException(HttpErrorCode.AlreadyExistNicknameError);
        }

    }


    public SignupDto signup(@Valid SignupRequestDto requestDto){
        OAuthUserInfoDto userInfo = getUserInfo(requestDto.getSnsType(), requestDto.getAccessToken());
        Optional<User> user = userRepository.findBySnsId(userInfo.getSnsId());
        if (user.isPresent()){
            throw new HttpErrorException(HttpErrorCode.AlreadyExistUserError);
        }

        userRepository.save(User.of(userInfo, requestDto.getNickname()));

        String accessToken = jwtTokenProvider.generateAccessToken(userInfo.getSnsId());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        redisService.save(refreshToken, accessToken);

        return SignupDto.of(accessToken, refreshToken);
    }

    public LoginDto login(@Valid LoginRequestDto requestDto){
        OAuthUserInfoDto userInfo = getUserInfo(requestDto.getSnsType(), requestDto.getAccessToken());
        Optional<User> user = userRepository.findBySnsId(userInfo.getSnsId());
        if (user.isEmpty()){
            throw new HttpErrorException(HttpErrorCode.UserNotFoundError);
        }

        String accessToken = jwtTokenProvider.generateAccessToken(userInfo.getSnsId());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        redisService.save(refreshToken, accessToken);

        return LoginDto.of(accessToken, refreshToken);
    }

    public void logout(String refreshToken){
        String resolvedRefreshToken = jwtTokenProvider.resolveToken(refreshToken);

        Optional<String> savedAccessToken = redisService.get(resolvedRefreshToken);
        if(savedAccessToken.isEmpty()){
            throw new HttpErrorException(HttpErrorCode.NoSuchRefreshTokenError);
        }

        redisService.delete(resolvedRefreshToken);

    }

    public TokenReIssueDto reIssueToken(String accessToken, String refreshToken){

        String resolvedAccessToken = jwtTokenProvider.resolveToken(accessToken);
        String resolvedRefreshToken = jwtTokenProvider.resolveToken(refreshToken);

        String savedAccessToken = redisService.get(resolvedRefreshToken)
                .orElseThrow(() -> new HttpErrorException(HttpErrorCode.NoSuchRefreshTokenError));

        // RefreshToken 유효성 및 만료여부 확인
        boolean isExpiredRefreshToken = jwtTokenProvider.isExpiredToken(resolvedRefreshToken);
        if (isExpiredRefreshToken) {
            redisService.delete(resolvedAccessToken);
            throw new HttpErrorException(HttpErrorCode.ExpiredRefreshTokenError);
        }

        // RefreshToken이 탈취 당한 경우
        if (!resolvedAccessToken.equals(savedAccessToken)) {
            redisService.delete(resolvedAccessToken);
            throw new HttpErrorException(HttpErrorCode.NoSuchAccessTokenError);
        }

        // AccessToken 유효성 및 만료여부 확인
        boolean isExpiredAccessToken = jwtTokenProvider.isExpiredToken(resolvedAccessToken);
        if (!isExpiredAccessToken) {
            redisService.delete(resolvedRefreshToken);
            throw new HttpErrorException(HttpErrorCode.NotExpiredAccessTokenError);
        }

        // 토큰 재발행
        String reIssuedAccessToken = jwtTokenProvider.reIssueAccessToken(resolvedAccessToken);
        redisService.delete(resolvedAccessToken);
        redisService.save(resolvedRefreshToken, reIssuedAccessToken);
        return TokenReIssueDto.of(reIssuedAccessToken);
    }


    private OAuthUserInfoDto getUserInfo(SnsType snsType, String accessToken) {
        return switch (snsType) {
            case Google -> OAuthUserInfoDto.from(googleOAuthService.getUserInfo(accessToken));
            case Kakao -> OAuthUserInfoDto.from(kakaoOAuthService.getUserInfo(accessToken));
            case Naver -> OAuthUserInfoDto.from(naverOAuthService.getUserInfo(accessToken));
        };
    }

}
