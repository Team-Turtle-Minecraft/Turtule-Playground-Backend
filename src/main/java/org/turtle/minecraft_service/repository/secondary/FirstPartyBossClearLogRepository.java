package org.turtle.minecraft_service.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.secondary.boss.FirstPartyBossClearLog;

import java.util.Optional;

public interface FirstPartyBossClearLogRepository extends JpaRepository<FirstPartyBossClearLog, Long> {

    Optional<FirstPartyBossClearLog> findByBossName(String bossName);
}
