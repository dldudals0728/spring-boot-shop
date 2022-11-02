package kr.ac.gachon.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// 전역적으로 사용할 component?
// 잘 몰라도 되고, 어떻게 쓰는지만 알아도 된다.
public class SecurityConfig {
    // Spring Security 5.7.0-M2 부터 기존 WebSecurityConfigureAdapter 방식에서 SecurityFilterChain 으로 변경 권장
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
