package org.turtle.minecraft_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.turtle.minecraft_service.filter.JwtAuthenticationFilter;
import org.turtle.minecraft_service.provider.JwtTokenProvider;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())  // 변경된 부분
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/**","/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "https://turtle-playground.kr"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                        "/api/health",
                        "/api/common/**",
                        "/api/auth/login",
                        "/api/auth/check/nickname",
                        "/api/auth/logout",
                        "/api/auth/refreshToken",
                        "/api/auth/signup",
                        "/h2-console/**",
                        "/images/**",
                        "/swagger-ui/**",
                        "/v3/**")
                .requestMatchers(HttpMethod.OPTIONS, "/**"); // OPTIONS 요청 추가
    }
}
