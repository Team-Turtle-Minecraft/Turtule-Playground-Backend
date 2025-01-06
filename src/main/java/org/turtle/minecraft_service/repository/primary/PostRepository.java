package org.turtle.minecraft_service.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.primary.community.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
