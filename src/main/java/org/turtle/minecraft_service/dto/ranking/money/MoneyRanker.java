package org.turtle.minecraft_service.dto.ranking.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

@Getter
@AllArgsConstructor
@Builder
public class MoneyRanker {

    private String playerName;
    private double money;

    public static MoneyRanker fromEntity(MinecraftUser moneyRanker){
        return MoneyRanker.builder()
                .playerName(moneyRanker.getPlayerName())
                .money(moneyRanker.getMoney())
                .build();
    }
}
