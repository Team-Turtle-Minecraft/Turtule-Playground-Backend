package org.turtle.minecraft_service.dto.auth.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoUserProfile {
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("thumbnail_image_url")
    private String thumbnailImageUrl;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("is_default_image")
    private boolean isDefaultImage;

    @JsonProperty("is_default_nickname")
    private boolean isDefaultNickname;
}
