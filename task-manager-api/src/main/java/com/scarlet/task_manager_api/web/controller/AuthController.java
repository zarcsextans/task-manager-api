package com.scarlet.task_manager_api.web.controller;


import com.scarlet.task_manager_api.security.JwtService;
import com.scarlet.task_manager_api.web.dto.LoginRequest;
import com.scarlet.task_manager_api.web.dto.LoginResponse;


import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {



    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;



    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ){

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }




    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ){


        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )

        );



        String token =
                jwtService.generateToken(
                        request.getUsername()
                );



        return ResponseEntity.ok(
                new LoginResponse(token)
        );

    }

}