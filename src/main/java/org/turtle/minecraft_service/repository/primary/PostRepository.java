package org.turtle.minecraft_service.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM post p LEFT JOIN FETCH p.postImages WHERE p.id = :postId")
    Optional<Post> findByIdWithImages(@Param("postId") Long postId);
}
