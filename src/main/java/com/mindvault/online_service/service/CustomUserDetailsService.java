package com.mindvault.online_service.service;

import com.mindvault.online_service.repositories.UserRepository;
<<<<<<< HEAD
import com.mindvault.online_service.security.CustomUserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
>>>>>>> development
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
<<<<<<< HEAD
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.mindvault.online_service.entities.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        
        return new CustomUserDetails(user); 
=======
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email)
                );
>>>>>>> development
    }
}
