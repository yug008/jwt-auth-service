package com.yugmehta.auth_service.Configuration;

import com.yugmehta.auth_service.filter.JwtFilter;
import com.yugmehta.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    //@Autowired
    //private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{       //we use it to make our own custom SecurityFilterChain. The return type is 'SecurityFilterChain' as we are returning a new (new custom) filter chain
        http.csrf(customizer -> customizer.disable());                       //disables csrf token
        http.authorizeHttpRequests(auth -> auth.requestMatchers(org.springframework.http.HttpMethod.POST, "/login","/register").permitAll().anyRequest().authenticated());   //authentication is applied for any request. Thus all requests should come from authenticated users only
        //http.formLogin(Customizer.withDefaults());                                     //Turns the default Spring Security login system back ON.
        //http.httpBasic(Customizer.withDefaults());                            //It enables form-based login and use the default configuration. Tools like Postman prefer httpBasic
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));       //here Spring Security will NOT use HTTP sessions at all. Every request must authenticate itself. Thus for every request you have to pass the credentials for loginForm. And so everytime credentials are entered a new sessionID is generated
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);                               //its used to add JWT Filter before the Spring Security Filter Chain
        return http.build();                                                       //builds your own customized security filter chain
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManger(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }

}
