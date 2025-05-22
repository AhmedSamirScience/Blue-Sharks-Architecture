/***
 * ((Root))
 * This file is used to define the dependencies for the buildSrc module
 */
plugins {
    /**
     * plugins { kotlin-dsl }
     * - This applies the Kotlin DSL (Domain-Specific Language) plugin.
     * - The Kotlin DSL allows you to write Gradle build scripts in Kotlin instead of Groovy, making them type-safe and easier to maintain.
     */
    `kotlin-dsl`
}

repositories {
    /**
     * repositories {} Block
     * Repositories specify where Gradle should look for dependencies when resolving them:
     *
     * - google(): Fetches dependencies from Google's repository (e.g., Android Gradle Plugin, Jetpack libraries).
     * - mavenCentral(): Fetches dependencies from Maven Central, a large repository hosting open-source libraries.
     * - gradlePluginPortal(): Used to resolve Gradle plugins from the Gradle Plugin Portal.
     */
    google()
    mavenCentral()
    gradlePluginPortal()
}


dependencies {
    /**
     * da badal el plugins block le kol el modules w tl3'i b2a el build.gradle.kts lel base module
     * ((((Direct Plugin IDs Are More Flexible Using direct plugin IDs from buildSrc gives you more control))))
     * dependencies {} Block
     * This block defines the dependencies that your module needs.
     *
     * Why Use buildSrc for Dependencies?
     * Using buildSrc for dependency management brings multiple benefits:
     *
     * - Centralized Versioning → Avoids defining versions in multiple places.
     * - Better Maintainability → Upgrading dependencies is easier.
     * - Type Safety → Kotlin DSL helps catch errors at compile time.
     * - Less Duplication → Avoids repeating the same dependencies across build.gradle.kts files.
     */

    /**
     * - This is a Kotlin Gradle Plugin.
     * - It allows you to use Kotlin features in your Gradle build scripts.
     * - The api keyword makes the dependency available to consumers of the buildSrc module.
     */
    api(kotlin("gradle-plugin:1.9.0"))


    /**
     * - This is the Android Gradle Plugin (AGP), which is required for building Android projects.
     * - It provides tools for compiling, packaging, and deploying Android applications.
     * - Version 8.4.1 is the latest (as of now), ensuring compatibility with newer Android Studio and Gradle features.
     * Note: The AGP version must match the Gradle version to ensure compatibility.
     * It provides essential tools for:
     *                      - Compiling and building Android apps.
     *                      - Dexing, signing, and packaging APKs/AABs.
 *                          - Connecting Android Studio with Gradle.
     */
    implementation("com.android.tools.build:gradle:8.4.1")

    /**
     * - This is the Kotlin Gradle Plugin, needed for projects using Kotlin.
     * - It enables Kotlin support in Gradle and ensures that Kotlin code can be compiled properly.
     * - Without this, Gradle would n’t understand how to compile Kotlin source files.
     */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")

    /**
     * - Spotless is a powerful code formatting tool for Gradle projects.
     * - It ensures consistent code style by automatically formatting source files.
     * - Supports multiple languages, including Kotlin, Java, XML, and more.
     *
     * Why Use Spotless?
     * - 🚀 Automatically formats code to maintain a clean and uniform style.
     * - 🛠 Integrates with Kotlin, Java, XML, and other languages.
     * - 🔄 Helps enforce coding standards and eliminates manual formatting efforts.
     * - ✅ Prevents formatting-related issues in pull requests.
     *
     * Version: 6.22.0 → Provides the latest features and bug fixes.
     * Official Documentation: https://github.com/diffplug/spotless
     */
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.22.0")

    /**
     * 🛡️ Detekt - Static Code Analysis for Kotlin
     * - Detekt is a **static code analysis tool** that helps identify code smells, complexity issues, and potential bugs.
     * - It enforces best practices and ensures cleaner Kotlin code.
     * - Can be integrated into Gradle to automatically check code quality.
     *
     * 🔹 Why Use Detekt?
     * - 🚀 Improves code maintainability by detecting complex or inefficient code.
     * - 🔄 Supports customizable rules for enforcing coding standards.
     * - ✅ Generates detailed reports with detected issues.
     *
     * 🔗 Official Documentation: https://detekt.dev/
     */
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")

    /**
     * 📌 Gradle Versions Plugin - Keep Dependencies Up to Date
     * - The **Gradle Versions Plugin** is a tool that helps track outdated dependencies and suggests updates.
     * - It scans the project's dependencies and plugins to find newer versions.
     * - Helps ensure your project stays up to date with the latest stable releases.
     *
     * 🔹 Why Use the Gradle Versions Plugin?
     * - 🚀 Automates the process of checking for dependency updates.
     * - 🔄 Reduces security risks by identifying outdated libraries.
     * - 📊 Generates detailed reports on which dependencies need updating.
     * - ✅ Supports filtering by stable releases to avoid unstable versions.
     *
     * 🔗 Official Documentation: https://github.com/ben-manes/gradle-versions-plugin
     */
    implementation("com.github.ben-manes:gradle-versions-plugin:0.51.0")

    /**
     * 📌 Navigation Safe Args Plugin - Type-Safe Argument Passing
     *
     * 🔹 **What is Safe Args?**
     * - Safe Args is a Gradle plugin that generates **type-safe navigation arguments** for the Jetpack Navigation component.
     * - It eliminates the need for manually passing arguments via Bundles, reducing boilerplate code.
     *
     * 🔹 **Why Use Safe Args?**
     * - ✅ **Type Safety:** Prevents runtime crashes due to incorrect argument types.
     * - ✅ **Improves Readability:** Arguments are passed via generated classes instead of raw Bundles.
     * - ✅ **Easy Integration:** Works seamlessly with Jetpack Navigation.
     *
     * 🔹 **How It Works**
     * 1. Define arguments in your navigation graph (`nav_graph.xml`).
     * 2. Safe Args generates a `Directions` class with methods for type-safe navigation.
     * 3. Use these generated classes to navigate with arguments safely.
     *
     * 🔹 **Example Usage**
     *
     * 📌 **Define Arguments in `nav_graph.xml`**
     * ```xml
     * <fragment
     *     android:id="@+id/detailsFragment"
     *     android:name="com.example.DetailsFragment">
     *     <argument
     *         android:name="userId"
     *         app:argType="string" />
     * </fragment>
     * ```
     *
     * 📌 **Navigate Using Safe Args**
     * ```kotlin
     * val action = HomeFragmentDirections.actionHomeToDetails(userId = "123")
     * findNavController().navigate(action)
     * ```
     *
     * 📌 **Retrieve Arguments in Fragment**
     * ```kotlin
     * val args: DetailsFragmentArgs by navArgs()
     * val userId = args.userId // Type-safe retrieval
     * ```
     *
     * 🔹 **Dependency**
     * - Safe Args plugin must be applied in the project-level `build.gradle.kts`:
     * ```gradle
     * plugins {
     *     id("androidx.navigation.safeargs.kotlin") version "2.7.7"
     * }
     * ```
     *
     * - Add this dependency in `buildSrc` or module `build.gradle.kts`:
     * ```gradle
     * implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
     * ```
     *
     * 🔗 **Official Documentation:**
     * [Navigation Safe Args](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args)
     */
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")

}