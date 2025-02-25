package org.turtle.minecraft_service.service.common;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;
import org.turtle.minecraft_service.dto.common.UserCountDto;
import org.turtle.minecraft_service.repository.secondary.MinecraftRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonService {

    private final MinecraftRepository minecraftRepository;

    public UserCountDto getCountOfUsers(){
        List<String> gmPlayerNames = Arrays.asList("_appli_", "Koo_pa_", "_YYH_", "KSH_1348", "kkOma_fan");

        int countOfUsers = minecraftRepository.findAllUsersWithoutGM(gmPlayerNames);

        return UserCountDto.from(countOfUsers);

    }
}
