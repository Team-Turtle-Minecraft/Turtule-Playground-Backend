package org.turtle.minecraft_service.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.primary.community.PostLike;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    PostLike findByPostIdAndUserId(Long postId, Long userId);
}
