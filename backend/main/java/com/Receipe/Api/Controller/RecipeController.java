package com.Receipe.Api.Controller;

import com.Receipe.Api.DTO.ReceipeDTO;
import com.Receipe.Api.ExceptionHandler.ResourceNotFoundException;
import com.Receipe.Api.Service.RecipeService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // Endpoint to fetch all recipes
    @GetMapping
    public ResponseEntity<List<ReceipeDTO>> getAllRecipes() {
        List<ReceipeDTO> recipes = recipeService.getAllRecipes();
        if (recipes.isEmpty()) {
            throw new ResourceNotFoundException("No recipes found.");
        }
        return ResponseEntity.ok(recipes);
    }

    // Endpoint to fetch a recipe by ID with validation
    @GetMapping("/{id}")
    public ResponseEntity<ReceipeDTO> getRecipeById(
            @PathVariable @Min(value = 1, message = "Recipe ID must be greater than 0") Long id) {
        ReceipeDTO recipe = recipeService.getRecipeById(id);
        if (recipe == null) {
            throw new ResourceNotFoundException("Recipe with ID " + id + " not found.");
        }
        return ResponseEntity.ok(recipe);
    }

    // Endpoint to fetch recipes by cuisine
    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<ReceipeDTO>> getRecipeByCuisine(@PathVariable String cuisine) {
        List<ReceipeDTO> recipes = recipeService.getRecipesByCuisine(cuisine);
        if (recipes.isEmpty()) {
            throw new ResourceNotFoundException("No recipes found for cuisine: " + cuisine);
        }
        return ResponseEntity.ok(recipes);
    }

    // Endpoint to fetch recipes sorted by calories per serving
    @GetMapping("/sorted")
    public ResponseEntity<List<ReceipeDTO>> getRecipesSortedByCalories(
            @RequestParam(defaultValue = "asc") String sortOrder) {
        List<ReceipeDTO> recipes = recipeService.getRecipesSortedByCalories(sortOrder);
        if (recipes.isEmpty()) {
            throw new ResourceNotFoundException("No recipes found to sort by calories.");
        }
        return ResponseEntity.ok(recipes);
    }

    // Endpoint to fetch recipes from external API and save to H2 database
    @PostMapping("/fetch-and-save-recipes")
    public ResponseEntity<String> fetchAndSaveRecipes() {
        recipeService.fetchAndSaveRecipesFromExternalAPI();
        return ResponseEntity.ok("Recipes fetched and saved successfully!");
    }
}
