package com.example.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.UserEntryRepo;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntryRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userRepo.findByUsername(username);

        if (user != null) {
            return null;
        }
        
        return null; 
    }

}
