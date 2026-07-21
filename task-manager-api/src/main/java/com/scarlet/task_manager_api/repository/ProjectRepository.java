package com.scarlet.task_manager_api.repository;

import com.scarlet.task_manager_api.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}