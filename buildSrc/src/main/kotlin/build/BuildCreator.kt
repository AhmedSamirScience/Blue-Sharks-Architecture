// Defines the package `build`, grouping all build-related configurations.
package build

// Imports custom extension functions for setting BuildConfig fields.
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField

// Imports Android build system classes for configuring Application and Library build types.
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.LibraryBuildType

// Imports an extension function to retrieve local properties from `gradle.properties`.
import extensions.getLocalProperty

// Imports Gradle APIs for handling named collections of build types.
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

/**
 * `BuildCreator` is a sealed class responsible for defining and configuring different build types.
 * - It provides an abstract method for configuring **ApplicationBuildType** and **LibraryBuildType**.
 * - Subclasses define different build configurations (Debug, Release, ReleaseExternalQa).
 */
sealed class BuildCreator(val name: String) {

    /**
     * Abstract method for creating an `ApplicationBuildType`.
     * - Takes a `NamedDomainObjectContainer<ApplicationBuildType>` as an argument.
     * - Must be implemented by subclasses.
     */
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType

    /**
     * Abstract method for creating a `LibraryBuildType`.
     * - Takes a `NamedDomainObjectContainer<LibraryBuildType>` as an argument.
     * - Must be implemented by subclasses.
     */
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType


    /**
     * `Debug` build type:
     * - Used during development with debugging features enabled.
     * - Enables unit test coverage and debugging tools.
     */
    class Debug(private val project: Project) : BuildCreator(BuildTypes.DEBUG) {

        /**
         * Creates the `Debug` variant for an application.
         */
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name) {

                // Apply debug-specific build configurations
                isMinifyEnabled = Build.Debug.isMinifyEnabled  // Keep all code for debugging.
                isDebuggable = Build.Debug.isDebuggable        // Enable debugging features.
                versionNameSuffix = Build.Debug.versionNameSuffix // Add "-DEBUG" to version name.
                applicationIdSuffix = Build.Debug.applicationIdSuffix // Append ".debug" to application ID.
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage // Enable unit test coverage.

                // Set build configuration fields from local properties
                buildConfigStringField(BuildVariables.BASE_URL, project.getLocalProperty("dev.debug_endpoint"))
                buildConfigStringField(BuildVariables.PIN_CERTIFCATE, project.getLocalProperty("dev.project.certificate_pin"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getLocalProperty("dev.map_key"))
                buildConfigStringField(BuildVariables.SECRET_KEY_2025, project.getLocalProperty("release_key.key_store"))
            }
        }

        /**
         * Creates the `Debug` variant for a library.
         */
        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage

                // Set build configuration fields from local properties
                buildConfigStringField(BuildVariables.BASE_URL, project.getLocalProperty("dev.debug_endpoint"))
                buildConfigStringField(BuildVariables.PIN_CERTIFCATE, project.getLocalProperty("dev.project.certificate_pin"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getLocalProperty("dev.map_key"))
                buildConfigStringField(BuildVariables.SECRET_KEY_2025, project.getLocalProperty("release_key.key_store"))

            }
        }
    }


    /**
     * `Release` build type:
     * - Used for the final production version of the app.
     * - Enables minification and disables debugging.
     */
    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {

        /**
         * Creates the `Release` variant for an application.
         */
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name) {

                // Apply release-specific configurations
                isMinifyEnabled = Build.Release.isMinifyEnabled // Enable minification.
                enableUnitTestCoverage = Build.Release.enableUnitTestCoverage // Disable test coverage.
                isDebuggable = Build.Release.isDebuggable // Disable debugging.

                // Set build configuration fields from local properties
                buildConfigStringField(BuildVariables.BASE_URL, project.getLocalProperty("dev.prod_endpoint"))
                buildConfigStringField(BuildVariables.PIN_CERTIFCATE, project.getLocalProperty("release.project.certificate_pin"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getLocalProperty("release.map_key"))
                buildConfigStringField(BuildVariables.SECRET_KEY_2025, project.getLocalProperty("release_key.key_store"))

            }
        }

        /**
         * Creates the `Release` variant for a library.
         */
        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Release.isMinifyEnabled  // Enable minification.
                enableUnitTestCoverage = Build.Release.enableUnitTestCoverage // Disable test coverage.

                // Set build configuration fields from local properties
                buildConfigStringField(BuildVariables.BASE_URL, project.getLocalProperty("dev.prod_endpoint"))
                buildConfigStringField(BuildVariables.PIN_CERTIFCATE, project.getLocalProperty("release.project.certificate_pin"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getLocalProperty("release.map_key"))
                buildConfigStringField(BuildVariables.SECRET_KEY_2025, project.getLocalProperty("release_key.key_store"))
            }
        }
    }

    /**
     * `ReleaseExternalQa` build type:
     * - Used for external quality assurance (QA) testing.
     * - Enables debugging and test coverage.
     * - Uses a different API endpoint for QA.
     */
    class ReleaseExternalQa(private val project: Project) :
        BuildCreator(BuildTypes.RELEASE_EXTERNAL_QA) {

        /**
         * Creates the `ReleaseExternalQa` variant for an application.
         */
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.create(name) {
                // Apply QA-specific configurations
                isMinifyEnabled = Build.ReleaseExternalQa.isMinifyEnabled // Minification is disabled.
                enableUnitTestCoverage = Build.ReleaseExternalQa.enableUnitTestCoverage // Enable test coverage.
                isDebuggable = Build.ReleaseExternalQa.isDebuggable // Enable debugging.
                versionNameSuffix = Build.ReleaseExternalQa.versionNameSuffix // Add "-QA" to version name.
                applicationIdSuffix = Build.ReleaseExternalQa.applicationIdSuffix // Append `.releaseExternalQa` to application ID.

                // Set build configuration fields from local properties
                buildConfigStringField(BuildVariables.BASE_URL, project.getLocalProperty("dev.qa_endpoint"))
                buildConfigStringField(BuildVariables.PIN_CERTIFCATE, project.getLocalProperty("dev.project.certificate_pin"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getLocalProperty("dev.map_key"))
                buildConfigStringField(BuildVariables.SECRET_KEY_2025, project.getLocalProperty("release_key.key_store"))

            }
        }

        /**
         * Creates the `ReleaseExternalQa` variant for a library.
         */
        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.create(name) {
                isMinifyEnabled = Build.ReleaseExternalQa.isMinifyEnabled
                enableUnitTestCoverage = Build.ReleaseExternalQa.enableUnitTestCoverage

                // Set build configuration fields from local properties
                buildConfigStringField(BuildVariables.BASE_URL, project.getLocalProperty("dev.qa_endpoint"))
                buildConfigStringField(BuildVariables.PIN_CERTIFCATE, project.getLocalProperty("dev.project.certificate_pin"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getLocalProperty("dev.map_key"))
                buildConfigStringField(BuildVariables.SECRET_KEY_2025, project.getLocalProperty("release_key.key_store"))

            }
        }
    }
}