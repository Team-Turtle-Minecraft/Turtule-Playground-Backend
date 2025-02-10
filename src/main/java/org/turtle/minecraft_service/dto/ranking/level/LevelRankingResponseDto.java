package org.turtle.minecraft_service.dto.ranking.level;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "LevelRankingResponseDto")
public class LevelRankingResponseDto {

    private String jobName;
    private List<JobLevelRanker> rankers;

    public static LevelRankingResponseDto fromDto(LevelRankingDto dto) {
        return LevelRankingResponseDto.builder()
                .jobName(dto.getJobName())
                .rankers(dto.getRankers())
                .build();
    }
}
