// Defines the package `tests`, grouping all test-related constants.
package tests

/**
 * `TestBuildConfig` is an object that stores **test configuration constants**.
 * - This ensures a **consistent and reusable** test setup across multiple build types.
 * - Helps avoid **hardcoding values** in `build.gradle.kts`.
 */
object TestBuildConfig {

    /**
     * `TEST_INSTRUMENTATION_RUNNER` - Defines the **default test runner** for Android instrumented tests.
     * - This runner is responsible for executing Android tests on a device or emulator.
     * - Uses **AndroidJUnitRunner**, which allows running JUnit-based Android tests.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   defaultConfig {
     *       testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
     *   }
     *   ```
     */
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}
