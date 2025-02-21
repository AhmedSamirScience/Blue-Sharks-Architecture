// Defines the package `build`, grouping related build configuration files.
package build

/**
 * `BuildTypes` is an object that stores **build type names**.
 * - These names correspond to build types declared in Gradle (`build.gradle.kts`).
 * - Build types define how the application is compiled, optimized, and secured.
 */
object BuildTypes {

    /**
     * `DEBUG` build type:
     * - Used for development and testing.
     * - Enables debugging tools, logging, and test coverage.
     * - **Example usage**:
     *   ```kotlin
     *   buildTypes.getByName(BuildTypes.DEBUG) {
     *       isDebuggable = true
     *       applicationIdSuffix = ".debug"
     *   }
     *   ```
     */
    const val DEBUG = "debug"

    /**
     * `RELEASE` build type:
     * - Used for production builds.
     * - **Optimized for performance**: Enables minification, removes logs, and applies ProGuard.
     * - **Example usage**:
     *   ```kotlin
     *   buildTypes.getByName(BuildTypes.RELEASE) {
     *       isMinifyEnabled = true
     *       proguardFiles("proguard-rules.pro")
     *   }
     *   ```
     */
    const val RELEASE = "release"

    /**
     * `RELEASE_EXTERNAL_QA` build type:
     * - Used for external Quality Assurance (QA) testing.
     * - Allows debugging but does not enable all developer features.
     * - Uses a separate API endpoint for testing (`qa_endpoint`).
     * - **Example usage**:
     *   ```kotlin
     *   buildTypes.getByName(BuildTypes.RELEASE_EXTERNAL_QA) {
     *       isDebuggable = true
     *       applicationIdSuffix = ".qa"
     *   }
     *   ```
     */
    const val RELEASE_EXTERNAL_QA = "releaseExternalQA"
}
