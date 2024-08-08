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

## Language and libraries

- Kotlin
  - Serialization
  - Coroutines
  - Kps
- Android
  - Intents
- Jetpack Libraries
  - Compose
  - Material 3
  - Hilt
  - ViewModel
  - Navigation
  - Room database
  - Paging 3
    - Remote mediator
- Other libraries
  - Retrofit
  - OkHttp
  - Coil
  - Lottie

## Navigation

TODO: Navigation graph

## Captures

TODO: Captures
