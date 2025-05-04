// Defines the package `extensions`, grouping utility functions that extend Gradle functionality.
package extensions

// Imports Gradle's `Project` class, which represents the current Gradle project.
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.LibraryBuildType
import org.gradle.api.Project

// Imports Java's Properties class to read local configuration files.
import java.util.Properties

/**
 * The name of the local properties file that stores sensitive credentials or environment-specific values.
 * - This file is **not** committed to version control (should be listed in `.gitignore`).
 * - It contains values such as API keys, database URLs, or debug configurations.
 * - Example file content (`dev_credentials.properties`):
 *   ```
 *   dev.debug_endpoint=https://api.debug.example.com/
 *   dev.prod_endpoint=https://api.example.com/
 *   dev.map_key=AIzaSyXXXXXX
 *   ```
 */
private const val LOCAL_PROPERTIES_FILE_NAME = "dev_credentials.properties"

/**
 * Retrieves a property value from `dev_credentials.properties`.
 *
 * @param propertyName The name of the property to retrieve.
 * @return The value of the requested property.
 * @throws NoSuchFieldException If the property does not exist.
 *
 * **Example Usage in `build.gradle.kts`**:
 * ```kotlin
 * val baseUrl = project.getLocalProperty("dev.debug_endpoint")
 * buildConfigStringField("BASE_URL", baseUrl)
 * ```
 */
fun Project.getLocalProperty(propertyName: String): String {
    // Creates a Properties object to hold key-value pairs from the file.
    val localProperties = Properties().apply {

        // Locates the properties file in the root directory of the project.
        val localPropertiesFile = project.rootProject.file(LOCAL_PROPERTIES_FILE_NAME)

        // Checks if the file exists before attempting to read it.
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())  // Loads properties from the file into memory.
        }
    }

    // Returns the property value or throws an exception if not found.
    return localProperties.getProperty(propertyName)
        ?: throw NoSuchFieldException("Property not found: $propertyName")
}


/**
 * Adds a **String** field to `BuildConfig.java` via `build.gradle.kts`.
 *
 * @param name The name of the BuildConfig field.
 * @param value The value of the field.
 *
 * **Example Usage in `build.gradle.kts`**:
 * ```kotlin
 * buildConfigStringField("BASE_URL", project.getLocalProperty("dev.debug_endpoint"))
 * ```
 *
 * **Result in `BuildConfig.java`**:
 * ```java
 * public final class BuildConfig {
 *     public static final String BASE_URL = "https://api.debug.example.com/";
 * }
 * ```
 */
fun ApplicationBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, value)
}


/**
 * Adds an **int** field to `BuildConfig.java` via `build.gradle.kts`.
 *
 * @param name The name of the BuildConfig field.
 * @param value The value of the field.
 *
 * **Example Usage in `build.gradle.kts`**:
 * ```kotlin
 * buildConfigIntField("DB_VERSION", "3")
 * ```
 *
 * **Result in `BuildConfig.java`**:
 * ```java
 * public final class BuildConfig {
 *     public static final int DB_VERSION = 3;
 * }
 * ```
 */
fun ApplicationBuildType.buildConfigIntField(name: String, value: String) {
    this.buildConfigField("int", name, value)
}


/**
 * Adds a **boolean** field to `BuildConfig.java` via `build.gradle.kts`.
 *
 * @param name The name of the BuildConfig field.
 * @param value The value of the field (`true` or `false`).
 *
 * **Example Usage in `build.gradle.kts`**:
 * ```kotlin
 * buildConfigBooleanField("CAN_CLEAR_CACHE", "true")
 * ```
 *
 * **Result in `BuildConfig.java`**:
 * ```java
 * public final class BuildConfig {
 *     public static final boolean CAN_CLEAR_CACHE = true;
 * }
 * ```
 */
fun ApplicationBuildType.buildConfigBooleanField(name: String, value: String) {
    this.buildConfigField("boolean", name, value)
}

/**
 * Adds a `buildConfigField` of type `String` to the specified [LibraryBuildType].
 *
 * This is used to inject constant string values into the generated `BuildConfig` class.
 *
 * ### Example:
 * ```kotlin
 * buildConfigStringField("API_BASE_URL", "\"https://example.com/api/\"")
 * ```
 *
 * @param name The constant name in `BuildConfig`.
 * @param value The value to be assigned. Must be quoted like `"\"value\""` in the DSL.
 */
fun LibraryBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, value)
}

/**
 * Adds a `buildConfigField` of type `int` to the specified [LibraryBuildType].
 *
 * Useful for injecting numeric constants into `BuildConfig`, such as version codes or limits.
 *
 * ### Example:
 * ```kotlin
 * buildConfigIntField("MAX_RETRIES", "5")
 * ```
 *
 * @param name The constant name in `BuildConfig`.
 * @param value The value to be assigned, passed as a String.
 */
fun LibraryBuildType.buildConfigIntField(name: String, value: String) {
    this.buildConfigField("int", name, value)
}

/**
 * Adds a `buildConfigField` of type `boolean` to the specified [LibraryBuildType].
 *
 * Typically used for toggles such as enabling logs or features in different build types.
 *
 * ### Example:
 * ```kotlin
 * buildConfigBooleanField("LOGGING_ENABLED", "true")
 * ```
 *
 * @param name The constant name in `BuildConfig`.
 * @param value The boolean value as a String (`"true"` or `"false"`).
 */
fun LibraryBuildType.buildConfigBooleanField(name: String, value: String) {
    this.buildConfigField("boolean", name, value)
}
