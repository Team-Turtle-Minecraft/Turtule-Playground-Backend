package org.turtle.minecraft_service.dto.ranking.boss.solo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.boss.FirstSoloBossClearLog;
import org.turtle.minecraft_service.domain.secondary.boss.SoloBossClearLog;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SoloBossClearRankingDto {

    private String bossName;
    private FirstSoloBossClearUser firstClearUser;
    private List<SoloBossClearTimeRanker> clearTimeRankers;

    public static SoloBossClearRankingDto of(String bossName,
                                             FirstSoloBossClearLog firstClearUser,
                                             List<SoloBossClearLog> clearTimeRankers
    ) {
        return SoloBossClearRankingDto.builder()
                .bossName(bossName)
                .firstClearUser(FirstSoloBossClearUser.fromEntity(firstClearUser))
                .clearTimeRankers(clearTimeRankers.stream().map(SoloBossClearTimeRanker::fromEntity).toList())
                .build();
    }
}
