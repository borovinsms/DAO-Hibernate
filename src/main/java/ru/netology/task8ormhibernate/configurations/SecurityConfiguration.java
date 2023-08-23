package ru.netology.task8ormhibernate.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        final var user = User.withUsername("user")
                .password("{noop}upass")
                .authorities("user")
                .build();
        final var admin = User.withUsername("admin")
                .password("{noop}apass")
                .authorities("admin", "user")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/persons/all").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/persons/save").hasAuthority("admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST).hasAuthority("admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin")
                .and()
                .authorizeRequests().anyRequest().authenticated();
        return http.build();
    }
}