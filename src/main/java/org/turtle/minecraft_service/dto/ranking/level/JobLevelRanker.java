package org.turtle.minecraft_service.dto.ranking.level;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

@Getter
@AllArgsConstructor
@Builder
public class JobLevelRanker {

    private String playerName;
    private Long level;

    public static JobLevelRanker fromCombatJobRanker(MinecraftUser minecraftUser){
        return JobLevelRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .level((long) minecraftUser.getCharacterLevel())
                .build();
    }

    public static JobLevelRanker fromFishermanRanker(MinecraftUser minecraftUser){
        return JobLevelRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .level(minecraftUser.getFisherLevel())
                .build();
    }

    public static JobLevelRanker fromMinerRanker(MinecraftUser minecraftUser){
        return JobLevelRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .level(minecraftUser.getMiningLevel())
                .build();
    }

    public static JobLevelRanker fromFarmerRanker(MinecraftUser minecraftUser){
        return JobLevelRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .level(minecraftUser.getFarmingLevel())
                .build();
    }

    public static JobLevelRanker fromCookRanker(MinecraftUser minecraftUser){
        return JobLevelRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .level(minecraftUser.getCookingLevel())
                .build();
    }

    public static JobLevelRanker fromBlacksmithRanker(MinecraftUser minecraftUser){
        return JobLevelRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .level(minecraftUser.getSmithingLevel())
                .build();
    }
}
