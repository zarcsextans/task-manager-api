package com.scarlet.task_manager_api.web.dto;

public class UserResponse {

    private Integer id;

    private String username;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
}