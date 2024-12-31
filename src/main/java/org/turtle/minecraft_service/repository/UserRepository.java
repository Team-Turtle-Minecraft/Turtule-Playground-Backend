package org.turtle.minecraft_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findBySnsId(String snsId);
    Optional<User> findByNickname(String nickname);
}
