package org.turtle.minecraft_service.service.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.domain.secondary.MinecraftUser;
import org.turtle.minecraft_service.dto.user.attendance.*;
import org.turtle.minecraft_service.dto.user.inquiry.UserInfoInquiryDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.repository.secondary.MinecraftRepository;
import org.turtle.minecraft_service.service.redis.RedisService;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final MinecraftRepository minecraftRepository;

    private final RedisService redisService;
    private final MinecraftUserService minecraftUserService;

    public UserInfoInquiryDto getUserInfo(User user){

        MinecraftUser minecraftUser = validateExistUserInTurtlePlayGround(user.getNickname());

        return UserInfoInquiryDto.of(user, minecraftUser);
    }

    public UserAttendanceDto checkAttendance(User user){

        MinecraftUser minecraftUser = validateExistUserInTurtlePlayGround(user.getNickname());

        if(minecraftUser.getCurrentStatus().equals("OFFLINE")){
            throw new HttpErrorException(HttpErrorCode.PlayerNotLoggedInError);
        }

        if(redisService.hasAttendanceToday(minecraftUser.getPlayerName())) {
            throw new HttpErrorException(HttpErrorCode.AlreadyCheckedInError);
        }

        String response = minecraftUserService.giveRewardToUser(minecraftUser.getPlayerName());
        redisService.saveAttendance(minecraftUser.getPlayerName());
        redisService.saveAttendanceHistory(user.getSnsId());
        return UserAttendanceDto.from(response);

    }

    public UserHalfAttendanceRewardDto getHalfAttendanceReward(User user){

        MinecraftUser minecraftUser = validateExistUserInTurtlePlayGround(user.getNickname());

        if(minecraftUser.getCurrentStatus().equals("OFFLINE")){
            throw new HttpErrorException(HttpErrorCode.PlayerNotLoggedInError);
        }


        String response = minecraftUserService.giveHalfAttendanceRewardToUser(user.getNickname());
        redisService.saveHalfAttendanceRewardHistory(user.getSnsId());
        return UserHalfAttendanceRewardDto.from(response);

    }

    public UserFullAttendanceRewardDto getFullAttendanceReward(User user){

        MinecraftUser minecraftUser = validateExistUserInTurtlePlayGround(user.getNickname());

        if(minecraftUser.getCurrentStatus().equals("OFFLINE")){
            throw new HttpErrorException(HttpErrorCode.PlayerNotLoggedInError);
        }


        String response = minecraftUserService.giveFullAttendanceRewardToUser(user.getNickname());
        redisService.saveFullAttendanceRewardHistory(user.getSnsId());
        return UserFullAttendanceRewardDto.from(response);
    }


    public UserAttendanceHistoryDto getAttendanceHistory(User user){

        List<String> attendanceHistory = redisService.getAttendanceHistory(user.getSnsId());
        long attendanceCount = redisService.getAttendanceCount(user.getSnsId());

        return UserAttendanceHistoryDto.of(attendanceCount, attendanceHistory);
    }

    public UserAttendanceRewardHistoryDto getAttendanceRewardHistory(User user){
        boolean halfAttendanceRewardStatus = redisService.hasReceivedHalfAttendanceReward(user.getSnsId());
        boolean fullAttendanceRewardStatus = redisService.hasReceivedFullAttendanceReward(user.getSnsId());

        return UserAttendanceRewardHistoryDto.of(halfAttendanceRewardStatus, fullAttendanceRewardStatus);
    }



    // 검증 메서드
    public MinecraftUser validateExistUserInTurtlePlayGround(String nickname) {
        return minecraftRepository.findByPlayerName(nickname);
    }
}