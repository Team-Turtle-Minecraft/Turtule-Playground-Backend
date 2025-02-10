package org.turtle.minecraft_service.repository.secondary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.domain.secondary.boss.SoloBossClearLog;

import java.util.List;

public interface SoloBossClearLogRepository extends JpaRepository<SoloBossClearLog, Long> {

    @Query("SELECT s FROM SoloBossClearLog s WHERE s.bossName = :bossName AND  s.rankPosition <= 4")
    List<SoloBossClearLog> findByBossNameAndRankPosition(@Param("bossName") String bossName);
}
