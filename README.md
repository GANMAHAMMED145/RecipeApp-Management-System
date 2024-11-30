## Recipe Management System

A Recipe Management System built using React.js, React Router, and API Integration. This app allows users to view, filter, and sort recipes based on different criteria such as cuisine type, calories. 
It also provides a detailed view of each recipe with its ingredients, instructions, and nutritional information.

## Features

View all recipes:  Displays a list of all recipes fetched from an API.
Sort Recipes:     Sort recipes by various parameters such as calories (ascending/descending).
Filter Recipes:   Filter recipes by cuisine.
Recipe Details:   Each recipe includes detailed information such as ingredients, instructions, prep time, calories, and more.
Responsive Design: Fully responsive layout that works on desktop, tablet, and mobile devices.
Image Rendering: Display images associated with each recipe.

## Technologies
Frontend:

React.js (with hooks)

React Router for routing

CSS for styling (custom styles or libraries like Bootstrap or Material UI)

Axios (or Fetch) for API requests

## Testing:
Jest for testing
React Testing Library for component-level tests

## Setup and Installation
Follow these steps to set up and run the project locally.

## Clone the repository:

 https://github.com/GANMAHAMMED145/RecipeApp-Management-System.git
cd RecipeApp-Management-System

## Install dependencies:

Make sure you have Node.js and npm installed on your system

## command for install:

npm install

## Start the development server:

npm start

This will start the app on http://localhost:3000 in development mode. Any changes you make will be automatically reflected.


## API Integration

1. http://localhost:8080/api/recipes/sorted?defaultValue=asc   (for Get All Recipes Sorted by Calories)
2.  http://localhost:8080/api/recipes/1   (for  Get Recipe by ID)
3. http://localhost:8080/api/recipes      (for all receipes)
4. http://localhost:8080/api/recipes/cuisine/Asian (filter Recipes with cusine)


## Testing
To run the tests for this project, ensure that you have all the dependencies installed and then run:

npm test  

This will run the Jest test suite, which includes tests for fetching and rendering recipes, sorting and filtering recipes, and ensuring the correct rendering of recipe details.



## Contributing
We welcome contributions to this project! Here's how you can get involved:

Fork this repository.

Create a new branch (git checkout -b feature/your-feature).

Make your changes and commit them (git commit -am 'Add new feature').

Push to your branch (git push origin feature/your-feature).

Create a pull request.

Please make sure to follow the existing coding style and write tests for any new functionality you introduce.


##  Author

👤 Gangupalli Mahammed
Email: mahammed145jntua@gmail.com
