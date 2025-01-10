package org.turtle.minecraft_service.service.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.domain.secondary.MinecraftUser;
import org.turtle.minecraft_service.dto.user.attendance.UserAttendanceDto;
import org.turtle.minecraft_service.dto.user.inquiry.UserInfoInquiryDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.repository.secondary.MinecraftRepository;
import org.turtle.minecraft_service.service.redis.RedisService;

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
        return UserAttendanceDto.from(response);

    }

    // 검증 메서드
    public MinecraftUser validateExistUserInTurtlePlayGround(String nickname) {
        return minecraftRepository.findByPlayerName(nickname);
    }
}