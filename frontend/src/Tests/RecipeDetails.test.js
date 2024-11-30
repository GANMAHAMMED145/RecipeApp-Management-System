import { render, screen, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import RecipeDetails from '../Components/RecipeDetails';
import { getRecipeById } from '../ApiService/recipeService';

// Mock the API service
jest.mock('../ApiService/recipeService', () => ({
  getRecipeById: jest.fn(),
}));

// Sample data for testing
const mockRecipeDetails = {
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
};

describe('RecipeDetails', () => {
  beforeEach(() => {
    getRecipeById.mockResolvedValue(mockRecipeDetails);
  });

  test('renders loading spinner when recipe details are being fetched', () => {
    render(
      <MemoryRouter initialEntries={['/recipe?recipeid=1']}>
        <RecipeDetails />
      </MemoryRouter>
    );

    expect(screen.getByText(/Loading recipe details.../i)).toBeInTheDocument();
  });

  test('handles missing recipe ID in the URL', () => {
    render(
      <MemoryRouter initialEntries={['/recipe']}>
        <RecipeDetails />
      </MemoryRouter>
    );

    expect(screen.getByText(/Recipe ID is missing/i)).toBeInTheDocument();
  });

  test('handles error fetching recipe details', async () => {
    getRecipeById.mockRejectedValue(new Error('Error fetching recipe details'));

    render(
      <MemoryRouter initialEntries={['/recipe?recipeid=1']}>
        <RecipeDetails />
      </MemoryRouter>
    );

    await waitFor(() => {
      expect(screen.getByText(/Error fetching recipe details/i)).toBeInTheDocument();
    });
  });
});
