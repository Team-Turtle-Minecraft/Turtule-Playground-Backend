package org.turtle.minecraft_service.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.domain.secondary.boss.PartyBossClearLog;

import java.util.List;

public interface PartyBossClearLogRepository extends JpaRepository<PartyBossClearLog, Long> {

    @Query("SELECT p FROM PartyBossClearLog p WHERE p.bossName = :bossName AND p.rankPosition <=4")
    List<PartyBossClearLog> findByBossNameAndRankPosition(@Param("bossName")String bossName);

}
