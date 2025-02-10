package org.turtle.minecraft_service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SnsBaseUrl {
    GoogleBaseUrl("https://www.googleapis.com"),
    KakaoBaseUrl("https://kapi.kakao.com"),
    NaverBaseUrl("https://openapi.naver.com"),
    TestBaseUrl("http://localhost");

    private final String url;
}
