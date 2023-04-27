package com.devkor.study.configuration;

import com.devkor.study.authentication.AuthService;
import com.devkor.study.authentication.JwtAuthenticationFilter;
import com.devkor.study.authentication.google.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService; //google userservice
    private final AuthService authService;

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                    .requestMatchers("/auth/login","/auth/sign-up","/users","/","/posts","/products/**").permitAll()
                    //.requestMatchers("/products").authenticated()
                .and().addFilterBefore(new JwtAuthenticationFilter(authService,secretKey) , UsernamePasswordAuthenticationFilter.class);

                //.and()
                //.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

//
//                .authorizeHttpRequests((authorization) -> authorization.requestMatchers("/login","/sign-up").permitAll()
//                        .requestMatchers("")

        return http.build();
    }
}
