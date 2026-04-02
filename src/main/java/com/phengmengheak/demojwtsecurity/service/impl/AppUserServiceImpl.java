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

import java.util.List;

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

    @Override
    public AppUserResponse registerUser(AppUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword); // replace plain text with the hash

        // 2. Save the user to the database (your existing method)
        AppUser savedUser = appUserRepository.register(request);

        // 3. Assign a default role to the new user so they aren't locked out
        appUserRepository.assignRoleToUser(savedUser.getUserId(), "USER");

        // 4. Return the response (without exposing the password!)
        AppUserResponse response = new AppUserResponse();
        response.setId(Math.toIntExact(savedUser.getUserId()));
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setRoles(List.of("USER"));

        return response;
    }
}
