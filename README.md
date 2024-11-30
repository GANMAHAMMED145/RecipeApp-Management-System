## Recipe Management System

A Recipe Management System  FrontEnd was  built  using React.js, React Router, and API Integration. 


BackEnd was built using Java, Spring Boot, H2 in memory Databse, Hibernate (ORM for database interaction).


This app allows users to view, filter, and sort recipes based on different criteria such as cuisine type, calories.


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

CSS for styling  

Fetch for API requests

Backend:

Java 17+

Spring Boot 3.x (RESTful API development)

Hibernate (ORM for database interaction)

H2 Database (In-memory database for development and testing)

Jakarta Validation (Input validation)

JUnit & Mockito (Testing framework)

Lombok (Reduce boilerplate code)

Maven 3.6+

IDE (IntelliJ, Eclipse, or VS Code)


## Testing:
FrontEnd:
Jest for testing
React Testing Library for component-level tests

BackEnd:

JUnit & Mockito (Testing framework)

## Setup and Installation
Follow these steps to set up and run the project locally.

## Clone the repository:

 https://github.com/GANMAHAMMED145/RecipeApp-Management-System.git
 cd RecipeApp-Management-System

## Install dependencies:

Make sure you have Node.js and npm, React js, Java, SpringBoot installed on your system.

## commands for install:

npm install

## Start the development servers:

npm start(frontend)


mvn spring-boot:run(backend)


Frontend  app on http://localhost:3000 in development mode. 


Backend app will be run on http://localhost:8080

 


## API Endpoints

1. http://localhost:8080/api/recipes/sorted?defaultValue=asc   (for Get All Recipes Sorted by Calories)
2. http://localhost:8080/api/recipes/1   (for  Get Recipe by ID)
3. http://localhost:8080/api/recipes      (for all receipes)
4. http://localhost:8080/api/recipes/cuisine/{Asian} (filter Recipes with cusine)
5. http://localhost:8080/api/recipes/fetch-and-save-recipes(for getting data from external api https://dummyjson.com/recipes and storing into H2 in memeory Database.

#1,#2,#3,#4 are GET Request type methods for rertieving data. 


#5 is POST Request type method for inserting data into H2 in memoery  databse.


## Testing
To run the tests for this project, ensure that you have all the dependencies installed and then run:

Frontend:

npm test  

This will run the Jest test suite, which includes tests for fetching and rendering recipes, sorting and filtering recipes, and ensuring the correct rendering of recipe details.

Backend:

mvn test

## Contributing
We welcome contributions to this project! Here's how you can get involved:

Fork this repository.

Create a new branch (git checkout -b feature/your-feature).

Make your changes and commit them (git commit -am 'Add new feature').

Push to your branch (git push origin feature/your-feature).

Create a pull request.

Please make sure to follow the existing coding style and write tests for any new functionality you introduce.


# Author

👤 Gangupalli Mahammed
Email: mahammed145jntua@gmail.com

