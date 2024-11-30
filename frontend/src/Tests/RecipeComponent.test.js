import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import RecipeComponent from '../Components/RecipeComponent';
import { getAllRecipes } from '../ApiService/recipeService';
import { MemoryRouter } from 'react-router-dom';

// Mock the API service
jest.mock('../ApiService/recipeService', () => ({
  getAllRecipes: jest.fn(),
}));

// Sample data for testing with multiple recipes for sorting tests
const mockRecipes = [
  {
    id: 1,
    name: "Classic Margherita Pizza",
    ingredients: [
      "Pizza dough",
      "Tomato sauce",
      "Fresh mozzarella cheese",
      "Fresh basil leaves",
      "Olive oil",
      "Salt and pepper to taste",
    ],
    instructions: [
      "Preheat the oven to 475°F (245°C).",
      "Roll out the pizza dough and spread tomato sauce evenly.",
      "Top with slices of fresh mozzarella and fresh basil leaves.",
      "Drizzle with olive oil and season with salt and pepper.",
      "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
      "Slice and serve hot.",
    ],
    prepTimeMinutes: 20,
    cookTimeMinutes: 15,
    servings: 4,
    difficulty: "Easy",
    cuisine: "Italian",
    caloriesPerServing: 300,
    tags: ["Pizza", "Italian"],
    userId: 166,
    image: "https://cdn.dummyjson.com/recipe-images/1.webp",
    rating: 4.6,
    reviewCount: 98,
    mealType: ["Dinner"],
  },
  {
    id: 2,
    name: "Vegetable Stir Fry",
    ingredients: [
      "Mixed vegetables",
      "Soy sauce",
      "Olive oil",
      "Garlic",
      "Ginger",
    ],
    instructions: [
      "Heat the olive oil in a pan.",
      "Add garlic and ginger, cook for 1-2 minutes.",
      "Add vegetables and stir fry for 5-7 minutes.",
      "Add soy sauce and cook for an additional 2 minutes.",
    ],
    prepTimeMinutes: 10,
    cookTimeMinutes: 15,
    servings: 2,
    difficulty: "Medium",
    cuisine: "Asian",
    caloriesPerServing: 150,
    tags: ["Vegetarian", "Asian"],
    userId: 167,
    image: "https://cdn.dummyjson.com/recipe-images/2.webp",
    rating: 4.2,
    reviewCount: 50,
    mealType: ["Lunch"],
  },
];

describe('RecipeComponent', () => {
  beforeEach(() => {
    getAllRecipes.mockResolvedValue(mockRecipes);
  });

  test('renders loading spinner when recipes are being fetched', () => {
    render(
      <MemoryRouter>
        <RecipeComponent />
      </MemoryRouter>
    );

    expect(screen.getByText(/Loading Recipes.../i)).toBeInTheDocument();
  });

  test('renders recipes when fetched successfully', async () => {
    render(
      <MemoryRouter>
        <RecipeComponent />
      </MemoryRouter>
    );

    await waitFor(() => {
      expect(screen.getByText(/Classic Margherita Pizza/i)).toBeInTheDocument();
    });

    // Check that the correct recipe details are displayed, but make sure you target
    // specific elements inside the recipe card
    const pizzaCard = screen.getByText(/Classic Margherita Pizza/i).closest('.recipe-card');
    expect(pizzaCard).toHaveTextContent('Food: Italian');
    expect(pizzaCard).toHaveTextContent('Calories per Serving: 300');
    expect(pizzaCard).toHaveTextContent('Rating: 4.6');
    expect(pizzaCard).toHaveTextContent('Review Count: 98 reviews');
    expect(pizzaCard.querySelector('img')).toHaveAttribute(
      'src',
      'https://cdn.dummyjson.com/recipe-images/1.webp'
    );
  });

  test('filters recipes by cuisine', async () => {
    render(
      <MemoryRouter>
        <RecipeComponent />
      </MemoryRouter>
    );

    await waitFor(() => {
      expect(screen.getByText(/Classic Margherita Pizza/i)).toBeInTheDocument();
    });

    // Select the cuisine filter
    fireEvent.change(screen.getByRole('combobox'), { target: { value: 'Italian' } });

    await waitFor(() => {
      expect(screen.getByText(/Classic Margherita Pizza/i)).toBeInTheDocument();
    });
  });

  test('sorts recipes by calories ascending', async () => {
    render(
      <MemoryRouter>
        <RecipeComponent />
      </MemoryRouter>
    );

    await waitFor(() => {
      expect(screen.getByText(/Classic Margherita Pizza/i)).toBeInTheDocument();
    });

    fireEvent.click(screen.getByText(/Sort by Calories \(Asc\)/i));

    // After sorting by calories ascending, expect the first recipe's calories to be more than or equal to the second
    expect(mockRecipes[0].caloriesPerServing).toBeGreaterThan(mockRecipes[1].caloriesPerServing);
  });

  test('sorts recipes by calories descending', async () => {
    render(
      <MemoryRouter>
        <RecipeComponent />
      </MemoryRouter>
    );

    await waitFor(() => {
      expect(screen.getByText(/Classic Margherita Pizza/i)).toBeInTheDocument();
    });

    fireEvent.click(screen.getByText(/Sort by Calories \(Desc\)/i));

    // After sorting by calories descending, expect the first recipe's calories to be more than or equal to the second
    expect(mockRecipes[0].caloriesPerServing).toBeGreaterThanOrEqual(mockRecipes[1].caloriesPerServing);
  });
});
