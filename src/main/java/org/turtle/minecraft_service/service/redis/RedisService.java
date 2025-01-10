package org.turtle.minecraft_service.service.redis;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.exception.HttpErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    // JWT
    public void save(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public Optional<String> get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return Optional.ofNullable(valueOperations.get(key));
    }

    public void delete(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String deletedData = valueOperations.getAndDelete(key);
        if (deletedData == null) {
            throw new HttpErrorException(HttpErrorCode.InternalServerError);
        }
    }

    // Post Views
    public void savePostView(Long postId, String snsId) {
        String key = generatePostViewKey(postId, snsId);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "1", 3 * 60, TimeUnit.SECONDS);
    }

    public boolean hasPostView(Long postId, String snsId) {
        String key = generatePostViewKey(postId, snsId);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key) != null;
    }

    private String generatePostViewKey(Long postId, String snsId) {
        return "post:view" + postId + ":" + snsId;
    }

    // User Attendance
    public void saveAttendance(String nickname) {
        String key = generateAttendanceKey(nickname);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, LocalDate.now().toString(), getTimeOut(), TimeUnit.SECONDS);
    }

    public boolean hasAttendanceToday(String nickname) {
        String key = generateAttendanceKey(nickname);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        return value != null && value.equals(LocalDate.now().toString());
    }

    private String generateAttendanceKey(String nickname) {
        return "attendance:" + nickname;
    }

    // 다음날 오전 6시까지 남은 시간(초) 계산
    private long getTimeOut() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextSixAM = now.with(LocalTime.of(6, 0));

        // 현재 시각이 오전 6시 이후라면 다음날 오전 6시로 설정
        if (now.getHour() >= 6) {
            nextSixAM = nextSixAM.plusDays(1);
        }

        return ChronoUnit.SECONDS.between(now, nextSixAM);
    }
}
