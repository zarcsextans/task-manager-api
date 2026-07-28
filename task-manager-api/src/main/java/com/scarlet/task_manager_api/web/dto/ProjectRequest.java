package com.scarlet.task_manager_api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjectRequest {

    @Schema(example = "Sistema de Ventas")
    private String name;

    @Schema(example = "API para administrar ventas")
    private String description;

    @Schema(example = "En proceso")
    private String status;


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
}