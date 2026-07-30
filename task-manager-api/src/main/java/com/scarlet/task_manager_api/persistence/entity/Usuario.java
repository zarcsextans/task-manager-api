package com.scarlet.task_manager_api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "username", unique = true)
    private String username;


    @Column(name = "password")
    private String password;



    @JsonIgnore
    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL
    )
    private List<Project> projects;



    @JsonIgnore
    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL
    )
    private List<Task> tasks;



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


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public List<Project> getProjects() {
        return projects;
    }


    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }


    public List<Task> getTasks() {
        return tasks;
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}