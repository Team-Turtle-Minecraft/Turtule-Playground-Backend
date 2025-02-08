package org.turtle.minecraft_service.dto.ranking.boss.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.boss.FirstPartyBossClearLog;
import org.turtle.minecraft_service.domain.secondary.boss.PartyBossClearLog;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PartyBossClearRankingDto {

    private String bossName;
    private FirstBossClearParty firstBossClearParty;
    private List<BossClearTimeRankerParty> bossClearTimeRankerParties;

    public static PartyBossClearRankingDto of(String bossName,
                                              FirstPartyBossClearLog firstBossClearParty,
                                              List<PartyBossClearLog> bossClearTimeRankerParties
    ) {
        return PartyBossClearRankingDto.builder()
                .bossName(bossName)
                .firstBossClearParty(FirstBossClearParty.fromEntity(firstBossClearParty))
                .bossClearTimeRankerParties(bossClearTimeRankerParties.stream().map(BossClearTimeRankerParty::fromEntity).toList())
                .build();
    }
}
