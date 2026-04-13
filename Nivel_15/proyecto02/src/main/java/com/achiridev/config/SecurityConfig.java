package com.achiridev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  
import org.springframework.security.crypto.password.PasswordEncoder;
import com.achiridev.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean  
    public PasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder();  
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})

            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/").permitAll()
                .requestMatchers("/index.html").permitAll()
                .requestMatchers("/styles.css").permitAll()
                .requestMatchers("/app.js").permitAll()

                .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                .requestMatchers(HttpMethod.DELETE, "/api/admin/**")
                    .access((authentication, context) -> {
                        boolean hasRole = authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                        boolean hasPermiso = authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("DELETE_PRODUCT"));
                        return new AuthorizationDecision(hasRole && hasPermiso);
                    })

                .requestMatchers(HttpMethod.POST, "/api/mod/**")
                    .access((authentication, context) -> {
                        boolean hasRole = authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_MODERATOR") || a.getAuthority().equals("ROLE_ADMIN"));
                        boolean hasPermiso = authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("CREATE_PRODUCT"));
                        return new AuthorizationDecision(hasRole && hasPermiso);
                    })

                .requestMatchers(HttpMethod.PUT, "/api/mod/**")
                    .access((authentication, context) -> {
                        boolean hasRole = authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_MODERATOR") || a.getAuthority().equals("ROLE_ADMIN"));
                        boolean hasPermiso = authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("UPDATE_PRODUCT"));
                        return new AuthorizationDecision(hasRole && hasPermiso);
                    })

                .requestMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("USER", "MODERATOR", "ADMIN")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}