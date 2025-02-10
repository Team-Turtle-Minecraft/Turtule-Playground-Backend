package org.turtle.minecraft_service.dto.ranking.boss.solo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.boss.SoloBossClearLog;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SoloBossClearTimeRanker {

    private String playerName;
    private int clearTime;
    private int rankPosition;
    private LocalDateTime clearDay;

    public static SoloBossClearTimeRanker fromEntity(SoloBossClearLog clearTimeRanker){
        return SoloBossClearTimeRanker.builder()
                .playerName(clearTimeRanker.getPlayerName())
                .clearTime(clearTimeRanker.getClearTime())
                .rankPosition(clearTimeRanker.getRankPosition())
                .clearDay(clearTimeRanker.getUpdatedAt())
                .build();
    }

}
