package kr.ac.gachon.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
// 전역적으로 사용할 component?
// 잘 몰라도 되고, 어떻게 쓰는지만 알아도 된다.
public class SecurityConfig {
    // Spring Security 5.7.0-M2 부터 기존 WebSecurityConfigureAdapter 방식에서 SecurityFilterChain 으로 변경 권장
    // 어디서든 SecurityFilterChain이 돈다. spring security 사용 시 무조건 사용  -> 고정적으로 들어간다고 생각하면 됨
    // login과 직접적으로 관련된 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인증이 필요한 요청폼 설정
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .usernameParameter("address")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
        ;

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()    // 이거는 source 접근 권한이고
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()  // 이거는 view 접근 권한인가?
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;

        //인증 실패시 라우팅
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
