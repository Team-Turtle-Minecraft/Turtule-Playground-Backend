package org.turtle.minecraft_service.dto.ranking.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CollectionRankingDto {

    private List<CollectionRanker> collectionRankers;

    public static CollectionRankingDto from(List<MinecraftUser> collectionRankers) {
        return CollectionRankingDto.builder()
                .collectionRankers(collectionRankers.stream().map(CollectionRanker::fromEntity).toList())
                .build();

    }
}
