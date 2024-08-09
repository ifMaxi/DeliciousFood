# Delicious Food

Recipe app made with jetpack compose 🍽️. 

## Description

Recipe book application that obtains information from [The Meal database API](https://www.themealdb.com/api.php). Obtain recipes and, if desired, save a copy in the app's database for later reading both online and offline. It is written in Kotlin and uses Jetpack Compose for its UI and various jetpack libraries, as well as third-party libraries.

> [!NOTE]
> The app supports light and dark mode, but does not support dynamic color.

> [!NOTE]
> An Api-Key is not required to use the app.

## Architecture

The architecture used is the one recommended by Google, usually called MVVM (Model - View - ViewModel)

This is divided into:

- Model: Which represents the data and business logic
- View: Which represents the UI
- ViewModel: Which represents the bridge between the View and the Model

![Mvvm arch](https://github.com/user-attachments/assets/011add8b-cd32-4ae7-b78e-60a2ca578a59)

## Api

The API used is from [The Meal database API](https://www.themealdb.com/api.php). It uses several endpoints such as search, categories and others.

## Navigation

Navigation graph describing the flow of the app. 

Its main screen contains 4 main components: 

- **A search engine**: The search component activates when you place a query or its initial letter, displaying a list of 25 objects with the search matches. When you select a recipe, it navigates to the details screen displaying useful information.
- **A random recipe component**: Displays a random object every time the app is launched, selecting it navigates to the details screen.
- **The favorites button**: When pressed, it navigates to a screen with a list of all the objects stored in the app's database. Selecting an object navigates to the details screen. The great advantage of this component is that it is available offline.
- **And the categories section**: Located at the bottom of the screen is a section with different categories for recipes. Selecting an item navigates to a screen displaying a list of items with all the recipes in the selected category. In turn, when a recipe is selected, it navigates to the details screen.

It also has a details screen, which contains useful information about the selected recipe: a photo, the ingredients and their quantities, preparation instructions and three buttons whose function is to save/delete the item from the database, a link with an intent that opens the browser to see the origin of the recipe and a link that opens the YouTube app with an explanation of the recipe.

![NavGraph](https://github.com/user-attachments/assets/05c2bd8f-e962-4b90-bdbb-70c079b2cc1e)

## Language and libraries

- **Kotlin**
  - Serialization
  - Coroutines
  - Kps
- **Android**
  - Intents
- **Jetpack Libraries**
  - Compose
  - Material 3
  - Hilt
  - ViewModel
  - Navigation
  - Room database
  - Paging 3
    - Remote mediator
- **Other libraries**
  - Retrofit
  - OkHttp
  - Coil
  - Lottie

## Captures

| Home | Search |
| ------------- | ------------ |
| <img src="https://github.com/user-attachments/assets/2d82b896-c93b-4543-8d59-a26e6f50a60e" width="290" height="600"> | <img src="https://github.com/user-attachments/assets/4402abc3-118b-487a-b33f-33e628a8fda1" width="290" height="600"> |

| Category List | Favorites |
| ------------- | ------------ |
| <img src="https://github.com/user-attachments/assets/6c1b5576-df02-460a-9d7c-2f48fb914e51" width="290" height="600"> | <img src="https://github.com/user-attachments/assets/c2698d07-6ae3-4168-a5f5-1ab50b9722eb" width="290" height="600"> |

| Details (TOP) | Details (Bottom) |
| ------------- | ------------ |
| <img src="https://github.com/user-attachments/assets/d6a7043e-955a-4e40-9f42-3f3c03d0052e" width="290" height="600"> | <img src="https://github.com/user-attachments/assets/16841a99-affe-45b1-ae63-3ae1c6cd036b" width="290" height="600"> |

| Home (Dark mode) | Favorite (Dark mode) |
| ------------- | ------------ |
| <img src="https://github.com/user-attachments/assets/42cf4cdb-5186-4f84-9118-018a59684754" width="290" height="600"> | <img src="https://github.com/user-attachments/assets/244cd066-12fd-4956-8d7b-228eee415666" width="290" height="600"> |

| Details (Dark mode) |
| ------------- |
| <img src="https://github.com/user-attachments/assets/a9fc3ec0-9c06-4eb0-b737-263950196fad" width="290" height="600"> |
