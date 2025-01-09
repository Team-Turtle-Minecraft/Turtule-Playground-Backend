package org.turtle.minecraft_service.domain.primary.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.turtle.minecraft_service.domain.primary.user.User;

@Entity(name = "postLike")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public static PostLike of(Post post, User user) {
        return PostLike.builder()
                .post(post)
                .user(user)
                .build();
    }


}
