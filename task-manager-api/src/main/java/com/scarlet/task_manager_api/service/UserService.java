package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.domain.User;
import com.scarlet.task_manager_api.persistence.entity.Usuario;
import com.scarlet.task_manager_api.persistence.mapper.UserMapper;
import com.scarlet.task_manager_api.persistence.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Integer id) {

        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    public User createUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Usuario entity = userMapper.toEntity(user);

        Usuario savedEntity = userRepository.save(entity);

        return userMapper.toDomain(savedEntity);
    }

    public User updateUser(Integer id, User userDetails) {

        Usuario entity = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        if (!entity.getUsername().equals(userDetails.getUsername())
                && userRepository.existsByUsername(userDetails.getUsername())) {

            throw new RuntimeException("Username already exists");
        }

        entity.setUsername(userDetails.getUsername());
        entity.setPassword(userDetails.getPassword());

        Usuario updatedEntity = userRepository.save(entity);

        return userMapper.toDomain(updatedEntity);
    }

    public void deleteUser(Integer id) {

        Usuario entity = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        userRepository.delete(entity);
    }

}