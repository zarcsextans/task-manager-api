package com.scarlet.task_manager_api.persistence.mapper;

import com.scarlet.task_manager_api.domain.User;
import com.scarlet.task_manager_api.persistence.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(Usuario entity);

    Usuario toEntity(User domain);

}