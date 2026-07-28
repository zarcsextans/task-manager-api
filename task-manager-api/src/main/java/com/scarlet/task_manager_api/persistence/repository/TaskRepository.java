package com.scarlet.task_manager_api.persistence.repository;

import com.scarlet.task_manager_api.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}