// Defines the package `release`, which logically groups release-related configurations.
package release

/**
 * `ReleaseConfig` is an object that stores **versioning information** for the app.
 * - These values are used in `build.gradle.kts` to set the **version code** and **version name**.
 * - Centralizing versioning ensures **consistency** across builds and makes updates easier.
 */
object ReleaseConfig {

    /**
     * `VERSION_CODE` - Defines the **internal version number** of the app.
     * - This is an **incrementing integer** required for publishing updates to Google Play.
     * - **Must be increased for each new release**.
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   defaultConfig {
     *       versionCode = ReleaseConfig.VERSION_CODE
     *   }
     *   ```
     *
     * **Example Behavior**:
     * - `1` → First version of the app.
     * - `2` → Increment for a new release.
     */
    const val VERSION_CODE = 1

    /**
     * `VERSION_NAME` - Defines the **human-readable version name** of the app.
     * - This is the **public-facing version** seen by users in the Play Store.
     * - Usually follows a **semantic versioning format** (`major.minor.patch`).
     * - Example usage in `build.gradle.kts`:
     *   ```kotlin
     *   defaultConfig {
     *       versionName = ReleaseConfig.VERSION_NAME
     *   }
     *   ```
     *
     * **Example Behavior**:
     * - `"1.0"` → First version.
     * - `"1.1"` → Minor update with bug fixes.
     * - `"2.0"` → Major update with new features.
     */
    const val VERSION_NAME = "1.0"
}
