package com.team14.sogeun.config;
/*
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.team14.sogeun.auth.MyAccessDeniedHandler;
import com.team14.sogeun.auth.MyAuthenticationEntryPoint;
import com.team14.sogeun.auth.oauth.PrincipalOauth2UserService;
import com.team14.sogeun.domain.UserRole;
import org.springframework.security.web.SecurityFilterChain;
*/
//Form Login에 사용
/*
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity.RequestMatcherConfigurer.disable();
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/member/join").permitAll()
                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
*/