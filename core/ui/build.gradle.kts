// ───────────────────────────────────────────────────────────────────────────────
// Import Statements
// ───────────────────────────────────────────────────────────────────────────────
import dependencies.applyScalableDimensions
import dependencies.defaultLibraries // Imports a function to add default dependencies.
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
  namespace = "com.samir.bluearchitecture.ui"
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
   * 🍧 `applyScalableDimensions()` - Scalable Design Margins & Text Sizes 🍧
   *
   * ▶️ **Purpose:**
   * - Integrates **scalable margin and text size libraries** using `sdp-android` and `ssp-android`.
   * - Ensures **consistent spacing and typography** across different screen sizes.
   * - Helps maintain a **responsive and adaptive UI** without hardcoded pixel values.
   *
   * ▶️ **How It Works:**
   * - Adds dependencies for:
   *   - **`sdp-android`** → Scalable **dimension** pixels (margins, paddings, etc.).
   *   - **`ssp-android`** → Scalable **sp** for text sizes.
   * - Dynamically adjusts UI elements based on the **screen size and density**.
   *
   * ▶️ **Example Usage in XML (Scalable Margins & Paddings):**
   * ```xml
   * <TextView
   *     android:layout_width="wrap_content"
   *     android:layout_height="wrap_content"
   *     android:text="Hello World"
   *     android:layout_marginStart="@dimen/_8sdp"
   *     android:layout_marginTop="@dimen/_16sdp"
   *     android:textSize="@dimen/_14ssp"/>
   * ```
   *
   * ▶️ **Example Usage in Kotlin:**
   * ```kotlin
   * val margin = resources.getDimensionPixelSize(R.dimen._8sdp)
   * textView.setPadding(margin, margin, margin, margin)
   * ```
   *
   * ▶️ **Why Use This?**
   * ✅ Eliminates hardcoded pixel values.
   * ✅ Ensures **scalability** across various screen sizes.
   * ✅ Improves **readability and maintainability** of UI code.
   * ✅ Follows **Material Design guidelines** for adaptive layouts.
   *
   * ▶️ **Implementation in Dependencies File:**
   * ```kotlin
   * //region design margins
   * const val INTUIT_SDB = "com.intuit.sdp:sdp-android:${DependenciesVersions.DESIGN_MARGINS}"
   * const val INTUIT_SSP = "com.intuit.ssp:ssp-android:${DependenciesVersions.DESIGN_MARGINS}"
   * //endregion
   * ```
   *
   * ▶️ **How to Apply in `dependencies.gradle.kts`:**
   * ```kotlin
   * fun DependencyHandler.applyScalableDimensions() {
   *     implementation(INTUIT_SDB)
   *     implementation(INTUIT_SSP)
   * }
   * ```
   */
  applyScalableDimensions()
}
