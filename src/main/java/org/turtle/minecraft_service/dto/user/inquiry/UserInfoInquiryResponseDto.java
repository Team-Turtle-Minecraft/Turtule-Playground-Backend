package org.turtle.minecraft_service.dto.user.inquiry;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.SnsType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class UserInfoInquiryResponseDto {

    private SnsType snsType;

    private String nickname;

    private double money;

    private LocalDateTime lastLogin;

    private String title;

    private Long progress;

    private Long badgeDiscovered;

    private Long historyDiscovered;

    private Long monstersDiscovered;

    private Long regionsDiscovered;


    public static UserInfoInquiryResponseDto fromDto(UserInfoInquiryDto dto){
        return UserInfoInquiryResponseDto.builder()
                .snsType(dto.getSnsType())
                .nickname(dto.getNickname())
                .money(dto.getMoney())
                .lastLogin(dto.getLastLogin())
                .title(dto.getTitle())
                .progress(dto.getProgress())
                .badgeDiscovered(dto.getBadgeDiscovered())
                .historyDiscovered(dto.getHistoryDiscovered())
                .monstersDiscovered(dto.getMonstersDiscovered())
                .regionsDiscovered(dto.getRegionsDiscovered())
                .build();
    }
}
