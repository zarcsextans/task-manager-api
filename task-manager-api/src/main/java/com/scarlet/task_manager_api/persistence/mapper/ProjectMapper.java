package com.scarlet.task_manager_api.persistence.mapper;

import com.scarlet.task_manager_api.domain.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {


    @Mapping(
            target = "userId",
            source = "usuario.id"
    )
    Project toDomain(
            com.scarlet.task_manager_api.persistence.entity.Project entity
    );


    @Mapping(
            target = "usuario",
            ignore = true
    )
    com.scarlet.task_manager_api.persistence.entity.Project toEntity(
            Project domain
    );

}