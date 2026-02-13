package com.yugmehta.auth_service.service;

import com.yugmehta.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    //private final UserRepository userRepository;            //as we are calling 'findByUsername' method present in repo from security class we will autowire a bean/object of repo in this class

   /* @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));        //this calls the 'findByUsername' method in repo layer. Here 'userRepository' is the repo name. Its more better to use this method in @Service layer than in security layer
    }
    */

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (!username.equals("yug")) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername("yug")
                .password(passwordEncoder.encode("secret123")) // secret123
                .roles("USER")
                .build();
    }

}
