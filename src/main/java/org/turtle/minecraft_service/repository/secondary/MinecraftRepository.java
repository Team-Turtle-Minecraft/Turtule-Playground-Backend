package org.turtle.minecraft_service.repository.secondary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.domain.secondary.MinecraftUser;

import java.util.Optional;


public interface MinecraftRepository extends JpaRepository<MinecraftUser, Long> {

   Optional<MinecraftUser> findByPlayerName(String nickname);
}
