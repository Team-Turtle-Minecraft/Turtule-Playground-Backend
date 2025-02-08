package org.turtle.minecraft_service.dto.ranking.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

@Getter
@AllArgsConstructor
@Builder
public class CollectionRanker {

    private String playerName;
    private Long progress;

    public static CollectionRanker fromEntity(MinecraftUser minecraftUser){
        return CollectionRanker.builder()
                .playerName(minecraftUser.getPlayerName())
                .progress(minecraftUser.getProgress())
                .build();
    }
}
