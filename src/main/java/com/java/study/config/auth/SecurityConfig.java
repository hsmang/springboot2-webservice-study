package com.java.study.config.auth;

import com.java.study.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UsrService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 함
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있음
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // 설정된 이외 값들은 인증절차 필요
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // Oauth2 로그인 기능에 대한 여러 설정 시작점
                        .userInfoEndpoint() // Oauth2로그인 성공 이후 사용자 정보를 가져올때 설정을 담당
                            .userService(customOAuth2UsrService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록함

        super.configure(http);
    }
}
