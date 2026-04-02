package com.phengmengheak.demojwtsecurity.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@SecurityRequirement(name="bearerAuth")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @GetMapping
    public String getAllUsers(){
        return  "User returned!";
    }
}
