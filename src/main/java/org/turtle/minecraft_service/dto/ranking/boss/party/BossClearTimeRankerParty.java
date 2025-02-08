package org.turtle.minecraft_service.dto.ranking.boss.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.boss.PartyBossClearLog;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class BossClearTimeRankerParty {

    private String partyName;

    private String partyMembers;

    private int clearTime;

    private int rankPosition;

    private LocalDateTime clearDay;

    public static BossClearTimeRankerParty fromEntity(PartyBossClearLog bossClearTimeRankerParty){
        return BossClearTimeRankerParty.builder()
                .partyName(bossClearTimeRankerParty.getPartyName())
                .partyMembers(bossClearTimeRankerParty.getPartyMembers())
                .clearTime(bossClearTimeRankerParty.getClearTime())
                .rankPosition(bossClearTimeRankerParty.getRankPosition())
                .clearDay(bossClearTimeRankerParty.getUpdatedAt())
                .build();

    }
}
