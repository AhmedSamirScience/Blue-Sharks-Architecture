// Defines the package `build`, grouping all build-related constants.
package build

/**
 * `BuildVariables` is an object that stores **custom build-time constants**.
 * - These constants are injected into the `BuildConfig` class via `build.gradle.kts`.
 * - They allow different values based on build type or flavor.
 * - Helps manage API endpoints, app behavior flags, and other configuration values.
 */
object BuildVariables {

    /**
     * `BASE_URL` - Defines the base API URL for network requests.
     * - This is typically overridden per environment (debug, QA, production).
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   buildConfigStringField(BuildVariables.BASE_URL, "https://api.example.com/")
     *   ```
     * - **Example usage in Kotlin code**:
     *   ```kotlin
     *   val apiUrl = BuildConfig.BASE_URL
     *   ```
     */
    const val BASE_URL = "BASE_URL"

    /**
     * `CAN_CLEAR_CACHE` - Determines whether the app can clear cached data.
     * - Used to enable or disable cache clearing in debug vs. production builds.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, "true")
     *   ```
     * - **Example usage in Kotlin code**:
     *   ```kotlin
     *   if (BuildConfig.CAN_CLEAR_CACHE) {
     *       cacheManager.clear()
     *   }
     *   ```
     */
    const val CAN_CLEAR_CACHE = "CAN_CLEAR_CACHE"

    /**
     * `DB_VERSION` - Specifies the database schema version.
     * - Helps determine when migrations should be applied.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   buildConfigIntField(BuildVariables.DB_VERSION, "3")
     *   ```
     * - **Example usage in Kotlin code**:
     *   ```kotlin
     *   val dbVersion = BuildConfig.DB_VERSION
     *   ```
     */
    const val DB_VERSION = "DB_VERSION"

    /**
     * `MAP_KEY` - API key for map services (e.g., Google Maps).
     * - Stored in `BuildConfig` to avoid hardcoding it in the app.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   buildConfigStringField(BuildVariables.MAP_KEY, "AIzaSyXXXXXXX")
     *   ```
     * - **Example usage in Kotlin code**:
     *   ```kotlin
     *   val mapApiKey = BuildConfig.MAP_KEY
     *   ```
     */
    const val MAP_KEY = "MAP_KEY"

    /**
     * `PIN_CERTIFCATE` - Certificate pinning for secure API requests.
     * - Used to verify SSL certificates in network security configurations.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   buildConfigStringField(BuildVariables.PIN_CERTIFCATE, "sha256/...")
     *   ```
     * - **Example usage in Kotlin code**:
     *   ```kotlin
     *   val pin = BuildConfig.PIN_CERTIFCATE
     *   ```
     */
    const val PIN_CERTIFCATE = "PIN_CERTIFCATE"

    /**
     * Constant key used for storing or retrieving a symmetric encryption key
     * from secure storage (e.g., Android Keystore, SharedPreferences, or DataStore).
     *
     * - Typically used as a reference name when saving a generated key
     * - Should match the identifier expected by your encryption/decryption manager
     * - Recommended to rotate or version keys by year (e.g., 2025)
     */
    const val SECRET_KEY_2025 = "SECRET_KEY_2025"
}
