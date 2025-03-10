package org.turtle.minecraft_service.dto.ranking.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MoneyRankingDto {

    private List<MoneyRanker> moneyRankers;

    public static MoneyRankingDto from(List<MinecraftUser> moneyRankers){
        return MoneyRankingDto.builder()
                .moneyRankers(moneyRankers.stream()
                        .map(MoneyRanker::fromEntity).toList())
                .build();
    }

}
