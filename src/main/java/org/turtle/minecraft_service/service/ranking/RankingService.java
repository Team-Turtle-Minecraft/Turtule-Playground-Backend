package org.turtle.minecraft_service.service.ranking;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.BossClearType;
import org.turtle.minecraft_service.constant.JobType;
import org.turtle.minecraft_service.domain.secondary.boss.FirstPartyBossClearLog;
import org.turtle.minecraft_service.domain.secondary.boss.FirstSoloBossClearLog;
import org.turtle.minecraft_service.domain.secondary.boss.PartyBossClearLog;
import org.turtle.minecraft_service.domain.secondary.boss.SoloBossClearLog;
import org.turtle.minecraft_service.dto.ranking.boss.BossClearRankingDto;
import org.turtle.minecraft_service.dto.ranking.boss.party.PartyBossClearRankingDto;
import org.turtle.minecraft_service.dto.ranking.boss.solo.SoloBossClearRankingDto;
import org.turtle.minecraft_service.dto.ranking.collection.CollectionRankingDto;
import org.turtle.minecraft_service.dto.ranking.level.LevelRankingDto;
import org.turtle.minecraft_service.dto.ranking.money.MoneyRankingDto;
import org.turtle.minecraft_service.dto.ranking.post.PostRankingDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.repository.primary.PostRepository;
import org.turtle.minecraft_service.repository.secondary.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RankingService {

    @Value("${file.upload.image.api}")
    private String postImageApiUrlPrefix;

    private final MinecraftRepository minecraftRepository;
    private final PostRepository postRepository;
    private final FirstSoloBossClearLogRepository firstSoloBossClearLogRepository;
    private final FirstPartyBossClearLogRepository firstPartyBossClearLogRepository;
    private final SoloBossClearLogRepository soloBossClearLogRepository;
    private final PartyBossClearLogRepository partyBossClearLogRepository;

    public CollectionRankingDto getCollectionRanking(){

        return CollectionRankingDto.from(minecraftRepository.findTop5UserWithCollection());

    }

    public MoneyRankingDto getMoneyRanking(){

        return MoneyRankingDto.from(minecraftRepository.findTop5UserWithMoney());

    }

    public PostRankingDto getPostRanking(){

        return PostRankingDto.of(postImageApiUrlPrefix, postRepository.findTop5PostsByLikes());

    }

    public LevelRankingDto getLevelRanking(JobType jobType, String jobName) {

        return switch (jobType) {
            case Combat ->
                 LevelRankingDto.fromCombatJobRankers(jobName,minecraftRepository.findTop5UserWithCombatJobLevel(jobName));

            case Living -> {
                yield switch (jobName) {
                    case "FISHERMAN" -> LevelRankingDto.fromFishermanRankers(jobName, minecraftRepository.findTop5UserWithFishingLevel());
                    case "MINER" -> LevelRankingDto.fromMinerRankers(jobName, minecraftRepository.findTop5UserWithMiningLevel());
                    case "FARMER" -> LevelRankingDto.fromFarmerRanking(jobName, minecraftRepository.findTop5UserWithFarmingLevel());
                    case "COOK" -> LevelRankingDto.fromCookRanking(jobName, minecraftRepository.findTop5UserWithCookingLevel());
                    case "BLACKSMITH" -> LevelRankingDto.fromBlacksmithRanking(jobName, minecraftRepository.findTop5UserWithSmithingLevel());
                    default -> throw new HttpErrorException(HttpErrorCode.NotValidRequestError);
                };

            }

        };

    }

    public BossClearRankingDto getBossClearRanking(BossClearType bossClearType, String bossName){

        return switch (bossClearType){
            case Solo -> BossClearRankingDto.from(getSoloBossClearRankers(bossName));
            case Party -> BossClearRankingDto.from(getPartyBossClearRankers(bossName));
        };
    }


    private SoloBossClearRankingDto getSoloBossClearRankers(String bossName){

        FirstSoloBossClearLog firstBossClearUser = firstSoloBossClearLogRepository.findByBossName(bossName)
                .orElseThrow(()-> new HttpErrorException(HttpErrorCode.FirstSoloBossClearLogNotFoundError));

        List<SoloBossClearLog> soloBossClearTimeRankers = soloBossClearLogRepository.findByBossNameAndRankPosition(bossName);

        return SoloBossClearRankingDto.of(bossName, firstBossClearUser, soloBossClearTimeRankers);

    }

    private PartyBossClearRankingDto getPartyBossClearRankers(String bossName){

        FirstPartyBossClearLog firstBossClearParty = firstPartyBossClearLogRepository.findByBossName(bossName)
                .orElseThrow(()-> new HttpErrorException(HttpErrorCode.FirstPartyBossClearLogNotFoundError));

        List<PartyBossClearLog> partyBossClearTimeRankers = partyBossClearLogRepository.findByBossNameAndRankPosition(bossName);

        return PartyBossClearRankingDto.of(bossName, firstBossClearParty, partyBossClearTimeRankers);
    }

}


