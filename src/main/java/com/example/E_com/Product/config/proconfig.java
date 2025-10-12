//package com.example.E_com.Product.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//
//@Configuration
//public class proconfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // already disabled
//                .cors(cors -> cors.configurationSource(request -> {
//                    var corsConfig = new CorsConfiguration();
//                    corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
//                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//                    corsConfig.setAllowedHeaders(List.of("*"));
//                    return corsConfig;
//                }))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/**").permitAll() // allow all API endpoints
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//}
