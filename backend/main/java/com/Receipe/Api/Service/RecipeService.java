package com.Receipe.Api.Service;

import com.Receipe.Api.DTO.ReceipeDTO;
import com.Receipe.Api.Entity.Receipe;
import com.Receipe.Api.Model.ExternalApiResponse;
import com.Receipe.Api.Repoistory.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RestTemplate restTemplate;  // For making HTTP requests

    private static final ObjectMapper objectMapper = new ObjectMapper();  // For JSON parsing
    private static final String API_URL = "https://dummyjson.com/recipes";  // External API URL

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, RestTemplate restTemplate) {
        this.recipeRepository = recipeRepository;
        this.restTemplate = restTemplate;
    }

    // Fetch all recipes
    public List<ReceipeDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Fetch recipe by ID
    public ReceipeDTO getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    // Fetch recipes by cuisine
    public List<ReceipeDTO> getRecipesByCuisine(String cuisine) {
        return recipeRepository.findByCuisine(cuisine).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Fetch recipes sorted by calories
    public List<ReceipeDTO> getRecipesSortedByCalories(String sortOrder) {
        List<Receipe> recipes;
        if ("desc".equalsIgnoreCase(sortOrder)) {
            recipes = recipeRepository.findAllByOrderByCaloriesPerServingDesc();
        } else {
            recipes = recipeRepository.findAllByOrderByCaloriesPerServingAsc();
        }
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Fetch and store in H2 database
    public void fetchAndSaveRecipesFromExternalAPI() {
        try {
            // Fetch the external API response
            String response = restTemplate.getForObject(API_URL, String.class);

            if (response != null) {
                // Deserialize the response into ExternalApiResponse
                ExternalApiResponse externalApiResponse = objectMapper.readValue(response, ExternalApiResponse.class);

                // Get the list of recipes from the response
                List<Receipe> recipes = externalApiResponse.getRecipes();

                // Filter recipes that do not have empty instructions or ingredients
                recipes = recipes.stream()
                        .filter(recipe -> recipe.getInstructions() != null && !recipe.getInstructions().isEmpty() &&
                                recipe.getIngredients() != null && !recipe.getIngredients().isEmpty())
                        .collect(Collectors.toList());

                // Save the recipes to the database
                if (!recipes.isEmpty()) {
                    recipeRepository.saveAll(recipes);
                    System.out.println("Recipes successfully fetched and saved!");
                } else {
                    System.out.println("No valid recipes to save.");
                }
            } else {
                System.out.println("Failed to fetch data from the external API.");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Handle exceptions properly (e.g., logging)
        }
    }

    // Convert Recipe entity to RecipeDTO using ObjectMapper
    private ReceipeDTO convertToDTO(Receipe recipe) {
        return objectMapper.convertValue(recipe, ReceipeDTO.class);
    }
}
