package org.turtle.minecraft_service.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.secondary.boss.FirstSoloBossClearLog;

import java.util.Optional;

public interface FirstSoloBossClearLogRepository extends JpaRepository<FirstSoloBossClearLog, Long> {

    Optional<FirstSoloBossClearLog> findByBossName(String bossName);

}
