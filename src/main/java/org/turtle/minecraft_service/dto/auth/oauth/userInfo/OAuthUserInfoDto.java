package org.turtle.minecraft_service.dto.auth.oauth.userInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.SnsType;
import org.turtle.minecraft_service.dto.auth.oauth.google.GoogleUserInfoResponse;
import org.turtle.minecraft_service.dto.auth.oauth.kakao.KakaoUserInfoResponse;
import org.turtle.minecraft_service.dto.auth.oauth.naver.NaverUserInfoResponse;

@Getter
@Builder
@AllArgsConstructor
public class OAuthUserInfoDto {
    private String snsId;
    private SnsType snsType;
    private String name;
    private String email;

    public static OAuthUserInfoDto from(GoogleUserInfoResponse googleUserInfoResponse){
        return OAuthUserInfoDto.builder()
                .snsId(googleUserInfoResponse.getSub())
                .snsType(SnsType.Google)
                .name(googleUserInfoResponse.getName())
                .email(googleUserInfoResponse.getEmail())
                .build();
    }

    public static OAuthUserInfoDto from(KakaoUserInfoResponse kakaoUserInfoResponse){
        return OAuthUserInfoDto.builder()
                .snsId(String.valueOf(kakaoUserInfoResponse.getId()))
                .snsType(SnsType.Kakao)
                .name(kakaoUserInfoResponse.getKakaoAccount().getProfile().getNickname())
                .email(null)
                .build();
    }

    public static OAuthUserInfoDto from(NaverUserInfoResponse naverUserInfoResponse){
        return OAuthUserInfoDto.builder()
                .snsId(String.valueOf(naverUserInfoResponse.getResponseDetails().getId()))
                .snsType(SnsType.Naver)
                .name(naverUserInfoResponse.getResponseDetails().getName())
                .email(naverUserInfoResponse.getResponseDetails().getEmail())
                .build();
    }

}
