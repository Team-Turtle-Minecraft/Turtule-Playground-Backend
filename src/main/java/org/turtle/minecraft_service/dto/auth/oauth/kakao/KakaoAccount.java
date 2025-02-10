package org.turtle.minecraft_service.dto.auth.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoAccount {

    @JsonProperty("profile_needs_agreement")
    private boolean profileNeedsAgreement;

    @JsonProperty("email_needs_agreement")
    private boolean emailNeedsAgreement;

    private KakaoUserProfile profile;
}
