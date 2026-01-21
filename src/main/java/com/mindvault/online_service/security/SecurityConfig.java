package com.mindvault.online_service.security;

<<<<<<< HEAD
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
=======
import com.mindvault.online_service.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
>>>>>>> development
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
<<<<<<< HEAD
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
=======
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
>>>>>>> development
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< HEAD
import com.mindvault.online_service.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity // 🛡️ Important: This enables @PreAuthorize in your Controller
@OpenAPIDefinition(info = @Info(title = "Online Service API", version = "v1"))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
=======

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;

    // ✅ AuthenticationManager Bean
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
>>>>>>> development
    }

    // ✅ Security Filter Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
<<<<<<< HEAD
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Allow Auth and Swagger/OpenAPI
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(
                    "/v3/api-docs/**",     // This wildcard fix the 403 in your screenshot
                    "/swagger-ui/**", 
                    "/swagger-ui.html",
                    "/webjars/**"
                ).permitAll()
                
                // Categories Permissions
                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                .requestMatchers("/api/categories/**").hasRole("ADMIN") // RoleEnum.ADMIN match
                
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                
=======
            .sessionManagement(sm -> sm
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT: stateless
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()
                
                // Admin-only endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                
                // All other endpoints need authentication
                .anyRequest().authenticated()
            )
            // 🔥 Add JWT filter BEFORE Spring Security default
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

>>>>>>> development
        return http.build();
    }

    // Modern way to provide AuthenticationManager in Spring Boot 3.x
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}