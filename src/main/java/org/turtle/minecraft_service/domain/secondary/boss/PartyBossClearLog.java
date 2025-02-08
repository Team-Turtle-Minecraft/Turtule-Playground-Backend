package org.turtle.minecraft_service.domain.secondary.boss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "normalized_party_rankings")
@Getter
public class PartyBossClearLog {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "boss_name")
    private String bossName;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "party_members")
    private String partyMembers;

    @Column(name = "clear_time")
    private int clearTime;

    @Column(name = "rank_position")
    private int rankPosition;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
