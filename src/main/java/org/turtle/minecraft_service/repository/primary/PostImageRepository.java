package org.turtle.minecraft_service.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turtle.minecraft_service.domain.primary.community.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
