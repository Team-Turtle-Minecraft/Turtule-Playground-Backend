package org.turtle.minecraft_service.repository.primary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.constant.PostType;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM post p LEFT JOIN FETCH p.postImages WHERE p.id = :postId")
    Optional<Post> findByIdWithImages(@Param("postId") Long postId);

    @Query("SELECT DISTINCT p FROM post p LEFT join fetch p.postImages WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% OR p.user.nickname LIKE %:keyword%")
    Page<Post> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Post> findAllByPostType(PostType postType, Pageable pageable);

    @Query("SELECT p FROM post p " +
            "LEFT JOIN p.postLike pl " +
            "WHERE (:postType IS NULL OR p.postType = :postType) " +
            "GROUP BY p.id, p.title, p.content, p.createdAt, p.postType, p.views " +
            "ORDER BY COUNT(pl) DESC")
    Page<Post> findAllOrderByLikesCount(@Param("postType") PostType postType, Pageable pageable);
}
