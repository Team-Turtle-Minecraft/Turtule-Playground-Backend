package org.turtle.minecraft_service.dto.ranking.level;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class LevelRankingDto {

    private String jobName;
    private List<JobLevelRanker> rankers;

    public static LevelRankingDto fromCombatJobRankers(String jobName, List<MinecraftUser> combatJobRankers){
        return LevelRankingDto.builder()
                .jobName(jobName)
                .rankers(combatJobRankers.stream().map(JobLevelRanker::fromCombatJobRanker).toList())
                .build();
    }

    public static LevelRankingDto fromFishermanRankers(String jobName, List<MinecraftUser> fishermanRankers){
        return LevelRankingDto.builder()
                .jobName(jobName)
                .rankers(fishermanRankers.stream().map(JobLevelRanker::fromFishermanRanker).toList())
                .build();
    }

    public static LevelRankingDto fromMinerRankers(String jobName, List<MinecraftUser> minerRankers){
        return LevelRankingDto.builder()
                .jobName(jobName)
                .rankers(minerRankers.stream().map(JobLevelRanker::fromMinerRanker).toList())
                .build();
    }

    public static LevelRankingDto fromFarmerRanking(String jobName, List<MinecraftUser> farmerRankers){
        return LevelRankingDto.builder()
                .jobName(jobName)
                .rankers(farmerRankers.stream().map(JobLevelRanker::fromFarmerRanker).toList())
                .build();
    }

    public static LevelRankingDto fromCookRanking(String jobName, List<MinecraftUser> cookRankers){
        return LevelRankingDto.builder()
                .jobName(jobName)
                .rankers(cookRankers.stream().map(JobLevelRanker::fromCookRanker).toList())
                .build();
    }

    public static LevelRankingDto fromBlacksmithRanking(String jobName, List<MinecraftUser> blacksmithRankers){
        return LevelRankingDto.builder()
                .jobName(jobName)
                .rankers(blacksmithRankers.stream().map(JobLevelRanker::fromBlacksmithRanker).toList())
                .build();
    }
}
