// Defines the package `plugs`, grouping all build-related plugin constants.
package plugs

/**
 * `BuildPlugins` is an object that stores **Gradle plugin identifiers**.
 * - These identifiers are used in `build.gradle.kts` files.
 * - Centralizing them prevents **typos**, ensures **consistency**, and makes upgrades easier.
 */
object BuildPlugins {

    /**
     * `KOTLIN_ANDROID` - Enables **Kotlin support** in the Android project.
     * - Required for writing Android apps in Kotlin.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.KOTLIN_ANDROID)
     *   }
     *   ```
     */
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"

    /**
     * `ANDROID_APPLICATION` - Applies the **Android Application Plugin**.
     * - Used for Android app modules.
     * - Required for building and packaging an APK/AAB.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.ANDROID_APPLICATION)
     *   }
     *   ```
     */
    const val ANDROID_APPLICATION = "com.android.application"

    /**
     * `ANDROID` - A general reference to the **Android Plugin**.
     * - Might be used for flexibility when applying Android-specific logic.
     */
    const val ANDROID = "android"

    /**
     * `ANDROID_LIBRARY` - Applies the **Android Library Plugin**.
     * - Used for creating Android libraries (`.aar` files).
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.ANDROID_LIBRARY)
     *   }
     *   ```
     */
    const val ANDROID_LIBRARY = "android-library"

    // ───────────────────────────────────────────────────────────────────────────────
    // Dependency Injection (DI) Plugins
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `KAPT` - Enables **Kotlin Annotation Processing**.
     * - Required for libraries like **Dagger Hilt, Room, and Glide**.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.KAPT)
     *   }
     *   ```
     */
    const val KAPT = "org.jetbrains.kotlin.kapt"

    /**
     * `HILT` - Applies the **Dagger Hilt Plugin** for dependency injection.
     * - Required when using Hilt in an Android project.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.HILT)
     *   }
     *   ```
     */
    const val HILT = "com.google.dagger.hilt.android"

    /**
     * `DAGGER_HILT` - Another constant reference to the **Dagger Hilt Plugin**.
     * - Used for better organization in dependency configurations.
     */
    const val DAGGER_HILT = "com.google.dagger.hilt.android"

    /**
     * `DAGGER_HILT_VERSION_NUMBER` - Specifies the **version** of the Dagger Hilt plugin.
     * - Used to ensure all modules use the same version.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   dependencies {
     *       implementation("com.google.dagger:hilt-android:${BuildPlugins.DAGGER_HILT_VERSION_NUMBER}")
     *   }
     *   ```
     */
    const val DAGGER_HILT_VERSION_NUMBER = "2.49"

    // ───────────────────────────────────────────────────────────────────────────────
    // Code Quality and Static Analysis Plugins
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `KTLINT` - Applies the **Kotlin Lint Plugin** for enforcing coding style.
     * - Helps catch **formatting errors** automatically.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.KTLINT)
     *   }
     *   ```
     */
    const val KTLINT = "ktlint-settings"

    /**
     * `SPOTLESS` - Applies **Spotless**, a tool for automatic code formatting.
     * - Can be used for both **Kotlin and Java** projects.
     */
    const val SPOTLESS = "spotless-settings"

    /**
     * `DETEKT` - Applies the **Detekt Plugin** for static code analysis in Kotlin.
     * - Helps identify **code smells, complexity issues, and security vulnerabilities**.
     */
    const val DETEKT = "detekt-settings"

    // ───────────────────────────────────────────────────────────────────────────────
    // Dependency Management Plugins
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `UPDATE_DEPS_VERSIONS` - Automates **dependency version updates**.
     * - Used to check for newer versions of dependencies.
     */
    const val UPDATE_DEPS_VERSIONS = "update-dependencies"

    /**
     * `SAFE_ARGS` - Applies the **Safe Args Kotlin Plugin** for Jetpack Navigation.
     * - Generates **type-safe classes** for navigating and passing arguments between destinations.
     * - Reduces the risk of runtime errors due to incorrect argument types or missing data.
     * - This is the **Kotlin-specific** version of Safe Args.
     * - Requires the `KOTLIN_ANDROID` plugin to be applied **before** this plugin.
     * - Should be used in modules that utilize **Jetpack Navigation** and are written in **Kotlin**.
     *
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   plugins {
     *       id(BuildPlugins.KOTLIN_ANDROID)
     *       id(BuildPlugins.SAFE_ARGS)
     *   }
     *   ```
     */
    const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"


    /**
     * `MAVEN_PUBLISH` - Applies the **Maven Publish Plugin** to the module.
     *
     * - Enables publishing of the module as a **Maven-compatible artifact** (e.g., AAR or JAR).
     * - Commonly used to publish Android library modules to:
     *   - **Local Maven repository** (`mavenLocal()`)
     *   - **Remote Maven repositories** (e.g., GitHub Packages, JitPack, Nexus, JFrog Artifactory)
     *
     * ---
     * ### ✅ Key Features:
     * - Supports publishing different **build variants** (e.g., `debug`, `release`, or flavored variants).
     * - Allows inclusion of **source JARs**, **JavaDocs**, and **custom POM metadata**.
     * - Plays well with `singleVariant(...)` and variant-aware publishing for Android libraries.
     *
     * ---
     * ### ⚠️ Requirements:
     * - Must be applied **alongside** the `com.android.library` plugin in Android modules.
     * - Requires a `PublishingExtension` configuration to define artifacts, groupId, artifactId, and version.
     * - Should declare a `MavenPublication` per variant you want to publish (e.g., `clientGoogleDebug`).
     *
     * ---
     * ### 📦 Example usage in `build.gradle.kts`:
     * ```kotlin
     * plugins {
     *     id("com.android.library")
     *     id(BuildPlugins.MAVEN_PUBLISH)
     * }
     *
     * apply<SharedLibraryGradlePlugin>() // Custom plugin that registers variant(s) for publishing
     * ```
     *
     * ---
     * ### 🛠️ Example Publishing Task:
     * ```bash
     * ./gradlew :core:data:publishClientGoogleDebugPublicationToMavenLocal
     * ```
     *
     * ---
     * ### 💡 Tip:
     * - To resolve internal project dependencies cleanly during publishing, use:
     *   ```kotlin
     *   implementation(project(mapOf("path" to ":core:domain", "configuration" to "clientGoogleDebugRuntimeElements")))
     *   ```
     */
    const val MAVEN_PUBLISH = "maven-publish"
}
