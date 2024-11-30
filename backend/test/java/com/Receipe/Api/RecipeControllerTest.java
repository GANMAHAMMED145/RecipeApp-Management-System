package com.Receipe.Api;

import com.Receipe.Api.Controller.RecipeController;
import com.Receipe.Api.DTO.ReceipeDTO;
import com.Receipe.Api.ExceptionHandler.ResourceNotFoundException;
import com.Receipe.Api.Service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    // Test case for fetching all recipes
    @Test
    public void testGetAllRecipes_Success() throws Exception {
        ReceipeDTO recipe1 = mockRecipe(1L, "Pasta", "Italian", 400);
        ReceipeDTO recipe2 = mockRecipe(2L, "Pizza", "Italian", 700);
        List<ReceipeDTO> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeService.getAllRecipes()).thenReturn(recipes);

        mockMvc.perform(get("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Pasta"))
                .andExpect(jsonPath("$[1].name").value("Pizza"));

        verify(recipeService, times(1)).getAllRecipes();
    }



    // Test case for fetching a recipe by ID successfully
    @Test
    public void testGetRecipeById_Success() throws Exception {
        ReceipeDTO recipe = mockRecipe(1L, "Pasta", "Italian", 400);

        when(recipeService.getRecipeById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pasta"));

        verify(recipeService, times(1)).getRecipeById(1L);
    }

    // Test case for fetching recipes by cuisine
    @Test
    public void testGetRecipesByCuisine_Success() throws Exception {
        ReceipeDTO recipe1 = mockRecipe(1L, "Pasta", "Italian", 400);
        ReceipeDTO recipe2 = mockRecipe(2L, "Pizza", "Italian", 700);

        List<ReceipeDTO> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeService.getRecipesByCuisine("Italian")).thenReturn(recipes);

        mockMvc.perform(get("/api/recipes/cuisine/Italian")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Pasta"))
                .andExpect(jsonPath("$[1].name").value("Pizza"));

        verify(recipeService, times(1)).getRecipesByCuisine("Italian");
    }



    // Test case for fetching recipes sorted by calories
    @Test
    public void testGetRecipesSortedByCalories_Success() throws Exception {
        ReceipeDTO recipe1 = mockRecipe(1L, "Pasta", "Italian", 400);
        ReceipeDTO recipe2 = mockRecipe(2L, "Pizza", "Italian", 700);

        List<ReceipeDTO> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeService.getRecipesSortedByCalories("asc")).thenReturn(recipes);

        mockMvc.perform(get("/api/recipes/sorted?sortOrder=asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Pasta"))
                .andExpect(jsonPath("$[1].name").value("Pizza"));

        verify(recipeService, times(1)).getRecipesSortedByCalories("asc");
    }



    // Mock helper method to avoid using constructors
    private ReceipeDTO mockRecipe(Long id, String name, String cuisine, int calories) {
        ReceipeDTO recipe = mock(ReceipeDTO.class);
        when(recipe.getId()).thenReturn(id);
        when(recipe.getName()).thenReturn(name);
        when(recipe.getCuisine()).thenReturn(cuisine);
        when(recipe.getCaloriesPerServing()).thenReturn(calories);
        return recipe;
    }
}
