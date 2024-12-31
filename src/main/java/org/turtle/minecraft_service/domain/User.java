package org.turtle.minecraft_service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.turtle.minecraft_service.constant.SnsType;
import org.turtle.minecraft_service.dto.auth.oauth.userInfo.OAuthUserInfoDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User implements OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String snsId;

    @Column(nullable = false, length = 50)
    private SnsType snsType;

    @Column(length = 255)
    private String profileImage;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(length = 255)
    private String email;

    private String authority;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getName() {
        return name;
    }

    public static User of(OAuthUserInfoDto dto, String nickname){
        return User.builder()
                .snsId(dto.getSnsId())
                .snsType(dto.getSnsType())
                .name(dto.getName())
                .nickname(nickname)
                .email(dto.getEmail())
                .build();
    }


}
