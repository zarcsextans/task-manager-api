package com.scarlet.task_manager_api.security;


import com.scarlet.task_manager_api.persistence.entity.Usuario;
import com.scarlet.task_manager_api.persistence.repository.UserRepository;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;



    public CustomUserDetailsService(
            UserRepository userRepository
    ){

        this.userRepository = userRepository;

    }



    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {


        Usuario usuario = userRepository.findByUsername(username)

                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"
                        )
                );


        return User.builder()

                .username(usuario.getUsername())

                .password(usuario.getPassword())

                .roles("USER")

                .build();

    }

}