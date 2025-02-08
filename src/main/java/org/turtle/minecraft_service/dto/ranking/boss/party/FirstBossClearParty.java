package org.turtle.minecraft_service.dto.ranking.boss.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.boss.FirstPartyBossClearLog;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class FirstBossClearParty {

    private String partyName;
    private String partyMembers;
    private int clearTime;
    private LocalDateTime clearDay;

    public static FirstBossClearParty fromEntity(FirstPartyBossClearLog firstBossClearParty){
        return FirstBossClearParty.builder()
                .partyName(firstBossClearParty.getPartyName())
                .partyMembers(firstBossClearParty.getPartyMembers())
                .clearTime(firstBossClearParty.getClearTime())
                .clearDay(firstBossClearParty.getAchievedAt())
                .build();
    }
}
