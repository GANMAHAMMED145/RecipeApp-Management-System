package com.Receipe.Api.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Receipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @ElementCollection

    private List<String> ingredients = new ArrayList<>();;

    @ElementCollection

    private List<String> instructions = new ArrayList<>();;


    private int prepTimeMinutes;


    private int cookTimeMinutes;


    private int servings;

    private String difficulty;

    private String cuisine;


    private int caloriesPerServing;

    @ElementCollection
    private List<String> tags = new ArrayList<>();;
    private Long userId;

    private String image;

    private double rating;
    private int reviewCount;

    @ElementCollection
    private List<String> mealType = new ArrayList<>();;
}
