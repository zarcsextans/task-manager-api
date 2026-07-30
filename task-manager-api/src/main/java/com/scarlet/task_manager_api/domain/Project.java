package com.scarlet.task_manager_api.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class Project {

    @Schema(example = "1")
    private Integer id;

    @Schema(example = "Sistema Biblioteca")
    private String name;

    @Schema(example = "API para gestionar libros")
    private String description;

    @Schema(example = "En proceso")
    private String status;

    @Schema(example = "2026-07-30T10:00:00")
    private LocalDateTime createdAt;


    @Schema(example = "2")
    private Integer userId;


    private List<Task> tasks;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}