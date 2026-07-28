package com.scarlet.task_manager_api.persistence.mapper;

import com.scarlet.task_manager_api.domain.Category;
import com.scarlet.task_manager_api.persistence.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toDomain(Categoria entity);

    Categoria toEntity(Category domain);

}