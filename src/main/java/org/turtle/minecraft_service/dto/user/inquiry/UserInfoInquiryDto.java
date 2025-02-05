package org.turtle.minecraft_service.dto.user.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.SnsType;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.domain.secondary.MinecraftUser;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class UserInfoInquiryDto {

    private SnsType snsType;

    private String nickname;

    private double money;

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

    public static UserInfoInquiryDto of(User user, MinecraftUser minecraftUser){
        return UserInfoInquiryDto.builder()
                .snsType(user.getSnsType())
                .nickname(minecraftUser.getPlayerName())
                .money(minecraftUser.getMoney())
                .title(minecraftUser.getTag())
                .progress(minecraftUser.getProgress())
                .loginStatus(minecraftUser.getCurrentStatus())
                .totalEnchantSuccess(minecraftUser.getTotalEnchantSuccess())
                .totalEnchantFail(minecraftUser.getTotalEnchantFail())
                .totalEnchantAttempts(minecraftUser.getTotalEnchantAttempts())
                .totalEnchantRate(minecraftUser.getTotalEnchantRate())
                .characterClass(minecraftUser.getCharacterClass())
                .characterLevel(minecraftUser.getCharacterLevel())
                .smithingLevel(minecraftUser.getSmithingLevel())
                .farmingLevel(minecraftUser.getFarmingLevel())
                .gatheringLevel(minecraftUser.getGatheringLevel())
                .fisherLevel(minecraftUser.getFisherLevel())
                .cookingLevel(minecraftUser.getCookingLevel())
                .miningLevel(minecraftUser.getMiningLevel())
                .build();
    }

}
