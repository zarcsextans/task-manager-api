package com.scarlet.task_manager_api.web.mapper;

import com.scarlet.task_manager_api.domain.User;
import com.scarlet.task_manager_api.web.dto.UserResponse;

import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());

        return response;
    }
}