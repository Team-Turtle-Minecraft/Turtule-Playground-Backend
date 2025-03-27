package org.turtle.minecraft_service.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.primary.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findBySnsId(String snsId);
    Optional<User> findByNickname(String nickname);
    List<User> findAllByNicknameIn(List<String> nicknames);
}
