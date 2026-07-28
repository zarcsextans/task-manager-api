package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.domain.Task;
import com.scarlet.task_manager_api.persistence.entity.Project;
import com.scarlet.task_manager_api.persistence.mapper.TaskMapper;
import com.scarlet.task_manager_api.persistence.repository.ProjectRepository;
import com.scarlet.task_manager_api.persistence.repository.TaskRepository;
import com.scarlet.task_manager_api.persistence.entity.Categoria;
import com.scarlet.task_manager_api.persistence.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;


    public TaskService(
            TaskRepository taskRepository,
            TaskMapper taskMapper,
            ProjectRepository projectRepository,
            CategoryRepository categoryRepository
    ) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<Task> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDomain)
                .collect(Collectors.toList());
    }


    public Optional<Task> getTaskById(Integer id) {

        return taskRepository.findById(id)
                .map(taskMapper::toDomain);
    }


    public Task createTask(Task task) {

        if (task.getProjectId() == null) {
            throw new RuntimeException("Project ID is required");
        }

        if (task.getCategoryId() == null) {
            throw new RuntimeException("Category ID is required");
        }


        Project project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() ->
                        new RuntimeException("Project not found")
                );


        Categoria categoria = categoryRepository.findById(task.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found")
                );


        var entity = taskMapper.toEntity(task);


        entity.setProject(project);
        entity.setCategoria(categoria);


        var savedEntity = taskRepository.save(entity);


        return taskMapper.toDomain(savedEntity);
    }


    public Task updateTask(Integer id, Task taskDetails) {

        var entity = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found")
                );


        entity.setTitle(taskDetails.getTitle());
        entity.setDescription(taskDetails.getDescription());
        entity.setPriority(taskDetails.getPriority());
        entity.setCompleted(taskDetails.getCompleted());


        if(taskDetails.getProjectId() != null){

            Project project = projectRepository.findById(taskDetails.getProjectId())
                    .orElseThrow(() ->
                            new RuntimeException("Project not found")
                    );

            entity.setProject(project);
        }


        if(taskDetails.getCategoryId() != null){

            Categoria categoria = categoryRepository.findById(taskDetails.getCategoryId())
                    .orElseThrow(() ->
                            new RuntimeException("Category not found")
                    );

            entity.setCategoria(categoria);
        }


        var updatedEntity = taskRepository.save(entity);


        return taskMapper.toDomain(updatedEntity);
    }


    public void deleteTask(Integer id) {


        var entity = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found")
                );


        taskRepository.delete(entity);
    }
}