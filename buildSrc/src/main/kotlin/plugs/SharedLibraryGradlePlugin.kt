// Defines the package `plugs`, which logically groups all Gradle-related plugins.
package plugs

// ───────────────────────────────────────────────────────────────────────────────
// Import Statements
// ───────────────────────────────────────────────────────────────────────────────
import build.BuildConfig // Importing project-wide configuration values (e.g., compileSdk, minSdk).
import build.BuildCreator // Manages the creation of build types.
import org.gradle.api.publish.maven.MavenPublication
import build.BuildDimensions // Defines flavor dimensions.
import com.android.build.api.dsl.LibraryExtension // Used to configure Android library modules.
import org.gradle.api.JavaVersion // Specifies Java version compatibility.
import org.gradle.api.Plugin // Defines a Gradle plugin.
import org.gradle.api.Project // Represents the Gradle project.
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.withType // Extension function for applying configurations to Gradle tasks.
import sigining.BuildSigning // Handles signing configurations.
import sigining.SigningTypes // Constants for different signing types.
import tests.TestBuildConfig // Holds test instrumentation runner configurations.

/**
 * `SharedLibraryGradlePlugin` is a **custom Gradle plugin** that configures shared Android library modules.
 * - This plugin **automates** applying standard configurations across multiple library modules.
 * - Ensures **consistency** in build setup, dependency management, and signing configurations.
 */
class SharedLibraryGradlePlugin : Plugin<Project> {

    /**
     * `apply` is called when this plugin is applied to a Gradle project.
     * - Calls helper functions to configure the project.
     */
    override fun apply(project: Project) {
        project.addPluginConfigurations()
        project.addAndroidConfigurations()
        project.applyKotlinOptions()
        project.configurePublishing() // ✅ NEW

    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Plugin Configurations
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `addPluginConfigurations` applies essential **Gradle plugins**.
     * - **Kotlin Plugin** → Enables Kotlin support in the library module.
     * - **KAPT Plugin** → Enables annotation processing (for Dagger, Room, etc.).
     * - (Commented) Static Analysis Plugins:
     *   - **KTLint** → Enforces Kotlin style guide.
     *   - **Spotless** → Automatic code formatting.
     *   - **Detekt** → Static analysis for detecting code smells.
     *   - **Update Dependencies Plugin** → Ensures all dependencies are up to date.

     */
    private fun Project.addPluginConfigurations() {
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KAPT)
        plugins.apply(BuildPlugins.KTLINT)
        plugins.apply(BuildPlugins.SPOTLESS)
        plugins.apply(BuildPlugins.DETEKT)
        plugins.apply(BuildPlugins.UPDATE_DEPS_VERSIONS)
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Android Configurations
    // ───────────────────────────────────────────────────────────────────────────────
    /**
     * `addAndroidConfigurations` applies **Android-specific configurations**.
     * - Configures SDK versions.
     * - Sets default configurations (minSdk, test runner).
     * - Defines signing configurations.
     * - Creates build types (Debug, Release, QA).
     * - Adds product flavors (Google, Huawei, Client, Driver).
     */
    private fun Project.addAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {

            /**
             * **Set SDK Versions**
             * - Uses `BuildConfig` values to ensure consistency across all modules.
             */
            compileSdk = BuildConfig.COMPILE_SDK_VERSION
            defaultConfig {
                minSdk = BuildConfig.MIN_SDK_VERSION
                testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
            }

            // ───────────────────────────────────────────────────────────────────────────────
            // Signing Configurations
            // ───────────────────────────────────────────────────────────────────────────────
            signingConfigs {
                BuildSigning.Release(project).create(this)
                BuildSigning.ReleaseExternalQa(project).create(this)
                BuildSigning.Debug(project).create(this)
            }

            // ───────────────────────────────────────────────────────────────────────────────
            // Build Types
            // ───────────────────────────────────────────────────────────────────────────────
            buildTypes {
                /**
                 * **Release Build Type**
                 * - Enables ProGuard for **code shrinking and obfuscation**.
                 * - Uses **secure signing configuration**.
                 */
                BuildCreator.Release(project).createLibrary(this).apply {
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro",
                        "proguard-string-concat-fix.pro" )
                    signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
                }

                /**
                 * **Debug Build Type**
                 * - Used for **development and testing**.
                 * - Uses the **default debug keystore**.
                 */
                BuildCreator.Debug(project).createLibrary(this).apply {
                    signingConfig = signingConfigs.getByName(SigningTypes.DEBUG)
                }

                /**
                 * **QA Build Type**
                 * - Used for **internal testing**.
                 * - Uses a separate signing key for **QA builds**.
                 */
                BuildCreator.ReleaseExternalQa(project).createLibrary(this).apply {
                    signingConfig = signingConfigs.getByName(SigningTypes.RELEASE_EXTERNAL_QA)
                }
            }

            // ───────────────────────────────────────────────────────────────────────────────
            // Product Flavors
            // ───────────────────────────────────────────────────────────────────────────────
            /**
             * Defines **flavor dimensions**.
             * - `APP` → Groups app-based flavors (e.g., Client, Driver).
             * - `STORE` → Groups store-based flavors (e.g., Google Play, Huawei Store).
             */
            flavorDimensions.add(BuildDimensions.APP)
            flavorDimensions.add(BuildDimensions.STORE)

            /**
             * Defines different **product flavors** for app distribution.
             * - `Google` → Google Play Store version.
             * - `Huawei` → Huawei AppGallery version.
             * - `Client` → Client-side version of the app.
             * - `Driver` → Driver-specific version of the app.
             */
            productFlavors {
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Client.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
            }

            // ───────────────────────────────────────────────────────────────────────────────
            // Build Features
            // ───────────────────────────────────────────────────────────────────────────────
            buildFeatures {
                buildConfig = true
            }

            // ───────────────────────────────────────────────────────────────────────────────
            // Compilation Options
            // ───────────────────────────────────────────────────────────────────────────────
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            // ✅ This is what registers the 'release' SoftwareComponent for Maven publishing
            publishing {
                singleVariant("clientGoogleRelease") {
                    withSourcesJar()
                    //withJavadocJar()
                }
                singleVariant("clientGoogleDebug") {
                    withSourcesJar()
                }
            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Kotlin Options Configuration
    // ───────────────────────────────────────────────────────────────────────────────
    /**
     * `applyKotlinOptions` configures Kotlin compilation settings.
     * - Sets **JVM target version** to 17 for better performance and compatibility.
     */
    private fun Project.applyKotlinOptions() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
                freeCompilerArgs += listOf("-Xstring-concat=inline") // ✅ Important
            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // ✅ AAR Publishing Configuration (Maven-compatible)
    // ───────────────────────────────────────────────────────────────────────────────
    /*fun Project.configurePublishing() {
        plugins.withId("maven-publish") {
            afterEvaluate {
                extensions.findByType(org.gradle.api.publish.PublishingExtension::class.java)
                    ?.apply {
                        publications {
                            create("release", MavenPublication::class.java) {
                                groupId = "com.samir.core"
                                artifactId = project.name
                                version = "1.0.0"

                                val releaseComponent = components.findByName("clientGoogleRelease")
                                if (releaseComponent != null) {
                                    from(releaseComponent)
                                } else {
                                    logger.warn("⚠️ Cannot publish '${project.name}': no 'release' component registered.")
                                }

                                pom {
                                    name.set("${project.name.capitalize()} Module")
                                    description.set("Auto-published Android library module '${project.name}'")
                                }
                            }
                        }

                        repositories {
                            mavenLocal()
                            // Optionally: publish to local dir for team/internal use
                            // maven {
                            //     url = uri("${rootProject.buildDir}/local-maven")
                            // }
                        }
                    }
            }
        }
    }*/

    fun Project.configurePublishing() {
        plugins.withId("maven-publish") {
            afterEvaluate {
                extensions.findByType(PublishingExtension::class.java)?.apply {
                    publications {

                        listOf(
                            "clientGoogleDebug",
                            "clientGoogleRelease"
                            // Add more here as needed
                        ).forEach { variant ->
                            create(variant, MavenPublication::class.java) {
                                groupId = "com.samir.core"
                                artifactId = "$name-$variant" // Ensures uniqueness
                                version = "1.0.0"

                                val component = components.findByName(variant)
                                if (component != null) {
                                    from(component)
                                } else {
                                    logger.warn("⚠️ '$variant' component not found in ${project.name}")
                                }

                                pom {
                                    name.set("$name - $variant")
                                    description.set("Published variant $variant of module $name")
                                }
                            }
                        }
                    }

                    repositories {
                        mavenLocal()
                        // Optionally: external maven repo
                        // maven {
                        //     url = uri("${rootProject.buildDir}/local-maven")
                        // }
                    }
                }
            }
        }
    }

    /**
     * ./gradlew publishClientGoogleDebugPublicationToMavenLocal
     * ./gradlew publishClientGoogleReleasePublicationToMavenLocal
     */
}