package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.domain.Project;
import com.scarlet.task_manager_api.persistence.mapper.ProjectMapper;
import com.scarlet.task_manager_api.persistence.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    public ProjectService(
            ProjectRepository projectRepository,
            ProjectMapper projectMapper
    ) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
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

        com.scarlet.task_manager_api.persistence.entity.Project entity =
                projectMapper.toEntity(project);

        com.scarlet.task_manager_api.persistence.entity.Project saved =
                projectRepository.save(entity);

        return projectMapper.toDomain(saved);
    }


    public Project updateProject(Integer id, Project projectDetails) {

        var entity = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));


        entity.setName(projectDetails.getName());
        entity.setDescription(projectDetails.getDescription());
        entity.setStatus(projectDetails.getStatus());


        var updatedEntity = projectRepository.save(entity);


        return projectMapper.toDomain(updatedEntity);
    }


    public void deleteProject(Integer id) {

        var entity = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));


        projectRepository.delete(entity);
    }
}