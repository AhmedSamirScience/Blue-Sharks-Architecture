// ───────────────────────────────────────────────────────────────────────────────
// Import Statements
// ───────────────────────────────────────────────────────────────────────────────
import dependencies.coroutines
import dependencies.dataModule
import dependencies.defaultLibraries // Imports a function to add default dependencies.
import dependencies.dependencyInjectionHilt
import dependencies.domainModule
import dependencies.navigationComponent
import dependencies.networking
import dependencies.presentationModule
import plugs.SharedLibraryGradlePlugin // Imports a custom Gradle plugin for library configuration.

// ───────────────────────────────────────────────────────────────────────────────
// Apply Plugins
// ───────────────────────────────────────────────────────────────────────────────
plugins {
  /**
   * `KOTLIN_ANDROID` - Enables **Kotlin support** in the Android module.
   * - Required when using Kotlin source files.
   * - Also required for plugins like `androidx.navigation.safeargs.kotlin`.
   * - Defined in `plugs.BuildPlugins` as `"org.jetbrains.kotlin.android"`.
   * - Example usage:
   *   ```kotlin
   *   plugins {
   *       id(BuildPlugins.KOTLIN_ANDROID)
   *   }
   *   ```
   */
  id(plugs.BuildPlugins.KOTLIN_ANDROID)

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

  id(plugs.BuildPlugins.SAFE_ARGS)

  // other plugins - order is important
  id(plugs.BuildPlugins.KAPT)
  id(plugs.BuildPlugins.DAGGER_HILT) version plugs.BuildPlugins.DAGGER_HILT_VERSION_NUMBER
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
  namespace = "com.samir.bluearchitecture.remotedata"

  buildFeatures {
    dataBinding = true
  }
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

  navigationComponent()

  presentationModule()

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

  coroutines()

  networking()

  dataModule()
  domainModule()
}
