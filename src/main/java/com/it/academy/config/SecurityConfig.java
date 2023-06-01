package com.it.academy.config;

import com.it.academy.security.CustomOAuth2UserService;
import com.it.academy.security.DetailsUserService;
import com.it.academy.security.JWTFilter;
import com.it.academy.security.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTFilter jwtFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Value("${logoutSuccessUrl}")
    private String logoutSuccessUrl;

    @Bean
    public UserDetailsService userDetailsService() {
        return new DetailsUserService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                    .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                    .and()
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**", "/oauth2/**", "/password/**", "/swagger-ui/**",
                        "/v3/api-docs/**", "/review/count/**", "/user/count/**", "/course/count/**", "/s3/download/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/auth/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2LoginSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(logoutSuccessUrl)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
