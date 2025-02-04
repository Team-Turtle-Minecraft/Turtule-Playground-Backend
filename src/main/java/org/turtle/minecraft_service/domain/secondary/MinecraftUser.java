package org.turtle.minecraft_service.domain.secondary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_detailed_info")
@Getter
public class MinecraftUser {

    @Id
    @Column(name = "player_uuid")
    private UUID playerUuid;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "money")
    private double money;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "tag")
    private String tag;

    @Column(name = "primary_group")
    private String primaryGroup;

    @Column(name = "registrants")
    private Long registrants;

    @Column(name = "progress")
    private Long progress;

    @Column(name = "categories", columnDefinition = "TEXT")
    private String categories;

    @Column(name = "badgets_discoveries")
    private Long badgetsDiscoveries;

    @Column(name = "history_discoveries")
    private Long historyDiscoveries;

    @Column(name = "monsters_discoveries")
    private Long monstersDiscoveries;

    @Column(name = "regions_discoveries")
    private Long regionsDiscoveries;

    @Column(name = "current_status")
    private String currentStatus;

    @Column(name = "character_class")
    private String characterClass;

    @Column(name = "character_level")
    private int characterLevel;

    @Column(name = "character_exp")
    private int characterExp;

    @Column(name = "smithing_level")
    private Long smithingLevel;

    @Column(name = "farming_level")
    private Long farmingLevel;

    @Column(name = "cooking_level")
    private Long cookingLevel;

    @Column(name = "mining_level")
    private Long miningLevel;

    @Column(name = "gathering_level")
    private Long gatheringLevel;

    @Column(name = "fisher_level")
    private Long fisherLevel;


}
