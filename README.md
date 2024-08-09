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

TODO: Captures
