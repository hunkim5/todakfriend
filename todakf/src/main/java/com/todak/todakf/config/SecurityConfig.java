package com.todak.todakf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.todak.todakf.security.CustomLoginSuccessHandler;
import com.todak.todakf.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final CustomUserDetailsService userDetailsService;

	private final CustomLoginSuccessHandler customLoginSuccessHandler;

    public SecurityConfig(CustomUserDetailsService userDetailsService
    		,CustomLoginSuccessHandler customLoginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.customLoginSuccessHandler = customLoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf(csrf -> csrf.disable()) // CSRF 비활성화
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/user/registerForm","/user/register", "/login","/common/**"
                		, "/css/**","/js/**","/summernote/**","/web/permit/**","/upload/**","/assets/**","/img/**"
                		,"index","/test").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")// 관리자만
                .requestMatchers("/common/**").hasAnyRole("USER", "ADMIN","LEADER")
                .anyRequest().authenticated()
            )
//        	.authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll() // 모든 요청 허용
//             )
        	.formLogin(form -> form
                .loginPage("/user/login") // 사용자 정의 로그인 페이지
                .loginProcessingUrl("/login") // POST 처리 경로
                .successHandler(customLoginSuccessHandler)
                //.defaultSuccessUrl("/admin/board/list", true)
                .failureUrl("/user/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true)
            ).sessionManagement(session -> session
            		.maximumSessions(1) // 하나의 세션만 유지
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
