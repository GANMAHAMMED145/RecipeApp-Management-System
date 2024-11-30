import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'  // Correct import for React Router v6
import RecipeComponent from './Components/RecipeComponent';
import RecipeDetails from './Components/RecipeDetails';
import './CSSStyles/AppStyles.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          {/* Define Routes with `element` prop */}
          <Route path="/" element={<RecipeComponent />} />  {/* Homepage with recipe list */}
          
          {/* Route for recipe details, will use query params */}
          <Route path="/recipe" element={<RecipeDetails />} />  {/* Will match /recipe?recipeid=1 */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;

