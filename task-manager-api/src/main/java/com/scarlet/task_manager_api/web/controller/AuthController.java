package com.scarlet.task_manager_api.web.controller;

import com.scarlet.task_manager_api.service.AuthService;
import com.scarlet.task_manager_api.web.dto.LoginRequest;
import com.scarlet.task_manager_api.web.dto.LoginResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(
            AuthService authService
    ) {

        this.authService = authService;

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );

    }

}