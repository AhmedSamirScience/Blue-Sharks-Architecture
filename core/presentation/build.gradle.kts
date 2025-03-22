// ───────────────────────────────────────────────────────────────────────────────
// Import Statements
// ───────────────────────────────────────────────────────────────────────────────
import dependencies.defaultLibraries // Imports a function to add default dependencies.
import dependencies.navGraph
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
  namespace = "com.samir.bluearchitecture.presentation"

  /**
   * **Enable Data Binding**
   * - `dataBinding = true` enables **Android Data Binding** in the module.
   * - Data Binding allows you to bind UI components in XML layouts directly to data sources.
   * - Eliminates the need for `findViewById()`, improving code readability and performance.
   * - Required for using `DataBindingUtil`, `ViewDataBinding`, and `BindingAdapters`.
   *
   * **Example Use Case:**
   * 1. Declare a `<layout>` tag in your XML:
   *    ```xml
   *    <layout xmlns:android="http://schemas.android.com/apk/res/android">
   *        <data>
   *            <variable name="user" type="com.example.User" />
   *        </data>
   *        <TextView android:text="@{user.name}" />
   *    </layout>
   *    ```
   * 2. Access the binding object in your Activity/Fragment:
   *    ```kotlin
   *    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
   *    binding.user = User("John Doe")
   *    ```
   *
   * **Note:**
   * - If using ViewBinding instead of Data Binding, use `viewBinding = true` instead.
   * - Data Binding is useful for MVVM architecture and working with LiveData.
   */
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

  /**
   * `navGraph()`
   * - Calls a function that **adds dependencies for the Android Navigation Component**.
   * - Ensures that the app has **Navigation support for fragments and UI components**.
   * - Typically includes:
   *   - `navigation-fragment-ktx` → Required for `NavController`
   *   - `navigation-ui-ktx` → Helps integrate with `BottomNavigationView` and `Toolbar`
   * - Example usage in `Dependencies.kt`:
   *   ```kotlin
   *   fun DependencyHandler.navGraph() {
   *       implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
   *       implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
   *   }
   *   ```
   */
  navGraph()

  uiModule()
}
