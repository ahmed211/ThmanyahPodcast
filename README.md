# Thmanyah Android Assignment

Android application implementing a dynamic home screen and search feature using Kotlin, Jetpack Compose, MVVM, and Clean Architecture.

The application fetches content sections dynamically from APIs and renders them in different layouts depending on the section type. The architecture focuses on feature isolation, reusable UI components, and scalable design.

## Features

### Home Screen
- Fetches dynamic sections from API
- Supports multiple section layouts:
  - Big square cards
  - Square grid
  - Horizontal queue list
  - Two-line grid
- Sections are rendered dynamically based on the API response
- Infinite vertical scrolling implemented natively via Paging 3

### Search Screen
- Real-time search using API
- Debounced search (200ms) to avoid unnecessary requests
- Cancels previous search requests when typing
- Reuses the same UI components used in the Home screen
- Infinite vertical scrolling implemented natively via Paging 3

## Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **MVVM Architecture**
- **Clean Architecture**
- **Hilt** (Dependency Injection)
- **Retrofit** (Networking)
- **OkHttp**
- **Coroutines + Flow**
- **Gson**
- **Paging 3**
- **Coil**

## Architecture Overview

The project follows Feature-First Clean Architecture.

Each feature is isolated and contains its own:
- Data layer
- Domain layer
- Presentation layer

Shared components and models are placed in a `core` package.

This approach provides:
- Clear separation of concerns
- Feature isolation
- Scalability
- Reusability

## Project Structure
```
com.thmanyah.thmanyah
│
├── core
│   ├── data
│   │   ├── dto
│   │   │   └── SectionDto.kt
│   │   └── mapper
│   │       └── SectionMapper.kt
│   │
│   ├── di
│   │   └── NetworkModule.kt
│   │
│   ├── model
│   │   └── DomainSection.kt
│   │
│   ├── navigation
│   │   └── AppNavigation.kt
│   │
│   └── ui
│       ├── components
│       │   ├── SectionView.kt
│       │   ├── ContentCard.kt
│       │   ├── LoadingStateView.kt
│       │   ├── ErrorStateView.kt
│       │   └── EmptyStateView.kt
│       └── theme
│
├── home
│   ├── data
│   │   ├── remote
│   │   │   └── HomeApiService.kt
│   │   └── repository
│   │       └── HomeRepositoryImpl.kt
│   │
│   ├── di
│   │   └── HomeModule.kt
│   │
│   ├── domain
│   │   ├── repository
│   │   │   └── HomeRepository.kt
│   │   └── usecase
│   │       └── GetHomeSectionsUseCase.kt
│   │
│   └── presentation
│       ├── HomeScreen.kt
│       └── HomeViewModel.kt
│
├── search
│   ├── data
│   │   ├── remote
│   │   │   └── SearchApiService.kt
│   │   └── repository
│   │       └── SearchRepositoryImpl.kt
│   │
│   ├── di
│   │   └── SearchModule.kt
│   │
│   ├── domain
│   │   ├── repository
│   │   │   └── SearchRepository.kt
│   │   └── usecase
│   │       └── SearchUseCase.kt
│   │
│   └── presentation
│       ├── SearchScreen.kt
│       └── SearchViewModel.kt
│
├── MainActivity.kt
└── ThmanyahApp.kt
```

## Layer Responsibilities

### Data Layer
Responsible for:
- API communication
- DTO models
- Repository implementations
- Mapping DTOs to domain models

### Domain Layer
Contains business logic including:
- Domain models
- Repository interfaces
- Use cases

The domain layer is pure Kotlin and independent of the Android framework.

### Presentation Layer
Responsible for:
- ViewModels
- Compose screens
- UI State Hoisting (`HomeScreen` vs `HomeScreenContent`)
- Handling dynamic Jetpack Paging `LoadStates`

Each feature contains its own presentation logic.


## Dynamic Section Rendering

The UI is server-driven.

The API defines:
- Section type
- Layout type
- Content type
- Order

The shared component `SectionView` dynamically renders the appropriate layout.


## Shared UI Components

Reusable UI components are placed inside:
`core/ui/components`


These components are used across both Home and Search features. They contain `@Preview` functions allowing instant mock visualizations.

## Networking

The application uses:
- Retrofit
- OkHttp
- Gson

Generic networking is configured inside:
`core/di/NetworkModule.kt`

The module provides:
- `OkHttpClient`
- `Retrofit` instances

Feature-specific API services and repositories are provided in their respective modules (`HomeModule.kt`, `SearchModule.kt`).

Two base URLs are used:
- **Home API:** `https://api-v2-b2sit6oh3a-uc.a.run.app/home_sections`
- **Search API:** `https://mock.apidog.com/m1/735111-711675-default/search`

## Dependency Injection

Dependency Injection is handled using Hilt.

Example provided dependencies:
- Retrofit clients
- API services
- Repository implementations

All core dependencies are scoped to:
`SingletonComponent`

## Search Optimization

Search input is implemented using Kotlin Flow debounce.

Features:
- Waits 200ms after user stops typing
- Prevents unnecessary network requests using `.distinctUntilChanged()`
- Cancels previous requests when a new query is entered leveraging Paging 3's flow collection.

## Automated Tests

The application includes robust testing suites:
- **Unit Tests:** Verified UseCases, Repository layers, and ViewModel state transitions using MockK and Coroutines Test Dispatchers (`HomeViewModelTest`, `SearchViewModelTest`).
- **UI Tests:** Compose Instrumented testing (`HomeScreenTest`, `SearchScreenTest`) utilizing Jetpack Compose Testing APIs. We achieved robust mock data rendering by creating Stateless versions of the screens (`HomeScreenContent`) to completely bypass Dagger Hilt complexity and strictly verify Paging rendering fidelity natively on-device.

## Possible Improvements

Potential improvements for production environments:
- **Pagination Local Caching:** Implement Room Database for offline caching of home sections.
