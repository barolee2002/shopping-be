package com.example.taskmanager.config.security;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.AppException;
import com.example.taskmanager.exception.Errors;
import com.example.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if( userOptional.isPresent())
            return new org.springframework.security.core.userdetails.User(username, userOptional.get().getPassword(), new ArrayList<>());
        else
            throw new AppException(Errors.USER_NOT_FOUND);
    }
}