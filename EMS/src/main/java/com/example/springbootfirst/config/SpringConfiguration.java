package com.example.springbootfirst.config;

import com.example.springbootfirst.services.CustomUserDetailsService;
import com.example.springbootfirst.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringConfiguration {

  @Autowired
  RegisterService registerService;

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());  // This is required for Postman Basic Auth
    return http.build();
  }




//  @Bean
//  public UserDetailsService userDetailsService() {
//    return new InMemoryUserDetailsManager(); // Empty = no default user
//  }

//  @Bean
//  UserDetailsService userDetailsService(){
//    UserDetails admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build();
//    UserDetails ksp = User.builder()
//            .username("ksp")
//            .password(passwordEncoder().encode("ksp@1009"))
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(admin,ksp);
//  }
}
