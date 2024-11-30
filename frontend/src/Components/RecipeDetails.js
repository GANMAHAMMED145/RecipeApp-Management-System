import { useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { getRecipeById } from '../ApiService/recipeService';

const RecipeDetails = () => {
  const location = useLocation();
  const [recipe, setRecipe] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  // Extract query params from URL
  const queryParams = new URLSearchParams(location.search);
  const recipeid = queryParams.get('recipeid');

  useEffect(() => {
    const fetchRecipe = async () => {
      if (!recipeid) {
        setError("Recipe ID is missing in the URL.");
        setLoading(false);
        return;
      }

      try {
        const result = await getRecipeById(recipeid);
        if (result.error) {
          setError(result.error);
        } else {
          setRecipe(result);
        }
      } catch (err) {
        setError("Error fetching recipe details.");
      } finally {
        setLoading(false);
      }
    };

    fetchRecipe();
  }, [recipeid]);

  if (loading) {
    return <div className="loading-spinner">Loading recipe details...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="recipe-details">
      <h1>{recipe.name}</h1>
      <h3>Ingredients:</h3>
      <ul>
        {recipe.ingredients && recipe.ingredients.length > 0 ? (
          recipe.ingredients.map((ingredient, index) => (
            <li key={index}>{ingredient}</li>
          ))
        ) : (
          <li>No ingredients available.</li>
        )}
      </ul>

      <h3>Instructions:</h3>
      <ol>
        {recipe.instructions && recipe.instructions.length > 0 ? (
          recipe.instructions.map((instruction, index) => (
            <li key={index}>{instruction}</li>
          ))
        ) : (
          <li>No instructions available.</li>
        )}
      </ol>

      <h3>Other Details:</h3>

      <table>
        <tbody>
          {recipe.prepTimeMinutes && (
            <tr>
              <td><strong>Prep Time</strong></td>
              <td>{recipe.prepTimeMinutes} minutes</td>
            </tr>
          )}
          {recipe.cookTimeMinutes && (
            <tr>
              <td><strong>Cook Time</strong></td>
              <td>{recipe.cookTimeMinutes} minutes</td>
            </tr>
          )}
          {recipe.difficulty && (
            <tr>
              <td><strong>Difficulty</strong></td>
              <td>{recipe.difficulty}</td>
            </tr>
          )}
          {recipe.servings && (
            <tr>
              <td><strong>Serving Size</strong></td>
              <td>{recipe.servings} servings</td>
            </tr>
          )}
          {recipe.tags && recipe.tags.length > 0 && (
            <tr>
              <td><strong>Tags</strong></td>
              <td>{recipe.tags.join(', ')}</td>
            </tr>
          )}
        </tbody>
      </table>

    </div>
  );
};

export default RecipeDetails;
