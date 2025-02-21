// Defines the package `flavors`, grouping all flavor-related constants.
package falvors

/**
 * `FlavorTypes` is an object that stores **product flavor names**.
 * - These names correspond to product flavors declared in Gradle (`build.gradle.kts`).
 * - Helps prevent **typos** and **ensures consistency** across build configurations.
 */
object FlavorTypes {

    /**
     * `GOOGLE` - Defines the flavor for the **Google Play Store** version of the app.
     * - Used in `BuildFlavor.Google`.
     * - Example usage in Gradle:
     *   ```kotlin
     *   productFlavors.create(FlavorTypes.GOOGLE) { dimension = "store" }
     *   ```
     */
    const val GOOGLE = "google"

    /**
     * `HUAWEI` - Defines the flavor for the **Huawei AppGallery** version of the app.
     * - Used in `BuildFlavor.Huawei`.
     * - Example usage in Gradle:
     *   ```kotlin
     *   productFlavors.create(FlavorTypes.HUAWEI) { dimension = "store" }
     *   ```
     */
    const val HUAWEI = "huawei"

    /**
     * `DRIVER` - Defines the flavor for the **Driver** version of the app.
     * - Used in `BuildFlavor.Driver`.
     * - Example usage in Gradle:
     *   ```kotlin
     *   productFlavors.create(FlavorTypes.DRIVER) { dimension = "app" }
     *   ```
     */
    const val DRIVER = "driver"

    /**
     * `CLIENT` - Defines the flavor for the **Client** version of the app.
     * - Used in `BuildFlavor.Client`.
     * - Example usage in Gradle:
     *   ```kotlin
     *   productFlavors.create(FlavorTypes.CLIENT) { dimension = "app" }
     *   ```
     */
    const val CLIENT = "client"
}
