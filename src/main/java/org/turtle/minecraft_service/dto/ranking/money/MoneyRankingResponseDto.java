package org.turtle.minecraft_service.dto.ranking.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MoneyRankingResponseDto {

    private List<String> moneyRankers;

    public static MoneyRankingResponseDto fromDto(MoneyRankingDto dto) {
        return MoneyRankingResponseDto.builder()
                .moneyRankers(dto.getMoneyRankers())
                .build();
    }
}
