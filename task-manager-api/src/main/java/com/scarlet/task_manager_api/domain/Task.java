package com.scarlet.task_manager_api.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class Task {

    @Schema(example = "1")
    private Integer id;


    @Schema(example = "false")
    private Boolean completed;


    @Schema(example = "Crear autenticación con JWT")
    private String description;


    @Schema(example = "HIGH")
    private String priority;


    @Schema(example = "Implementar login")
    private String title;


    @Schema(example = "32")
    private Integer projectId;


    @Schema(example = "1")
    private Integer categoryId;


    @Schema(example = "2")
    private Integer userId;



    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Boolean getCompleted() {
        return completed;
    }


    public void setCompleted(Boolean completed) {
        this.completed = completed;
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


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getProjectId() {
        return projectId;
    }


    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}