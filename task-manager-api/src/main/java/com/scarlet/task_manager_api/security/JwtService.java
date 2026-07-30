package com.scarlet.task_manager_api.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtService {


    private final String SECRET_KEY =
            "scarlet-task-manager-secret-key-scarlet-task-manager";


    public String generateToken(String username){


        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                        )
                )
                .compact();
    }



    public String extractUsername(String token){


        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                        )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
}