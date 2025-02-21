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



}