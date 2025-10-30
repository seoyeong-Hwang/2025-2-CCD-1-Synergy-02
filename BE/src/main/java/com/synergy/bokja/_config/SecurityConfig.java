// JWT 없는 버전의 코드
// 모든 URL 접근 허용, 로그인창 제거, CSRF 비활성화를 수행하는 코드
package com.synergy.bokja._config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/users/me/reports").permitAll() // 모든 요청 허용
                )
                .formLogin(form -> form.disable()) // 로그인 폼 비활성화
                .httpBasic(basic -> basic.disable()); // Basic Auth 비활성화
        return http.build();
    }
}