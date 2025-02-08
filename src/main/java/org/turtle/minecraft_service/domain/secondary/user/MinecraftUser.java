package org.turtle.minecraft_service.domain.secondary.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

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

    @Column(name = "tag")
    private String tag;

    @Column(name = "primary_group")
    private String primaryGroup;

    @Column(name = "progress")
    private Long progress;

    @Column(name = "current_status")
    private String currentStatus;

    @Column(name = "total_enchant_success")
    private Long totalEnchantSuccess;

    @Column(name = "total_enchant_fail")
    private Long totalEnchantFail;

    @Column(name = "total_enchant_attempts")
    private Long totalEnchantAttempts;

    @Column(name = "total_enchant_rate")
    private double totalEnchantRate;

    @Column(name = "character_class")
    private String characterClass;

    @Column(name = "character_level")
    private int characterLevel;

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
