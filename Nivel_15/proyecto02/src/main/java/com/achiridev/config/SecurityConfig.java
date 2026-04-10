package com.achiridev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})

            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/api/user/**")
                    .hasAnyRole("USER", "MODERATOR", "ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/mod/productos/**")
                    .hasAuthority("CREATE_PRODUCT")
                .requestMatchers(HttpMethod.PUT, "/api/mod/productos/**")
                    .hasAuthority("UPDATE_PRODUCT")
                .requestMatchers(HttpMethod.DELETE, "/api/admin/productos/**")
                    .hasAuthority("DELETE_PRODUCT")

                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()

            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
