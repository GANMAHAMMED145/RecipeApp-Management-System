const BASE_URL = 'http://localhost:8080/api/recipes';

export const getAllRecipes = async () => {
  try {
    const response = await fetch(BASE_URL);
    if (!response.ok) throw new Error('Failed to fetch recipes');
    return await response.json();
  } catch (error) {
    return { error: error.message };
  }
};

export const getRecipesByCuisine = async (cuisine) => {
  try {
    const response = await fetch(`${BASE_URL}/cuisine/${cuisine}`);
    if (!response.ok) throw new Error('Failed to fetch recipes by cuisine');
    return await response.json();
  } catch (error) {
    return { error: error.message };
  }
};

export const getSortedRecipes = async (sortOrder) => {
  try {
    const response = await fetch(`${BASE_URL}/sorted?defaultValue=${sortOrder}`);
    if (!response.ok) throw new Error('Failed to fetch sorted recipes');
    return await response.json();
  } catch (error) {
    return { error: error.message };
  }
};

export const getRecipeById = async (id) => {
  try {
    const response = await fetch(`${BASE_URL}/${id}`);
    if (!response.ok) throw new Error('Failed to fetch recipe by id');
    return await response.json();
  } catch (error) {
    return { error: error.message };
  }
};
