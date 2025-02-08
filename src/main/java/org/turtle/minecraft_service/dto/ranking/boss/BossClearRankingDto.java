package org.turtle.minecraft_service.dto.ranking.boss;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.dto.ranking.boss.party.BossClearTimeRankerParty;
import org.turtle.minecraft_service.dto.ranking.boss.party.FirstBossClearParty;
import org.turtle.minecraft_service.dto.ranking.boss.party.PartyBossClearRankingDto;
import org.turtle.minecraft_service.dto.ranking.boss.solo.FirstSoloBossClearUser;
import org.turtle.minecraft_service.dto.ranking.boss.solo.SoloBossClearRankingDto;
import org.turtle.minecraft_service.dto.ranking.boss.solo.SoloBossClearTimeRanker;


import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class BossClearRankingDto {

    private String bossName;
    private FirstSoloBossClearUser firstBossClearUser;
    private List<SoloBossClearTimeRanker> soloClearTimeRankers;
    private FirstBossClearParty firstBossClearParty;
    private List<BossClearTimeRankerParty> bossClearTimeRankerParties;


    public static BossClearRankingDto from(SoloBossClearRankingDto dto){
        return BossClearRankingDto.builder()
                .bossName(dto.getBossName())
                .firstBossClearUser(dto.getFirstClearUser())
                .soloClearTimeRankers(dto.getClearTimeRankers())
                .firstBossClearParty(null)
                .bossClearTimeRankerParties(null)
                .build();
    }

    public static BossClearRankingDto from(PartyBossClearRankingDto dto){
        return BossClearRankingDto.builder()
                .bossName(dto.getBossName())
                .firstBossClearUser(null)
                .soloClearTimeRankers(null)
                .firstBossClearParty(dto.getFirstBossClearParty())
                .bossClearTimeRankerParties(dto.getBossClearTimeRankerParties())
                .build();
    }



}
