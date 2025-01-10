package org.turtle.minecraft_service.dto.community.read.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class PostListMetaData {
    private int totalPage;
    private int currentPage;
    private String postImageApiUrlPrefix;

    public static PostListMetaData of(int totalPage, int currentPage, String postImageApiUrlPrefix) {
        return PostListMetaData.builder()
                .totalPage(totalPage)
                .currentPage(currentPage + 1)
                .postImageApiUrlPrefix(postImageApiUrlPrefix)
                .build();

    }
}
