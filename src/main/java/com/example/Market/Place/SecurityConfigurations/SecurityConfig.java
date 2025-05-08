package com.example.Market.Place.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all endpoints (for dev/testing)
                )
                .formLogin(form -> form.disable()) // Disable form login
                .httpBasic(basic -> basic.disable()); // Optional: disable basic auth

        return http.build();
    }
}
