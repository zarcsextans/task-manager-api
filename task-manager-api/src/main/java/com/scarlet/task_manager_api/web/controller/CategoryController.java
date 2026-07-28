package com.scarlet.task_manager_api.web.controller;

import com.scarlet.task_manager_api.domain.Category;
import com.scarlet.task_manager_api.service.CategoryService;
import com.scarlet.task_manager_api.web.dto.CategoryRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(
        name = "Category Controller",
        description = "Operations for managing categories"
)
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Operation(
            summary = "Get all categories",
            description = "Returns a list of all registered categories"
    )
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {

        List<Category> categories = categoryService.getAllCategories();

        if(categories.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categories);
    }


    @Operation(
            summary = "Get category by ID",
            description = "Returns a category using its identifier"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Integer id){

        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Create a category",
            description = "Creates a new category and saves it in the database"
    )
    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody CategoryRequest request){

        Category category = new Category();

        category.setName(request.getName());

        Category savedCategory = categoryService.createCategory(category);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCategory);
    }


    @Operation(
            summary = "Update a category",
            description = "Updates an existing category"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoryRequest request){

        Category category = new Category();

        category.setName(request.getName());

        Category updated = categoryService.updateCategory(id, category);

        return ResponseEntity.ok(updated);
    }


    @Operation(
            summary = "Delete a category",
            description = "Deletes a category by its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer id){

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }

}