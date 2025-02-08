package org.turtle.minecraft_service.dto.ranking.boss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.dto.ranking.boss.party.BossClearTimeRankerParty;
import org.turtle.minecraft_service.dto.ranking.boss.party.FirstBossClearParty;
import org.turtle.minecraft_service.dto.ranking.boss.solo.FirstSoloBossClearUser;
import org.turtle.minecraft_service.dto.ranking.boss.solo.SoloBossClearTimeRanker;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "BossClearRankingResponseDto")
public class BossClearRankingResponseDto {

    private String bossName;
    private FirstSoloBossClearUser firstBossClearUser;
    private List<SoloBossClearTimeRanker> soloClearTimeRankers;
    private FirstBossClearParty firstBossClearParty;
    private List<BossClearTimeRankerParty> bossClearTimeRankerParties;

    public static BossClearRankingResponseDto fromDto(BossClearRankingDto dto) {
        return BossClearRankingResponseDto.builder()
                .firstBossClearUser(dto.getFirstBossClearUser())
                .soloClearTimeRankers(dto.getSoloClearTimeRankers())
                .firstBossClearParty(dto.getFirstBossClearParty())
                .bossClearTimeRankerParties(dto.getBossClearTimeRankerParties())
                .build();
    }

}
