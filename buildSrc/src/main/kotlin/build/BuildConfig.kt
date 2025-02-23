// Defines the package `build`, grouping all build-related constants.
package build

/**
 * `BuildConfig` is an object that stores **global build configuration constants**.
 * - These values are used in `build.gradle.kts` to configure the Android build system.
 * - Helps maintain consistent values across multiple build types and flavors.
 */
object BuildConfig {

    /**
     * `APP_ID` - Defines the **application ID** (package name).
     * - This uniquely identifies your app on a device and in the Google Play Store.
     * - Must be unique across all apps.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   applicationId = BuildConfig.APP_ID
     *   ```
     */
    const val APP_ID = "com.samir.bluearchitecture"

    /**
     * `MIN_SDK_VERSION` - Defines the **minimum API level** required to install and run the app.
     * - Determines the **oldest** Android version the app supports.
     * - If a device has a lower API level, it **cannot** install the app.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   minSdk = BuildConfig.MIN_SDK_VERSION
     *   ```
     * - **Example behavior**:
     *   - `24` (Android 7.0 - Nougat) → App supports devices running **API 24+**.
     */
    const val MIN_SDK_VERSION = 24

    /**
     * `TARGET_SDK_VERSION` - Defines the **API level the app is designed and tested against**.
     * - Ensures the app runs with **latest Android features and restrictions**.
     * - If the app runs on a device with a newer API level, Android enables **backward compatibility**.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   targetSdk = BuildConfig.TARGET_SDK_VERSION
     *   ```
     * - **Example behavior**:
     *   - `34` (Android 14) → App is optimized for **Android 14**, but can run on lower versions.
     */
    const val TARGET_SDK_VERSION = 34

    /**
     * `COMPILE_SDK_VERSION` - Defines the **SDK version used to compile the app**.
     * - Should be set to the **latest available Android SDK**.
     * - If you use **new API features**, `compileSdk` must be set to that version.
     * - **Example usage in Gradle**:
     *   ```kotlin
     *   compileSdk = BuildConfig.COMPILE_SDK_VERSION
     *   ```
     * - **Example behavior**:
     *   - `34` (Android 14) → App is compiled with **Android 14 SDK**.
     */
    const val COMPILE_SDK_VERSION = 34
}
