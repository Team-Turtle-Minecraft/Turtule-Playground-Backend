package org.turtle.minecraft_service.dto.community.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.turtle.minecraft_service.constant.PostType;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PostUpdateRequestDto {

    @Schema(example = "Free", description = "게시물 카테고리")
    @NotNull(message = "게시물 카테고리가 필요합니다.")
    private PostType postType;

    @Schema(example = "개쩌는거 발견함!", description = "게시물 제목")
    @NotNull(message = "게시물 제목이 필요합니다.")
    private String title;

    @Schema(example = "아끼 마크하다가 이거 찾았는데 이거 뭐냐?", description = "게시물 내용")
    @Size(min = 2, message = "본문을 최소 2글자 이상 입력해주세요.")
    private String content;
}
