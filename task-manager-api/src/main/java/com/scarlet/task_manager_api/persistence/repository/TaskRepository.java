package com.scarlet.task_manager_api.persistence.repository;

import com.scarlet.task_manager_api.persistence.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}