package org.turtle.minecraft_service.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.domain.primary.user.User;
import org.turtle.minecraft_service.dto.admin.attendance.AttendanceRestoreDto;
import org.turtle.minecraft_service.dto.admin.attendance.AttendanceRestoreRequestDto;
import org.turtle.minecraft_service.dto.admin.attendance.AttendanceRestoreResponseDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.repository.primary.UserRepository;
import org.turtle.minecraft_service.service.redis.RedisService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final RedisService redisService;

    private final UserRepository userRepository;

    public AttendanceRestoreDto restoreAttendance(AttendanceRestoreRequestDto requestDto){

        List<String> userList = requestDto.getUsers();
        LocalDate targetDate = LocalDate.parse(requestDto.getTargetDate());

        List<User> usersToProcess;
        List<String> processedUsers = new ArrayList<>();

        //1. 복구할 유저 리스트 정리
        if(userList == null || userList.isEmpty()){
            usersToProcess = userRepository.findAll();
        }else {
            usersToProcess = userRepository.findAllByNicknameIn(userList);

            if(usersToProcess == null || usersToProcess.isEmpty()){
                throw new HttpErrorException(HttpErrorCode.UserNotFoundError);
            }

        }
        //2. 출석체크 복구 진행
        for (User user : usersToProcess) {
            List<String> attendanceHistory = redisService.getAttendanceHistory(user.getSnsId());

            boolean alreadyAttended = attendanceHistory.stream()
                    .map(LocalDateTime::parse)
                    .map(LocalDateTime::toLocalDate)
                    .anyMatch(date -> date.equals(targetDate));

            if (!alreadyAttended){
                redisService.restoreAttendanceHistory(user.getSnsId(), targetDate);
                processedUsers.add(user.getNickname());
            }
        }

        return AttendanceRestoreDto.of(processedUsers);
    }

}
