package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.domain.Task;
import com.scarlet.task_manager_api.persistence.entity.Project;
import com.scarlet.task_manager_api.persistence.mapper.TaskMapper;
import com.scarlet.task_manager_api.persistence.repository.ProjectRepository;
import com.scarlet.task_manager_api.persistence.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;


    public TaskService(
            TaskRepository taskRepository,
            TaskMapper taskMapper,
            ProjectRepository projectRepository
    ) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
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


        if(task.getProjectId() == null){
            throw new RuntimeException("Project ID is required");
        }


        Project project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() ->
                        new RuntimeException("Project not found")
                );


        var entity = taskMapper.toEntity(task);

        entity.setProject(project);


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