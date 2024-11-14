# Android-CleanArchitecture-Compose-App(Movies App)

This repository is an Android application built using **Clean Architecture** principles, structured to promote scalability, testability, and separation of concerns. The application is divided into three main layers: `data`, `domain`, and `ui`, along with a `di` (Dependency Injection) module for managing dependencies.

## Project Structure

The project structure is designed to ensure each layer has a clear role:

- **domain**: Defines abstract implementations of core functionalities.
- **data**: Implements the `domain` layer interfaces and provides data management logic.
- **ui**: Contains all UI-related components, navigation, and screen-related ViewModels.
- **di**: Handles Dependency Injection to manage dependencies between layers.

---

### 1. `domain` Layer

The `domain` layer is the core of the application. It contains abstract implementations of the project’s core requirements and business logic. This layer has no dependency on Android frameworks, making it independent and easily testable.

#### Folders

- **common**
    - `Result`: A utility class for handling success and error states consistently throughout the app.
- **datasource**
    - **local**
        - **dao**: Defines the database access objects (DAO) required by the local data source.
            - `MovieDAO`: Interface for local database operations specific to movies.
        - **entities**: Contains the data entities representing the database tables.
            - `Movie`: Represents a movie entity in the local database.
        - **util**: Provides utilities and converters for the local database.
            - `RoomDataTypeConverters`: Custom type converters for the Room database.
        - `AppDatabase.kt`: Sets up the Room database for the application.
        - `MoviesLocalDataSource`: Abstract interface for local data access methods.
    - **network**
        - **datasource**
            - `BaseNetworkDatasource.kt`: Base class to handle common network-related functionalities.
            - `MoviesNetworkDataSource`: Abstract interface for fetching data from remote sources.
        - `ApiService`: Defines the API endpoints and related methods for network operations.
        - `MoviesDataSource`: General abstraction for data sources (both local and network).
- **model**
    - `ImageData`: Represents image data associated with movies.
- **repository**
    - `MoviesRepository`: Abstract interface that serves as the main contract for data access. It provides the necessary methods to retrieve movie data from different sources.

---

### 2. `data` Layer

The `data` layer contains implementations of the abstractions defined in the `domain` layer. It includes the logic for accessing local and remote data sources, as well as the repository implementation that the `ui` layer interacts with.

#### Folders

- **datasource**
    - **local**: Implements the local data source functionalities using Room and other local storage options.
        - `MoviesLocalDataSourceImpl`: Concrete implementation of `MoviesLocalDataSource`.
    - **network**
        - **apiclient**: Contains network client implementations.
            - `ApiServiceImpl`: Implementation of the `ApiService` interface for network operations.
            - `MoviesNetworkDataSourceImpl`: Concrete implementation of `MoviesNetworkDataSource`, fetching data from APIs.
- **repository**
    - `MoviesRepositoryImpl.kt`: Implements `MoviesRepository` interface and integrates data from both local and remote sources to provide a single source of truth.

---

### 3. `ui` Layer

The `ui` layer is responsible for the application's user interface, including all screen components, navigation logic, and UI-specific ViewModels. This layer interacts only with the `domain` layer, following the principle of dependency inversion.

#### Folders

- **components**: Reusable UI components shared across multiple screens.
    - `AppHeader.kt`: Component for displaying the app's header.
    - `LoaderComponent.kt`: Component for showing loading states.
    - `MovieItemComponent.kt`: Component to display individual movie items in a list.
- **navigation**
    - `HomeNavigation.kt`: Handles the navigation setup between different screens.
    - `Navigate`: Defines and manages navigation actions across the app.
- **screens**
    - **details**: Components and ViewModels for the movie details screen.
        - `MovieDetailsScreen.kt`: UI for displaying detailed movie information.
        - `MovieDetailsViewModel`: Manages UI-related data for the details screen.
    - **list**: Components and ViewModels for the movie list screen.
        - `MoveListScreen.kt`: UI for displaying a list of movies.
        - `MovieListViewModel`: Manages UI-related data for the list screen.
- **stateHolders**
    - `StateHolder`: Holds and manages UI state across multiple components.
- **theme**
    - `Color.kt`: Defines the color palette used in the app.
    - `Theme.kt`: Configures the app's theme settings.
    - `Type.kt`: Defines the typography styles.
    - `MoviesApp.kt`: Main entry point of the app, setting up the app's theme and initializing the navigation graph.

---

### 4. `di` (Dependency Injection) Module

The `di` module is a project-level folder that configures Dependency Injection using Dagger or Hilt (or another DI library). It provides a single source for initializing dependencies, enabling easy swapping of implementations and enhancing testability.

This module ensures that dependencies are injected into components across all layers, including `data`, `domain`, and `ui`, following the Clean Architecture guidelines.

---

## Project Principles

- **Separation of Concerns**: Each layer has a specific responsibility, isolating business logic, data management, and user interface concerns.
- **Dependency Inversion**: Higher-level modules (`ui` layer) depend on abstractions defined in the `domain` layer, not on lower-level modules.
- **Single Source of Truth**: The `repository` in the `data` layer consolidates data from local and network sources, providing a unified data source for the app.

