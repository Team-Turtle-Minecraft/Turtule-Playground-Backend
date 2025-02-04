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

    public static UserInfoInquiryDto of(User user, MinecraftUser minecraftUser){
        return UserInfoInquiryDto.builder()
                .snsType(user.getSnsType())
                .nickname(minecraftUser.getPlayerName())
                .money(minecraftUser.getMoney())
                .lastLogin(minecraftUser.getLastSeen())
                .title(minecraftUser.getTag())
                .progress(minecraftUser.getProgress())
                .badgeDiscovered(minecraftUser.getBadgetsDiscoveries())
                .historyDiscovered(minecraftUser.getHistoryDiscoveries())
                .monstersDiscovered(minecraftUser.getMonstersDiscoveries())
                .regionsDiscovered(minecraftUser.getRegionsDiscoveries())
                .loginStatus(minecraftUser.getCurrentStatus())
                .characterClass(minecraftUser.getCharacterClass())
                .characterLevel(minecraftUser.getCharacterLevel())
                .characterExp(minecraftUser.getCharacterExp())
                .smithingLevel(minecraftUser.getSmithingLevel())
                .farmingLevel(minecraftUser.getFarmingLevel())
                .gatheringLevel(minecraftUser.getGatheringLevel())
                .fisherLevel(minecraftUser.getFisherLevel())
                .cookingLevel(minecraftUser.getCookingLevel())
                .miningLevel(minecraftUser.getMiningLevel())
                .build();
    }

}
