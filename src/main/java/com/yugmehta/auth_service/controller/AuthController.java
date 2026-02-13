package com.yugmehta.auth_service.controller;

import com.yugmehta.auth_service.model.LoginRequest;
import com.yugmehta.auth_service.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Controller class for Model - 'Login'
@RestController
public class AuthController {                    //you can use either 'LoginController' / 'AuthController'

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager , JwtService jwtService) {         //constructor
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));     //the username and password already gets authenticated using UsersDetailsService and PasswordEncoder but as AuthenticationManager implicitly calls them both we will authenticate the username and password using AuthenticationManager also
        return jwtService.generateToken(request.getUsername());                    //we will call the JWTToken generating method present in JWTService class in the AuthController/LoginController class
    }

    @GetMapping("/hello")
    public String greet(){
        return "hello";
    }
}
