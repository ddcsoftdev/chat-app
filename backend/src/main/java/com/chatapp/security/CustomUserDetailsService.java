package com.chatapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    //@Autowired
    //UserRepository
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO: fetch user by email and return as user details
        return null;
    }
}
