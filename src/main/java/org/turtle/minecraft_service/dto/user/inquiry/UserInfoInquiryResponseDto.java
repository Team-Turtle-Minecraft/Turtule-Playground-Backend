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

    private String loginStatus;

    private String characterClass;

    private int characterLevel;

    private int characterExp;

    private Long smithingLevel;

    private Long farmingLevel;

    private Long cookingLevel;

    private Long miningLevel;

    private Long gatheringLevel;

    private Long fisherLevel;


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
                .loginStatus(dto.getLoginStatus())
                .characterClass(dto.getCharacterClass())
                .characterLevel(dto.getCharacterLevel())
                .characterExp(dto.getCharacterExp())
                .smithingLevel(dto.getSmithingLevel())
                .farmingLevel(dto.getFarmingLevel())
                .cookingLevel(dto.getCookingLevel())
                .miningLevel(dto.getMiningLevel())
                .gatheringLevel(dto.getGatheringLevel())
                .fisherLevel(dto.getFisherLevel())
                .build();
    }
}
