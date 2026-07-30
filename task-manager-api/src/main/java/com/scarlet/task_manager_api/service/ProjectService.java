package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.domain.Project;
import com.scarlet.task_manager_api.persistence.mapper.ProjectMapper;
import com.scarlet.task_manager_api.persistence.repository.ProjectRepository;
import com.scarlet.task_manager_api.persistence.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;


    public ProjectService(
            ProjectRepository projectRepository,
            ProjectMapper projectMapper,
            UserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }


    public List<Project> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDomain)
                .collect(Collectors.toList());
    }


    public Optional<Project> getProjectById(Integer id) {

        return projectRepository.findById(id)
                .map(projectMapper::toDomain);
    }


    public Project createProject(Project project) {

        if (project.getUserId() == null) {
            throw new RuntimeException("User ID is required");
        }


        if (projectRepository.existsByName(project.getName())) {
            throw new RuntimeException("Project name already exists");
        }


        var usuario = userRepository.findById(project.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );


        com.scarlet.task_manager_api.persistence.entity.Project entity =
                projectMapper.toEntity(project);


        entity.setUsuario(usuario);


        com.scarlet.task_manager_api.persistence.entity.Project saved =
                projectRepository.save(entity);


        return projectMapper.toDomain(saved);
    }


    public Project updateProject(Integer id, Project projectDetails) {

        var entity = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found")
                );


        entity.setName(projectDetails.getName());
        entity.setDescription(projectDetails.getDescription());
        entity.setStatus(projectDetails.getStatus());


        if (projectDetails.getUserId() != null) {

            var usuario = userRepository.findById(projectDetails.getUserId())
                    .orElseThrow(() ->
                            new RuntimeException("User not found")
                    );

            entity.setUsuario(usuario);
        }


        var updatedEntity = projectRepository.save(entity);


        return projectMapper.toDomain(updatedEntity);
    }


    public void deleteProject(Integer id) {

        var entity = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found")
                );


        projectRepository.delete(entity);
    }
}