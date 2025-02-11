package org.turtle.minecraft_service.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileChecker {

    private final Environment environment;

    public boolean checkActiveProfile(String profile) {
        for (String activeProfile : environment.getActiveProfiles()) {
            if (activeProfile.equals(profile)) {
                return true;
            }
        }

        return false;
    }
}
