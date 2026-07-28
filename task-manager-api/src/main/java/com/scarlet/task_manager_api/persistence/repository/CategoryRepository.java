package com.scarlet.task_manager_api.persistence.repository;

import com.scarlet.task_manager_api.persistence.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categoria, Integer> {

}