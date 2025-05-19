package com.cincinnati.loan.loanoriginationsystem.Config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/api/v0/account", "/api/v0/account/**", "/api/v0/loan/**").permitAll()
                                .anyRequest().authenticated()
                        )
                        .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
