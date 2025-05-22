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

}
