package com.phengmengheak.demojwtsecurity.service;

import com.phengmengheak.demojwtsecurity.model.request.AppUserRequest;
import com.phengmengheak.demojwtsecurity.model.response.AppUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserResponse registerUser(AppUserRequest request);
}
