package com.Receipe.Api.DTO;

import com.Receipe.Api.Entity.Receipe;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceipeDTO {

    private Long id;

    @NotBlank(message = "Recipe name is required")
    private String name;

    @NotEmpty(message = "Ingredients list cannot be empty")
    private List<String> ingredients;

    @NotEmpty(message = "Instructions list cannot be empty")
    private List<String> instructions;

    private int prepTimeMinutes;
    private int cookTimeMinutes;
    private int servings;
    private String difficulty;
    private String cuisine;
    private int caloriesPerServing;
    private List<String> tags;
    private Long userId;
    private String image;
    private double rating;
    private int reviewCount;
    private List<String> mealType;


}
