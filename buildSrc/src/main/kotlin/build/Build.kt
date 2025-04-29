// Defines a package named `build`, which logically groups related build configuration classes.
package build

// Declares an abstract/sealed class named `Build`, which will be the base class for different build variants.
sealed class Build {
    // Common properties that will be inherited by all subclasses

    /**
     * Determines whether resource shrinking is enabled.
     *
     * - `true`: Enables resource shrinking using R8 or ProGuard. Unused resources (e.g., images, strings, layouts)
     *   will be removed from the final APK or AAB, reducing the app size.
     * - `false`: Keeps all declared resources, even if unused.
     *
     * ⚠️ Resource shrinking typically requires `isMinifyEnabled = true` to be effective,
     *    since the shrinker relies on code analysis to determine which resources are unused.
     *
     * 🔍 Recommended for **release builds** where reducing APK size is critical.
     */
    open val isShrinkEnabled = false


    /**
     * Determines whether code shrinking (minification) is enabled.
     * - `true`: Removes unused code and resources to reduce APK size.
     * - `false`: Keeps all code and resources.
     * - Minification is typically enabled in **release builds** to optimize performance.
     */
    open val isMinifyEnabled = false

    /**
     * Determines whether unit test coverage (code coverage reports) should be enabled.
     * - `true`: Generates code coverage reports.
     * - `false`: Skips generating coverage reports (reduces build time).
     * - Code coverage should only be enabled for **testing**, not for production.
     */
    open val enableUnitTestCoverage = false //shouf wenta bt3mel apk bytl3 report lel code coverage wla la2 w tb3an e7na 3yzeno lel test bas msh 3yzeno lel production 3lshan msh 3yzeno ykon feh code coverage w 3lshan wa2t el build yb2a 2a2l tb3an


    /**
     * Specifies whether the build variant should be debuggable.
     * - `true`: Allows debugging via Android Studio, logging, etc.
     * - `false`: Disables debugging (used in production for security reasons).
     */
    open val isDebuggable = false

    /**
     * Adds a suffix to the `applicationId` for different build variants.
     * - Example: `com.example.app.debug` for debug builds.
     * - Used to differentiate APKs for different environments.
     */
    open val applicationIdSuffix = ""

    /**
     * Adds a suffix to the `versionName`, helping to distinguish between different build versions.
     * - Example: `1.0.0-DEBUG` for debug builds.
     * - This makes it clear whether the installed version is a debug or production build.
     */
    open val versionNameSuffix = ""

    // Defines different build configurations using objects that extend `Build`.

    /**
     * Debug build variant:
     * - Used during development.
     * - Includes debugging features and tools.
     * - Enables test coverage.
     * - Appends `.debug` to the `applicationId`.
     */
    object Debug : Build() {
        override val versionNameSuffix = "-DEBUG"   // Adds "-DEBUG" to the version name.
        override val applicationIdSuffix = ".debug" // Sets the application ID suffix to `.debug`.
        override val isMinifyEnabled = false        // No minification (preserves full debugging capabilities).
        override val isDebuggable = true            // Enables debugging features.
        override val enableUnitTestCoverage = true  // Generates test coverage reports.
        override val isShrinkEnabled = false        //disable shrink
    }

    /**
     * Release External QA build variant:
     * - Used for external quality assurance (QA) testing.
     * - Code coverage is enabled to track test coverage.
     * - Debugging is disabled to mimic a production-like environment.
     * - Application ID is suffixed with `.releaseExternalQa`.
     */
    object ReleaseExternalQa : Build() {
        override val versionNameSuffix = "-QA"           // Adds "-QA" to the version name.
        override val applicationIdSuffix = ".releaseExternalQa" // Sets the application ID suffix to `.releaseExternalQa`.
        override val isMinifyEnabled = false             // No minification (easier for testing).
        override val isDebuggable = false               // Debugging is disabled.
        override val enableUnitTestCoverage = true      // Enables test coverage for QA purposes.
        override val isShrinkEnabled = true             //enable shrink
    }

    /**
     * Release build variant:
     * - The final production version of the app.
     * - Minification is enabled to reduce APK size and improve performance.
     * - Debugging is disabled for security reasons.
     * - Unit test coverage is disabled to speed up the build process.
     */
    object Release : Build() {
        override val isMinifyEnabled = true        // Enables minification (removes unused code).
        override val isDebuggable = false         // Disables debugging for security.
        override val enableUnitTestCoverage = false // Disables test coverage to speed up the build.
        override val isShrinkEnabled = true         //enable shrink
    }
}
