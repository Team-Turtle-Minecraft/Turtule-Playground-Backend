package org.turtle.minecraft_service.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.constant.TokenType;
import org.turtle.minecraft_service.exception.ErrorResponseDto;
import org.turtle.minecraft_service.exception.HttpErrorException;
import org.turtle.minecraft_service.provider.JwtTokenProvider;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        try {
            String accessToken = request.getHeader("Authorization");
            if(accessToken == null){
                throw new HttpErrorException(HttpErrorCode.AccessDeniedError);
            }

            String resolvedAccessToken = jwtTokenProvider.resolveAccessToken(accessToken);
            jwtTokenProvider.validateToken(TokenType.ACCESS_TOKEN, resolvedAccessToken);

            Authentication authentication = jwtTokenProvider.getAuthentication(resolvedAccessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            jwtExceptionHandler(response, e);
        }
    }

    // 토큰에 대한 오류가 발생했을 때, 커스터마이징해서 Exception 처리 값을 클라이언트에게 알려준다.
    public void jwtExceptionHandler(HttpServletResponse response, Exception e) {
        HttpErrorCode httpErrorCode = HttpErrorCode.InternalServerError;
        if (e instanceof HttpErrorException) {
            httpErrorCode = ((HttpErrorException) e).getHttpErrorCode();
        }

        response.setStatus(httpErrorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ErrorResponseDto.from(httpErrorCode));
            response.getWriter().write(json);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
