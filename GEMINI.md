# Movies_DB (TeamApp)

A modern Android application for browsing movies and managing a watchlist, utilizing the TMDB API. Built with Kotlin, Jetpack Compose, and Clean Architecture.

## 🛠 Tech Stack

*   **Language:** Kotlin (2.0.21)
*   **UI Framework:** Jetpack Compose (Material3)
*   **Architecture:** Clean Architecture + MVVM
*   **Dependency Injection:** Hilt
*   **Networking:** Retrofit + OkHttp + Gson
*   **Database:** Room (SQLite)
*   **Image Loading:** Coil
*   **Navigation:** Jetpack Navigation Compose
*   **Asynchronicity:** Coroutines + Flow
*   **Build System:** Gradle (Kotlin DSL) with Version Catalogs

## 🚀 Getting Started

### Prerequisites

*   **Android Studio:** Koala/Ladybug (2024.1+) or newer.
*   **JDK:** Version 17 or higher.

### API Key Configuration

This project requires a TMDB API key to function.

1.  Obtain an API Key from [TheMovieDB.org](https://www.themoviedb.org/).
2.  Create a file named `local.properties` in the project root (if it doesn't exist).
3.  Add your API key to the file:
    ```properties
    API_KEY="your_actual_api_key_here"
    ```
    *Note: The app's `build.gradle.kts` reads this property and exposes it via `BuildConfig.API_KEY`.*

### Building and Running

Use the standard Gradle wrapper commands:

*   **Build Debug APK:**
    ```bash
    ./gradlew assembleDebug
    ```
*   **Run Unit Tests:**
    ```bash
    ./gradlew testDebugUnitTest
    ```
*   **Run Instrumented Tests:**
    ```bash
    ./gradlew connectedDebugAndroidTest
    ```
*   **Clean Project:**
    ```bash
    ./gradlew clean
    ```

## 📂 Project Structure

The project follows a standard Clean Architecture layout within `app/src/main/java/com/example/movies_db/`:

*   **`ui/`**: Presentation layer.
    *   `screens/`: Composable screens (e.g., `HomeScreen`).
    *   `components/`: Reusable UI elements.
    *   `navigation/`: Navigation graph and route definitions.
*   **`domain/`**: Business logic layer (Pure Kotlin).
    *   `model/`: Domain models.
    *   `repository/`: Interfaces for data access.
*   **`data/`**: Data layer implementation.
    *   `remote/`: Retrofit services and DTOs.
    *   `local/`: Room database entities and DAOs.
    *   `repository/`: Repository implementations.
*   **`di/`**: Hilt modules for dependency injection.
*   **`network/`**: Network configuration (e.g., DNS, Interceptors).
*   **`MainActivity.kt`**: The single Activity entry point.
*   **`TeamApp.kt`**: The Application class (Hilt entry point).

## 📏 Development Conventions

*   **Dependency Management:** All dependencies are defined in `gradle/libs.versions.toml`. Do not hardcode versions in `build.gradle.kts`.
*   **Annotation Processing:** Use KSP (`ksp`) instead of KAPT for Room and Hilt.
*   **UI:** Use Jetpack Compose with Material3. Avoid XML layouts.
*   **Git:** Follow the feature branch workflow (`feature/your-feature-name`). Ensure `./gradlew clean build` passes before pushing.
