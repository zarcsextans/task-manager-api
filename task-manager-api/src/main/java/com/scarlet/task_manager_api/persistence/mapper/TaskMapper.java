package com.scarlet.task_manager_api.persistence.mapper;

import com.scarlet.task_manager_api.domain.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    @Mapping(
            source = "project.id",
            target = "projectId"
    )
    Task toDomain(
            com.scarlet.task_manager_api.persistence.entity.Task entity
    );


    @Mapping(
            target = "project",
            ignore = true
    )
    com.scarlet.task_manager_api.persistence.entity.Task toEntity(
            Task domain
    );
}