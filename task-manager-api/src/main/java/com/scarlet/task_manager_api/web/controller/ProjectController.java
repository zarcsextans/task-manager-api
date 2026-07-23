package com.scarlet.task_manager_api.web.controller;

import com.scarlet.task_manager_api.persistence.entity.Project;
import com.scarlet.task_manager_api.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @Operation(
            summary = "Get all projects",
            description = "Returns a list of all registered projects"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects found successfully"),
            @ApiResponse(responseCode = "404", description = "No projects found")
    })
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {

        List<Project> projects = projectService.getAllProjects();

        if (projects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projects);
    }


    @Operation(
            summary = "Get project by ID",
            description = "Returns a project using its identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(
            @Parameter(description = "Project ID")
            @PathVariable Integer id) {

        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Create a project",
            description = "Creates a new project and saves it in the database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid project data")
    })
    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project) {

        Project savedProject = projectService.createProject(project);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProject);
    }


    @Operation(
            summary = "Update a project",
            description = "Updates an existing project"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @Parameter(description = "Project ID")
            @PathVariable Integer id,
            @RequestBody Project project) {

        try {

            Project updatedProject = projectService.updateProject(id, project);

            return ResponseEntity.ok(updatedProject);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }


    @Operation(
            summary = "Delete a project",
            description = "Deletes a project by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @Parameter(description = "Project ID")
            @PathVariable Integer id) {

        try {

            projectService.deleteProject(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}