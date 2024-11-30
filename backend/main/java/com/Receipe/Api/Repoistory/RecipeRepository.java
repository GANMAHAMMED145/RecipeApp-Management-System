package com.Receipe.Api.Repoistory;

import com.Receipe.Api.DTO.ReceipeDTO;
import com.Receipe.Api.Entity.Receipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RecipeRepository extends JpaRepository<Receipe, Long> {
    List<Receipe> findByCuisine(String cuisine);
    List<Receipe> findAllByOrderByCaloriesPerServingAsc();
    List<Receipe> findAllByOrderByCaloriesPerServingDesc();

}



