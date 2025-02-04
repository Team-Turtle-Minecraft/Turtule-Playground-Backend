package org.turtle.minecraft_service.controller.rank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.JobType;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.ranking.collection.CollectionRankingDto;
import org.turtle.minecraft_service.dto.ranking.collection.CollectionRankingResponseDto;
import org.turtle.minecraft_service.dto.ranking.level.LevelRankingDto;
import org.turtle.minecraft_service.dto.ranking.level.LevelRankingResponseDto;
import org.turtle.minecraft_service.dto.ranking.money.MoneyRankingDto;
import org.turtle.minecraft_service.dto.ranking.money.MoneyRankingResponseDto;
import org.turtle.minecraft_service.dto.ranking.post.PostRankingDto;
import org.turtle.minecraft_service.dto.ranking.post.PostRankingResponseDto;
import org.turtle.minecraft_service.service.ranking.RankingService;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExample;
import org.turtle.minecraft_service.swagger.ApiErrorCodeExamples;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
@Tag(name = "랭킹")
public class RankingController {

    private final RankingService rankingService;

    @Operation(summary = "도감 완성도")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CollectionRankingResponseDto.class )))
    @GetMapping("/collection")
    public ResponseEntity<CollectionRankingResponseDto> getCollectionRanking(@AuthenticationPrincipal User user){

        CollectionRankingDto dto = rankingService.getCollectionRanking();

        return new ResponseEntity<>(CollectionRankingResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "돈")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MoneyRankingResponseDto.class )))
    @GetMapping("/money")
    public ResponseEntity<MoneyRankingResponseDto> getMoneyRanking(@AuthenticationPrincipal User user){

        MoneyRankingDto dto = rankingService.getMoneyRanking();

        return new ResponseEntity<>(MoneyRankingResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "전체 게시물 좋아요")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostRankingResponseDto.class )))
    @GetMapping("/post")
    public ResponseEntity<PostRankingResponseDto> getPostRanking(@AuthenticationPrincipal User user){

        PostRankingDto dto = rankingService.getPostRanking();

        return new ResponseEntity<>(PostRankingResponseDto.fromDto(dto), HttpStatus.OK);
    }

    @Operation(summary = "레벨")
    @ApiErrorCodeExamples(value = {
            @ApiErrorCodeExample(HttpErrorCode.AccessDeniedError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.ExpiredAccessTokenError),
            @ApiErrorCodeExample(value = HttpErrorCode.NotValidRequestError)
    })
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LevelRankingResponseDto.class )))
    @GetMapping("/level")
    public ResponseEntity<LevelRankingResponseDto> getLevelRanking(@AuthenticationPrincipal User user,
                                                                   @RequestParam JobType jobType,
                                                                   @RequestParam String jobName
    ){
        LevelRankingDto dto =  rankingService.getLevelRanking(jobType, jobName);

        return new ResponseEntity<>(LevelRankingResponseDto.fromDto(dto), HttpStatus.OK);
    }
}
