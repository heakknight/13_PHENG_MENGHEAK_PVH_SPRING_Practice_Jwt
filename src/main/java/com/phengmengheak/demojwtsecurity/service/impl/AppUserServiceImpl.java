package com.phengmengheak.demojwtsecurity.service.impl;

import com.phengmengheak.demojwtsecurity.model.entity.AppUser;
import com.phengmengheak.demojwtsecurity.model.request.AppUserRequest;
import com.phengmengheak.demojwtsecurity.model.response.AppUserResponse;
import com.phengmengheak.demojwtsecurity.repository.AppUserRepository;
import com.phengmengheak.demojwtsecurity.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NullMarked
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.getUserByEmail(email);
    }
}
