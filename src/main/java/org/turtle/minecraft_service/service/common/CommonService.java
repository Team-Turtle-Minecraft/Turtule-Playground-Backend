package org.turtle.minecraft_service.service.common;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.dto.common.UserCountDto;
import org.turtle.minecraft_service.repository.primary.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonService {

    private final UserRepository userRepository;

    public UserCountDto getCountOfUsers(){
        List<String> gmPlayerNames = Arrays.asList("_appli_", "Koo_pa_", "_YYH_", "KSH_1348", "kkOma_fan", "uiu3111");

        int countOfUsers = userRepository.findAllUsersWithoutGM(gmPlayerNames);

        return UserCountDto.from(countOfUsers);

    }
}
