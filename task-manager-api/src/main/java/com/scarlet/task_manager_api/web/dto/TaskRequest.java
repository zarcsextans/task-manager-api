package com.scarlet.task_manager_api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TaskRequest {

    @Schema(example = "Crear autenticación")
    private String title;


    @Schema(example = "Implementar login con JWT")
    private String description;


    @Schema(example = "HIGH")
    private String priority;


    @Schema(example = "false")
    private Boolean completed;


    @Schema(example = "1")
    private Integer projectId;

    @Schema(example = "1")
    private Integer categoryId;



    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }


    public Boolean getCompleted() {
        return completed;
    }


    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    public Integer getProjectId() {
        return projectId;
    }


    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}