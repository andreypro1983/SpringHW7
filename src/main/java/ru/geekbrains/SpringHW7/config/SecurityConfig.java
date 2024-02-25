package ru.geekbrains.SpringHW7.config;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Переопределение цепочки фильтра аутентификации
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/private-data").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.defaultSuccessUrl("/public-data").permitAll())
                .exceptionHandling((ht) -> {
                    ht.accessDeniedPage("/");
                })
                .logout(login -> login.logoutSuccessUrl("/"));

        return http.build();

    }

    // Переопределение менеджера хранения пользователей
    @Bean
    UserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.withUsername("user").password("{noop}password").roles("USER").build();
        UserDetails user2 = User.withUsername("admin").password("{noop}password").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    // Переопределение кодировщика паролей
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
