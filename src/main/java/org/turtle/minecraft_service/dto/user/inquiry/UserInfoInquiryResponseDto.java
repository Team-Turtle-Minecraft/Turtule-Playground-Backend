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

    private String loginStatus;

    private Long totalEnchantSuccess;

    private Long totalEnchantFail;

    private Long totalEnchantAttempts;

    private double totalEnchantRate;

    private String characterClass;

    private int characterLevel;

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
                .title(dto.getTitle())
                .progress(dto.getProgress())
                .loginStatus(dto.getLoginStatus())
                .totalEnchantSuccess(dto.getTotalEnchantSuccess())
                .totalEnchantFail(dto.getTotalEnchantFail())
                .totalEnchantAttempts(dto.getTotalEnchantAttempts())
                .totalEnchantRate(dto.getTotalEnchantRate())
                .characterClass(dto.getCharacterClass())
                .characterLevel(dto.getCharacterLevel())
                .smithingLevel(dto.getSmithingLevel())
                .farmingLevel(dto.getFarmingLevel())
                .cookingLevel(dto.getCookingLevel())
                .miningLevel(dto.getMiningLevel())
                .gatheringLevel(dto.getGatheringLevel())
                .fisherLevel(dto.getFisherLevel())
                .build();
    }
}
