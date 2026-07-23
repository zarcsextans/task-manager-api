package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.persistence.entity.Project;
import com.scarlet.task_manager_api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }


    public Project createProject(Project project) {
        return projectRepository.save(project);
    }


    public Project updateProject(Long id, Project projectDetails) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setStatus(projectDetails.getStatus());

        return projectRepository.save(project);
    }


    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        projectRepository.delete(project);
    }
}