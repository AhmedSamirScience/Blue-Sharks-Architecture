// ───────────────────────────────────────────────────────────────────────────────
// Import Statements
// ───────────────────────────────────────────────────────────────────────────────
import dependencies.defaultLibraries // Imports a function to add default dependencies.
import dependencies.navigationComponent
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

  id("androidx.navigation.safeargs.kotlin")
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
  namespace = "com.samir.bluearchitecture.viewmodelcases"

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
}
