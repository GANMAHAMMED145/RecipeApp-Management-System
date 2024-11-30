import { useEffect, useState } from 'react';
import { getAllRecipes } from '../ApiService/recipeService';
import { Link } from 'react-router-dom';

const RecipeComponent = () => {
  const [recipes, setRecipes] = useState([]);
  const [error, setError] = useState(null);
  const [food, setFood] = useState('');
  const [originalRecipes, setOriginalRecipes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchRecipes = async () => {
      const result = await getAllRecipes();
      if (result.error) {
        setError(result.error);
      } else {
        setOriginalRecipes(result);
        setRecipes(result);
        setLoading(false); // Set loading to false when recipes are fetched
      }
    };
    fetchRecipes();
  }, []);

  const handleFoodChange = (e) => {
    const selectedFood = e.target.value;
    setFood(selectedFood);
    setLoading(true); // Start loading again when a new cuisine is selected

    if (selectedFood) {
      const filteredRecipes = originalRecipes.filter(recipe => recipe.cuisine.toLowerCase() === selectedFood.toLowerCase());
      setRecipes(filteredRecipes);
    } else {
      setRecipes(originalRecipes);
    }

    setLoading(false); // Stop loading when filtering is done
  };

  const sortByCaloriesAsc = () => {
    const sortedRecipes = [...recipes].sort((a, b) => a.caloriesPerServing - b.caloriesPerServing);
    setRecipes(sortedRecipes);
  };

  const sortByCaloriesDesc = () => {
    const sortedRecipes = [...recipes].sort((a, b) => b.caloriesPerServing - a.caloriesPerServing);
    setRecipes(sortedRecipes);
  };

  return (
    <div>
      <header className="header">
        <h1>The Recipe Hub</h1>
        <div className="filter-bar">
          <select onChange={handleFoodChange} value={food}>
            <option value="">Select Food</option>
            <option value="Asian">Asian</option>
            <option value="American">American</option>
            <option value="Brazilian">Brazilian</option>
            <option value="Greek">Greek</option>
            <option value="Indian">Indian</option>
            <option value="Italian">Italian</option>
            <option value="Japanese">Japanese</option>
            <option value="Korean">Korean</option>
            <option value="Lebanese">Lebanese</option>
            <option value="Mexican">Mexican</option>
            <option value="Mediterranean">Mediterranean</option>
            <option value="Pakistani">Pakistani</option>
            <option value="Russian">Russian</option>
            <option value="Smoothie">Smoothie</option>
            <option value="Thai">Thai</option>
          </select>

          {/* Sort buttons for calories */}
          <button onClick={sortByCaloriesAsc}>Sort by Calories (Asc)</button>
          <button onClick={sortByCaloriesDesc}>Sort by Calories (Desc)</button>
        </div>
      </header>

      {loading && <div className="loading-spinner">Loading Recipes...</div>}

      {!loading && (
        <div className="recipe-grid">
          {recipes.map((recipe) => (
            <div key={recipe.id} className="recipe-card">
              <div className="recipe-image-container">
                <img
                  src={recipe.image}
                  alt={recipe.name}
                  className="recipe-image"
                />
              </div>
              <div className="recipe-info">
                <h3>{recipe.name}</h3>
                <p><strong>Food:</strong> {recipe.cuisine}</p>
                <p><strong>Calories per Serving:</strong> {recipe.caloriesPerServing}</p>
                <p><strong>Rating:</strong> {recipe.rating}</p>
                <p><strong>Review Count:</strong> {recipe.reviewCount} reviews</p>
                <Link to={`/recipe?recipeid=${recipe.id}`} className="view-details">View Details</Link> {/* Query parameter for recipeid */}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default RecipeComponent;
