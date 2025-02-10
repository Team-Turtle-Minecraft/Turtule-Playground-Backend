package org.turtle.minecraft_service.dto.ranking.boss.solo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.boss.FirstSoloBossClearLog;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class FirstSoloBossClearUser {

    private String playerName;
    private int clearTime;
    private LocalDateTime clearDay;

    public static FirstSoloBossClearUser fromEntity(FirstSoloBossClearLog firstClearUser){
        return FirstSoloBossClearUser.builder()
                .playerName(firstClearUser.getPlayerName())
                .clearTime(firstClearUser.getClearTime())
                .clearDay(firstClearUser.getAchievedAt())
                .build();
    }
}
