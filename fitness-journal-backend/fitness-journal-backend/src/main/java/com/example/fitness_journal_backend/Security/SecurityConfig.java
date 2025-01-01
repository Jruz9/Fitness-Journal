package com.example.fitness_journal_backend.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


//get rid of the login screen associated with spring 6 and starter security dependency.
// refrence:https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
//https://www.reddit.com/r/SpringBoot/comments/16l0umn/how_to_disable_spring_security_61/
// https://docs.spring.io/spring-boot/docs/1.4.0.RELEASE/reference/htmlsingle/#boot-features-security
@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.
                authorizeHttpRequests((auth)->auth.anyRequest()
                        .permitAll())
                .build();
    }
}
