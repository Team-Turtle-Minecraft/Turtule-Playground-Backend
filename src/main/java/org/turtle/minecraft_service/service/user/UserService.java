package org.turtle.minecraft_service.service.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.domain.secondary.MinecraftUser;
import org.turtle.minecraft_service.dto.user.inquiry.UserInfoInquiryDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.repository.secondary.MinecraftRepository;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final MinecraftRepository minecraftRepository;

    public UserInfoInquiryDto getUserInfo(User user){

        MinecraftUser minecraftUser =  minecraftRepository.findByPlayerName(user.getNickname())
                .orElseThrow(() -> new HttpErrorException(HttpErrorCode.UserNotFoundError));

        log.info("가져온 유저 정보:{}", minecraftUser.getPlayerName());

        return UserInfoInquiryDto.of(user, minecraftUser);
    }


}