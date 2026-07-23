package com.scarlet.task_manager_api;

import com.scarlet.task_manager_api.persistence.entity.Project;
import com.scarlet.task_manager_api.persistence.repository.ProjectRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class TaskManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApiApplication.class, args);
	}


	@Bean
	CommandLineRunner testPersistence(ProjectRepository projectRepository) {
		return args -> {

			Project project = new Project();

			project.setName("Test Project");
			project.setDescription("Testing persistence layer");
			project.setStatus("ACTIVE");
			project.setCreatedAt(LocalDateTime.now());

			projectRepository.save(project);

			System.out.println("Project saved successfully");
		};
	}
}