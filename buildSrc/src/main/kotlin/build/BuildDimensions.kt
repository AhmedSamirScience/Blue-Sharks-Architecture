// Defines the package `build`, which groups related build configuration files.
package build

/**
 * `BuildDimensions` is an object that stores **flavor dimension names**.
 * - Flavor dimensions are used to **categorize product flavors** in Gradle.
 * - Every product flavor must belong to a dimension.
 * - These dimensions help structure multiple flavor configurations.
 */
object BuildDimensions {
    /**
     * `STORE` represents a **dimension** for store-based flavors.
     * - This can be used when defining flavors based on **distribution platforms**.
     * - Example: Flavors like `googlePlay`, `huaweiStore`, or `amazonStore` can belong to this dimension.
     */
    const val STORE = "store"
}
