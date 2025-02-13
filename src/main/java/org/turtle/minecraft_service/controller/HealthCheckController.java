package org.turtle.minecraft_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@Tag(name = "AWS 로드벨런서 health 체크")
public class HealthCheckController {

    @GetMapping()
    public ResponseEntity<Void> getHealthStatus(){
        return new  ResponseEntity<>(HttpStatus.OK);
    }
}
