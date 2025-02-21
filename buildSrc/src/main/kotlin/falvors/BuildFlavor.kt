// Imports necessary classes for defining product flavors.
import build.BuildDimensions
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor

// Imports flavor types from another package.
import falvors.FlavorTypes

// Imports Gradle API for handling named collections of product flavors.
import org.gradle.api.NamedDomainObjectContainer

/**
 * `BuildFlavor` is a sealed class that defines **different product flavors**.
 * - Product flavors allow you to create multiple versions of the app.
 * - Each flavor can have **different app IDs, version names, and store configurations**.
 * - This class supports both **app flavors** and **library module flavors**.
 *
 * **Why Use a `sealed class`?**
 * - Ensures that only predefined flavors (`Google`, `Huawei`, `Driver`, `Client`) exist.
 * - Prevents unwanted or dynamic modifications to the flavor list.
 *
 * @param name The unique name of the flavor (e.g., `"google"`, `"huawei"`, `"driver"`, `"client"`).
 */
sealed class BuildFlavor(val name: String) {

    /**
     * Creates a product flavor for the **app module**.
     * @param namedDomainObjectContainer The container holding all app product flavors.
     * @return The configured `ApplicationProductFlavor` instance.
     */
    // its used for app module
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ) : ApplicationProductFlavor

    /**
     * Creates a product flavor for **library modules** (e.g., authentication, visit creation, discounts).
     * @param namedDomainObjectContainer The container holding all library product flavors.
     * @return The configured `LibraryProductFlavor` instance.
     */
    // its used for modules like authintecation, create visit, discount flow and so on
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ) : LibraryProductFlavor


    // ───────────────────────────────────────────────────────────────────────────────
    // Google Play Store Flavor
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `Google` product flavor:
     * - Used for distributing the app via **Google Play Store**.
     * - Assigned to the `STORE` flavor dimension.
     * - Appends `.google` to the `applicationId` and `-google` to the version name.
     */
    object Google : BuildFlavor(FlavorTypes.GOOGLE){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.STORE // Assigns to the "store" dimension.
                applicationIdSuffix = ".$name" // App ID becomes `com.example.app.google`
                versionNameSuffix = "-$name" // Version name becomes `1.0-google`
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.STORE // Assigns to the "store" dimension.
            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Huawei AppGallery Flavor
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `Huawei` product flavor:
     * - Used for distributing the app via **Huawei AppGallery**.
     * - Assigned to the `STORE` flavor dimension.
     * - Appends `.huawei` to the `applicationId` and `-huawei` to the version name.
     */
    object Huawei : BuildFlavor(FlavorTypes.HUAWEI){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.STORE
                applicationIdSuffix = ".$name" // App ID becomes `com.example.app.huawei`
                versionNameSuffix = "-$name" // Version name becomes `1.0-huawei`
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.STORE
            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Driver App Flavor
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `Driver` product flavor:
     * - Used for a driver-specific version of the app.
     * - Assigned to the `APP` flavor dimension.
     * - Appends `.driver` to the `applicationId` and `-driver` to the version name.
     */
    object Driver : BuildFlavor(FlavorTypes.DRIVER){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.APP // Assigned to the "app" dimension.
                applicationIdSuffix = ".$name" // App ID becomes `com.example.app.driver`
                versionNameSuffix = "-$name" // Version name becomes `1.0-driver`
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.APP
            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Client App Flavor
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `Client` product flavor:
     * - Used for a client-specific version of the app.
     * - Assigned to the `APP` flavor dimension.
     * - Appends `.client` to the `applicationId` and `-client` to the version name.
     */
    object Client : BuildFlavor(FlavorTypes.CLIENT){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.APP
                applicationIdSuffix = ".$name" // App ID becomes `com.example.app.client`
                versionNameSuffix = "-$name" // Version name becomes `1.0-client`
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimensions.APP
            }
        }
    }


}