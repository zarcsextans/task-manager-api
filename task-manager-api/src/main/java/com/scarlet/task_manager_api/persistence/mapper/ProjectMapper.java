package com.scarlet.task_manager_api.persistence.mapper;

import com.scarlet.task_manager_api.domain.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    Project toDomain(
            com.scarlet.task_manager_api.persistence.entity.Project entity
    );


    com.scarlet.task_manager_api.persistence.entity.Project toEntity(
            Project domain
    );
}