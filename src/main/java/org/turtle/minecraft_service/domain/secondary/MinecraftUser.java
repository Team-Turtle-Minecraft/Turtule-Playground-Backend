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
@Setter
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



}
