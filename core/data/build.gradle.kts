// ───────────────────────────────────────────────────────────────────────────────
// Import Statements
// ───────────────────────────────────────────────────────────────────────────────
import dependencies.defaultLibraries // Imports a function to add default dependencies.
import dependencies.dependencyInjectionHilt
import dependencies.domainModule
import dependencies.networking
import dependencies.preferenceDataStore
import dependencies.roomDatabase
import dependencies.uiModule
import plugs.SharedLibraryGradlePlugin // Imports a custom Gradle plugin for library configuration.

// ───────────────────────────────────────────────────────────────────────────────
// Apply Plugins
// ───────────────────────────────────────────────────────────────────────────────
plugins {
  /**
   * `ANDROID_LIBRARY` - Applies the Android Library plugin.
   * - Required for building **Android libraries (`.aar` files)**.
   * - Unlike an application module, it **does not generate an APK**.
   * - Example usage:
   *   ```kotlin
   *   plugins {
   *       id("com.android.library")
   *   }
   *   ```
   */
  id(plugs.BuildPlugins.ANDROID_LIBRARY)

  // other plugins - order is important
  id(plugs.BuildPlugins.KAPT)
  id(plugs.BuildPlugins.DAGGER_HILT) version plugs.BuildPlugins.DAGGER_HILT_VERSION_NUMBER
  id(plugs.BuildPlugins.MAVEN_PUBLISH)
}

/**
 * `apply<SharedLibraryGradlePlugin>()`
 * - Applies the **custom Gradle plugin** (`SharedLibraryGradlePlugin.kt`).
 * - Automates the setup of **build types, signing, flavors, and Kotlin options**.
 * - Ensures **consistent configurations** across all library modules.
 * - Example usage:
 *   ```kotlin
 *   apply<SharedLibraryGradlePlugin>()
 *   ```
 */
apply<SharedLibraryGradlePlugin>()

// ───────────────────────────────────────────────────────────────────────────────
// Android Configuration
// ───────────────────────────────────────────────────────────────────────────────
android {
  /**
   * **Namespace Declaration**
   * - Specifies the **unique package name** for the library.
   * - Helps **prevent class name conflicts** between different libraries.
   * - Example usage:
   *   ```kotlin
   *   namespace = "com.example.my_library"
   *   ```
   */
  namespace = "com.samir.bluearchitecture.data"
}

// ───────────────────────────────────────────────────────────────────────────────
// Dependency Management
// ───────────────────────────────────────────────────────────────────────────────
dependencies {
  /**
   * `defaultLibraries()`
   * - Calls a function that **automatically adds required dependencies**.
   * - Reduces manual dependency management.
   * - Example usage in `Dependencies.kt`:
   *   ```kotlin
   *   fun DependencyHandler.defaultLibraries() {
   *       implementation("androidx.core:core-ktx:1.9.0")
   *       implementation("androidx.appcompat:appcompat:1.6.1")
   *   }
   *   ```
   */
  defaultLibraries()

  /**
   * `dependencyInjectionHilt()`
   *
   * Adds Hilt dependencies required for setting up dependency injection across the application.
   *
   * ### Included Dependencies:
   * - `DAGGER_HILT_ANDROID`: Core Hilt library for Android dependency injection.
   * - `DAGGER_HILT_COMPILER`: Annotation processor for Hilt's internal code generation.
   * - `ANDROID_HILT_COMPILER`: Compiler for AndroidX Hilt integrations (e.g., `@HiltViewModel`).
   *
   * > Optional (commented out):
   * - `hiltLifeCycleViewModel`: Extension library for integrating Hilt with ViewModel lifecycle (can be included when needed).
   *
   * ### Purpose:
   * - Simplifies and unifies dependency management using Hilt.
   * - Eliminates boilerplate DI code and enhances testability.
   *
   * ### Example Usage:
   * ```kotlin
   * fun DependencyHandler.dependencyInjectionHilt() {
   *     implementation(Dependencies.DAGGER_HILT_ANDROID)
   *     // implementation(Dependencies.hiltLifeCycleViewModel)
   *     kapt(Dependencies.DAGGER_HILT_COMPILER)
   *     kapt(Dependencies.ANDROID_HILT_COMPILER)
   * }
   * ```
   *
   * > Tip: Ensure the Hilt Gradle plugin is applied in your root or module-level `build.gradle.kts`:
   * ```kotlin
   * plugins {
   *     id("dagger.hilt.android.plugin")
   *     kotlin("kapt")
   * }
   * ```
   */
  dependencyInjectionHilt()

  /**
   * `networking()`
   *
   * Adds core networking libraries required for API communication using Retrofit and OkHttp.
   *
   * ### Included Dependencies:
   * - `RETROFIT_SQUARE_UP`: Core Retrofit library for HTTP communication.
   * - `RETROFIT_SQUARE_UP_CONVERTER_GSON`: Gson converter for JSON serialization/deserialization.
   * - `OKHTTP_SQUARE_UP`: OkHttp client for efficient and customizable HTTP calls.
   * - `INTERCEPTOR_SQUARE_UP`: Logging interceptor for monitoring network traffic.
   *
   * ### Purpose:
   * - Promotes a consistent and centralized setup for networking across the app.
   * - Encourages separation of concerns by abstracting dependency definitions.
   *
   * ### Example Usage:
   * ```kotlin
   * fun DependencyHandler.networking() {
   *     implementation(Dependencies.RETROFIT_SQUARE_UP)
   *     implementation(Dependencies.RETROFIT_SQUARE_UP_CONVERTER_GSON)
   *     implementation(Dependencies.OKHTTP_SQUARE_UP)
   *     implementation(Dependencies.INTERCEPTOR_SQUARE_UP)
   * }
   * ```
   *
   * > Note: Ensure ProGuard/R8 rules are properly configured for Retrofit and OkHttp in release builds.
   */
  networking()

  domainModule()
  uiModule()

  roomDatabase()

  preferenceDataStore()
}
