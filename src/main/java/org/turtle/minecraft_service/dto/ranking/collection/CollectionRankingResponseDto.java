package org.turtle.minecraft_service.dto.ranking.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CollectionRankingResponseDto {

    private List<CollectionRanker> collectionRankers;

    public static CollectionRankingResponseDto fromDto(CollectionRankingDto dto){
        return CollectionRankingResponseDto.builder()
                .collectionRankers(dto.getCollectionRankers())
                .build();
    }
}
