package org.turtle.minecraft_service.domain.primary.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "postImage")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @CreatedDate
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    public static PostImage of(String imageName, Post newPost){
        return PostImage.builder()
                .imageName(imageName)
                .post(newPost)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
