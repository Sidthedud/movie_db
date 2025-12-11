# Movies DB (TeamApp) 🎬

A modern, scalable Android application template designed for collaborative development. Built with **Clean Architecture**, **Jetpack Compose**, and **Offline-First** principles, this project serves as a robust foundation for browsing movies and managing a watchlist using the TMDB API.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?style=flat&logo=kotlin)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-Jetpack_Compose-3DDC84?style=flat&logo=android)](https://developer.android.com/jetpack/compose)
[![Architecture](https://img.shields.io/badge/Architecture-Clean_MVVM-blue?style=flat)](https://developer.android.com/topic/architecture)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## Table of Contents

- [Movies DB (TeamApp) 🎬](#movies-db-teamapp-)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
    - [Architecture Blueprint](#architecture-blueprint)
  - [Features](#features)
    - [Core Capabilities](#core-capabilities)
    - [Technical Highlights](#technical-highlights)
  - [Tech Stack](#tech-stack)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [API Key Setup](#api-key-setup)
    - [Build \& Run](#build--run)
  - [Project Structure](#project-structure)
  - [Development Workflow](#development-workflow)
    - [Adding a New Feature](#adding-a-new-feature)
    - [Database Management](#database-management)
  - [Configuration](#configuration)
  - [Contributing](#contributing)
  - [License](#license)

## Overview

**Movies DB** is an Android application built with Kotlin and Jetpack Compose. It implements the 'Single Source of Truth' principle using Hilt, Retrofit, and Room, ensuring consistent data flow from the repository to the UI.

### Architecture Blueprint

The application follows the recommended **Clean Architecture** guide by Google, separating concerns into three distinct layers:

```mermaid
graph TD
    UI[UI Layer(Compose + ViewModel)] --> Domain[Domain Layer(UseCases + Models)]
    Domain --> Data[Data Layer(Repository Implementation)]
    Data --> Remote[Remote Source(Retrofit + TMDB API)]
    Data --> Local[Local Source(Room Database)]
```

*   **UI Layer:** Handles user interaction and displays data using Jetpack Compose.
*   **Domain Layer:** Contains pure Kotlin business logic and defines the data contracts (Repositories).
*   **Data Layer:** Orchestrates data fetching, managing the decision to load from the local cache or the network.

## Features

### Core Capabilities
*   **🎬 Browse Movies:** Discover popular, top-rated, and upcoming movies.
*   **❤️ Watchlist:** Save movies locally to a favorites list.
*   **🔍 Detail View:** Deep dive into movie synopsis, release dates, and cast.
*   **📱 Adaptive UI:** Built with Material3 to look great on all device sizes.

### Technical Highlights
*   **Offline Support:** Uses Room to cache data, allowing the user to see their watchlist without an internet connection.
*   **Dependency Injection:** Fully modularized with Hilt for testability and scalability.
*   **Reactive Data Flow:** Built entirely on Kotlin Coroutines and Flow.
*   **Modern Navigation:** Type-safe navigation with Jetpack Navigation Compose.

## Tech Stack

*   **Language:** [Kotlin](https://kotlinlang.org/) (v2.0.21)
*   **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material3)
*   **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
*   **Networking:** [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) (DNS over HTTPS support)
*   **Serialization:** [Gson](https://github.com/google/gson)
*   **Database:** [Room](https://developer.android.com/training/data-storage/room) (SQLite)
*   **Image Loading:** [Coil](https://coil-kt.github.io/coil/)
*   **Build System:** Gradle with Version Catalogs (`libs.versions.toml`)

## Getting Started

Follow these steps to set up your local development environment.

### Prerequisites

*   **Android Studio:** Koala/Ladybug (2024.1) or newer.
*   **Java Development Kit (JDK):** Version 17 or higher.
*   **TMDB Account:** You need an API key from [The Movie Database](https://www.themoviedb.org/).

### API Key Setup


1.  **Get your Key:** Log in to TMDB and generate an API Key in your account settings.
2.  **Configure Local Properties:**
    Create a file named `local.properties` in the root directory (if it doesn't exist) and add:
    ```properties
    sdk.dir=/path/to/your/android/sdk
    API_KEY="your_actual_tmdb_api_key_here"
    ```
    *Note: The app will fail to build or fetch data if this key is missing.*

### Build & Run

Open the project in Android Studio and sync Gradle. Then, use the following commands or the IDE buttons:

```bash
# Clean the project
./gradlew clean

# Build the Debug APK
./gradlew assembleDebug

# Run Unit Tests
./gradlew testDebugUnitTest

# Run Instrumented (UI) Tests
./gradlew connectedDebugAndroidTest
```

## 📂 Project Structure

We follow a strict separation of concerns. Here is where everything lives:

```text
com.example.movies_db
├── di                  <-- Dependency Injection Modules (How to create Retrofit/Room)
├── data
│   ├── local           <-- Room Database files (DAO, Entities)
│   ├── remote          <-- Retrofit files (ApiService, DTOs)
│   └── repository      <-- The bridge between Data and UI
├── domain
│   ├── model           <-- Pure data classes (Movie, User)
│   ├── repository      <-- Interface definitions (What data we need)
│   └── usecase         <-- Specific business logic units
├── network
│   └── DnsClient.kt    <-- DNS and Network Interceptors
├── ui
│   ├── theme           <-- Colors, Type, Shapes
│   ├── components      <-- REUSABLE widgets (MovieCard, TopBar)
│   ├── navigation      <-- NavHost and Screen Routes
│   └── screens         <-- FULL PAGES (HomeScreen, DetailScreen)
└── TeamApp.kt          <-- The Hilt Application Class
```

## Development Workflow

This project is set up as a foundation. Here is how you contribute features:

### Adding a New Feature

1.  **Define Domain:** Create the Model and Repository Interface in `domain/`.
2.  **Implement Data:** Create the DTOs in `data/remote` and Entities in `data/local`. Implement the Repository in `data/repository`.
3.  **Create UI:** Build the Screen in `ui/screens` and ViewModel.
4.  **Register Route:** Add the new screen to `ui/navigation/AppNavigation.kt`.
5.  **Inject:** Provide any necessary dependencies in `di/` modules.

### Database Management

We use **Room** for local storage.
*   **Entities:** defined in `data/local/MovieEntity.kt`.
*   **DAOs:** defined in `data/local/MovieDao.kt`.
*   **Migrations:** If you modify an Entity, update the database version in `MovieDatabase.kt` and provide a migration strategy.

## Configuration

The project uses a standard `local.properties` file for secret management.

| Variable | Description | Required |
| :--- | :--- | :--- |
| `API_KEY` | Your TMDB v3 API Key | ✅ Yes |
| `sdk.dir` | Path to Android SDK | ✅ Yes |

## Contributing

We welcome contributions from the team!

1.  **Fork** the repository (or create a branch if internal).
2.  **Create a Feature Branch** (`git checkout -b feature/amazing-feature`).
3.  **Commit** your changes (`git commit -m 'Add amazing feature'`).
4.  **Push** to the branch (`git push origin feature/amazing-feature`).
5.  **Open a Pull Request**.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
