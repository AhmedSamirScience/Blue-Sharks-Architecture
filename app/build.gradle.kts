import build.BuildCreator
import build.BuildDimensions
import dependencies.coroutines
import dependencies.defaultLibraries
import dependencies.dependencyInjectionHilt
import dependencies.jetpackViewModelAndLifecycle
import dependencies.loginModule
import dependencies.navigationComponent
import dependencies.networking
import dependencies.presentationModule
import dependencies.remoteDataModule
import dependencies.viewModelCasesModule

/**
 * ***build.gradle/build.gradle.kts (app module)***
 *
 * - All these blocks (e.g. plugins {}, android {}, dependencies {}, etc) are domain-specify language (DSL)
 * - implemented using trailing lambda -> (https://vtsen.hashnode.dev/what-is-trailing-lambda-and-comma-in-kotlin) and
 * - function literal with receiver -> (https://vtsen.hashnode.dev/understand-kotlin-function-literal-with-receiver-by-example)
 */
plugins {
  /**
   * plugins {} Block
   * - Similar to the build.gradle/.kts in the root project, this applies plugins in this module.
   * - It doesn't need to explicitly call version() because it has already been specified in the root project.
   * - apply(true) is also not needed because by default it is true.
   */
  id(plugs.BuildPlugins.ANDROID_APPLICATION) // plugin to build Android app (e.g. default app module) (AGP)
  id(plugs.BuildPlugins.KOTLIN_ANDROID) // plugin to enable Kotlin support in your project.

  /**
   * Applies the **Ktlint Gradle Plugin** to enforce Kotlin coding standards.
   *
   * - **Purpose:** Ensures all Kotlin code follows a consistent style guide.
   * - **Why Use Ktlint?**
   *   - Prevents inconsistent formatting in the codebase.
   *   - Improves code readability and maintainability.
   *   - Automatically formats code when running `ktlintFormat`.
   * - **How It Works:**
   *   - **`ktlintCheck`** → Runs ktlint to check for formatting violations.
   *   - **`ktlintFormat`** → Fixes auto-correctable formatting issues.
   * - **Example Usage:**
   *   - Run `./gradlew ktlintCheck` to find violations.
   *   - Run `./gradlew ktlintFormat` to auto-fix them.
   */
  id(plugs.BuildPlugins.KTLINT)

  /**
   * Spotless Plugin
   * - Spotless is a code formatter that ensures consistent coding styles across the project.
   * - It supports multiple file types, including Kotlin, Java, XML, and more.
   * - Helps enforce clean code standards by automatically formatting source files.
   *
   * 🔹 Why Use Spotless?
   * - 🚀 Automates code formatting to save time.
   * - 🔄 Ensures uniform coding styles in large projects.
   * - ✅ Prevents unnecessary formatting-related changes in version control.
   *
   * Official Documentation: https://github.com/diffplug/spotless
   */
  id(plugs.BuildPlugins.SPOTLESS)

  /**
   * 🛡️ Detekt - Kotlin Static Code Analysis Plugin
   *
   * - Detekt is a **static analysis tool** that helps enforce coding standards,
   *   detect code smells, and improve code quality in Kotlin projects.
   * - This plugin integrates Detekt into the Gradle build process.
   * - It provides automatic code inspections and generates reports.
   *
   * 🔹 Benefits of Using Detekt:
   * - 🚀 Detects common issues such as **long methods, unused parameters, and magic numbers**.
   * - 📊 Provides **reports in multiple formats** (HTML, XML, SARIF).
   * - 🔧 Fully **configurable rules** to match your project's coding guidelines.
   * - 🔄 Supports **Gradle task automation** for continuous integration.
   *
   * 🔗 Official Documentation: [https://detekt.dev](https://detekt.dev)
   */
  id(plugs.BuildPlugins.DETEKT)

  /**
   * 📌 Gradle Versions Plugin - Automatic Dependency Updates
   * - This plugin helps track and manage outdated dependencies in your project.
   * - It scans all dependencies and plugins to find newer versions.
   * - Ensures that your project stays updated with the latest stable releases.
   *
   * 🔹 Benefits:
   * - 🚀 **Automates dependency updates** to prevent outdated libraries.
   * - 🔄 **Reduces security risks** by identifying deprecated dependencies.
   * - 📊 **Provides detailed reports** on outdated and available versions.
   * - ✅ **Filters out unstable versions** to ensure only stable updates.
   *
   * 🔗 Official Documentation: https://github.com/ben-manes/gradle-versions-plugin
   */
  id(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)

  /**
   * 📌 Applying the Dokka Plugin - Kotlin Documentation Generator
   * - This plugin integrates **Dokka** into the Gradle build system.
   * - It enables automatic generation of API documentation from KDoc comments.
   *
   * 🔹 Why Apply the Dokka Plugin?
   * - 📝 Automatically generates project documentation from KDoc.
   * - 📄 Supports multiple output formats: HTML, Markdown, Javadoc, etc.
   * - 🔄 Useful for sharing documentation with teams or publishing libraries.
   * - ✅ Ensures up-to-date documentation with every build.
   *
   * 🛠️ Components:
   * - **dokka-gradle-plugin** → Core plugin for generating documentation.
   * - **kotlin-as-java-plugin** → Converts Kotlin documentation into a JavaDoc-like format.
   *
   * 🔗 Official Documentation: https://kotlinlang.org/docs/dokka-introduction.html
   */
  id(plugs.BuildPlugins.DOKKA)

  /**
   * 📌 Applying the Safe Args Kotlin Plugin - Type-Safe Navigation in Jetpack
   * - This plugin integrates **Safe Args** into the Gradle build system.
   * - It enables the generation of **type-safe classes** for passing data between navigation destinations.
   *
   * 🔹 Why Apply the Safe Args Plugin?
   * - 🚫 Avoids runtime errors from incorrect `Bundle` keys or types.
   * - ✅ Generates `Directions` and `Args` classes for safer navigation.
   * - 🤝 Strongly typed arguments improve maintainability and readability.
   * - 💡 Works seamlessly with the Jetpack Navigation Component in Kotlin projects.
   *
   * ⚠️ Requirements:
   * - The **Kotlin Android plugin** (`org.jetbrains.kotlin.android`) must be applied *before* this plugin.
   * - Only compatible with **Kotlin modules**.
   *
   * 🔗 Official Documentation: https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args
   */
  id(plugs.BuildPlugins.SAFE_ARGS)

  /**
   * ⚙️ Applying the Kotlin Annotation Processing Tool (KAPT)
   * - This plugin enables support for **annotation processing** in Kotlin-based Android projects.
   * - It is required for many popular libraries that generate code at compile time using annotations.
   *
   * 🔹 Why Apply the KAPT Plugin?
   * - ✅ Enables libraries like **Dagger/Hilt**, **Room**, **Data Binding**, and **Moshi/KotlinX Serialization** to work.
   * - 🏗️ Generates necessary source files during compilation (e.g., Dagger components, Room DAO implementations).
   * - 🧠 Handles Java annotation processors in Kotlin projects by converting Kotlin code to Java stubs internally.
   *
   * ⚠️ Requirements:
   * - Use `kapt {}` block in your `build.gradle.kts` for custom configuration (if needed).
   * - Make sure annotation processors are declared using `kapt` instead of `implementation`.
   *
   * 💡 Notes:
   * - Slower than Java’s annotation processing due to the extra stub generation step.
   * - For better performance and full Kotlin support, consider using **KSP** (Kotlin Symbol Processing) when available.
   *
   * 🔗 Official Documentation: https://kotlinlang.org/docs/kapt.html
   */
  id(plugs.BuildPlugins.KAPT)

  /**
   * 🧪 Applying the Dagger Hilt Plugin – Dependency Injection for Android
   * - Integrates the **Dagger Hilt Gradle Plugin** to support Hilt-based dependency injection in Android projects.
   * - Automatically generates Hilt components and simplifies dependency graph setup across modules.
   *
   * 🔹 Why Apply the Dagger Hilt Plugin?
   * - ✅ Simplifies setup of Dagger in Android apps with a standard lifecycle-aware structure.
   * - 🧱 Automatically creates base classes like `HiltApplication`, `HiltActivity`, and `HiltViewModel`.
   * - 🧠 Eliminates boilerplate Dagger components, improving maintainability.
   * - 🛡️ Ensures consistency in dependency injection across feature and core modules.
   *
   * ⚙️ Behavior:
   * - Triggers code generation during build (e.g., `*_HiltComponents`, `*_Factory`, etc.).
   * - Requires `kapt` to process Hilt's annotations properly.
   *
   * ⚠️ Requirements:
   * - Apply the **Kotlin KAPT plugin** first.
   * - Your application class must extend `HiltAndroidApp`.
   *
   * 🔗 Official Docs: https://developer.android.com/training/dependency-injection/hilt-android
   */
  id(plugs.BuildPlugins.DAGGER_HILT) version plugs.BuildPlugins.DAGGER_HILT_VERSION_NUMBER
}

android {
  /**
   * android {} Block -> This defines the properties specific to the Android platform.
   */

  /**
   * namespace -> namespace for this project, where all the generated code is based on this namespace.
   */
  namespace = build.BuildConfig.APP_ID

  /**
   * compileSdk -> API level is used by Gradle to compile your app(usually the same as targetSdk)
   *-----------------------------------------------------------------
   * (More Details about compileSdk)
   *-----------------------------------------------------------------
   * If your app uses an API that is introduced in API level 26, your compileSdk must be set to minimum 26. If you set it to 25, it fails to compile.
   * Assuming the minSdk is 21, you likely get this warning/error too
   *
   * -----> Call requires API level 26 (current min is 21): android.app.NotificationChannel() <-----
   *
   * As recommended by the Android Studio IDE, you can fix the warning/error by annotating your function with
   *-----------------------------------------------------------------
   * @RequiresApi(Build.VERSION_CODES.O)
   * private fun yourFunction() {
   *     /*...*/
   * }
   *-----------------------------------------------------------------
   *
   * However, if you run your App on the Android version (< API level 26), it will NOT crash but fails silently. You will notice the error messages in the logcat. So, I think adding the @RequiresApi() is a bad practice here.
   * What you should do is handle the code above the app runs below the API level 26.
   *
   *-----------------------------------------------------------------
   * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
   *     /* Code for API level >= 26 */
   * } else {
   *    /* code for API level >= 23 and < 26) */
   * }
   *-----------------------------------------------------------------
   *
   * An example is the notification channel is only required / available for API level 26. Then, we should wrap it with Build.VERSION_CODES.O.
   *
   *-----------------------------------------------------------------
   *if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
   *
   *     val notificationChannel = NotificationChannel(
   *         notificationChannelId,
   *         "DemoWorker",
   *         NotificationManager.IMPORTANCE_DEFAULT,
   *     )
   *
   *     val notificationManager: NotificationManager? =
   *         getSystemService(applicationContext, NotificationManager::class.java)
   *
   *     notificationManager?.createNotificationChannel(notificationChannel)
   * }
   *-----------------------------------------------------------------
   * Conclusion
   *-----------------------------------------------------------------
   * In general,
   * minSdk < targetSdk <= compileSdk
   *
   * but ideally and practically (100% good work),
   * minSdk < targetSdk == compileSdk == latest SDK version
   *
   * Don't use @RequiresApi() and handle the different API versions' behavior in the code.
   *
   * Here is the reference -> https://vtsen.hashnode.dev/minsdk-vs-targetsdk-vs-compilesdk
   */
  compileSdk = build.BuildConfig.COMPILE_SDK_VERSION

  // ───────────────────────────────────────────────────────────────────────────────
  // Default Configurations
  // ───────────────────────────────────────────────────────────────────────────────
  defaultConfig {
    /**
     * defaultConfig {} Block -> This specifies the default configuration for the project.
     */

    /**
     * applicationId -> Unique ID that is used to identify your app on a device or in Google Play Store.
     */
    applicationId = build.BuildConfig.APP_ID

    /**
     * minSdk -> Minimum API Level required for the app to run
     *-----------------------------------------------------------------
     * (More Details about minSdk)
     *-----------------------------------------------------------------
     * If your minSdk is set to 21, your app cannot be run on any Android version that is below API level 21. If the Android version (API level 20) attempts to install your app, you get this error.
     *
     * Installation did not succeed. The application could not be installed: INSTALL_FAILED_OLDER_SDK
     *
     * The Google Play Store prevents the user from installing the app too if the phone's Android version doesn't meet the minSdk requirement by the app.
     *
     * Here is the reference -> https://vtsen.hashnode.dev/minsdk-vs-targetsdk-vs-compilesdk
     */
    minSdk = build.BuildConfig.MIN_SDK_VERSION

    /**
     * targetSdk -> API level the app is designed and tested on (usually the same as compileSdk)
     *-----------------------------------------------------------------
     * (More Details about targetSdk)
     * -----------------------------------------------------------------
     * (**App runs on API level > targetSdk**)
     * If the app is run on the Android version (API level) that is higher than the targetSdk, the Android operating system will try to run backward compatibility behavior to match behavior as in targetSdk API level.
     *
     * For example, runtime app permission is introduced in API level 23. Before API level 23, runtime app permission is not needed.
     *
     * If your targetSdk is set to 22 and your app runs on Android version (API level 23), the Android OS will try to match the behavior as in API level 22. Thus, no runtime permission is requested.
     *
     * If your targetSdk is set to 23 and your app runs on Android version (API level 23 or higher), runtime permission is requested.
     *-----------------------------------------------------------------
     * (**App runs on API level < targetSdk**)
     * What about the app that runs on the Android version (API level) that is < targetSdk? The app behaves based on that Android version (API level).
     *
     * If your targetSdk is set to 23 and your app runs on Android version (API level 22), runtime permission is not requested.
     *
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------
     *                runtime app permission is introduced in API level 23 or higher ||| Before API level 23, runtime app permission is not needed.
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------
     *                                                          In case Of (App runs on API level > targetSdk)
     *                                                          ----------------------------------------------
     * target SDK  22    ||||   your app runs on Android version (API level 23)             ----> no runtime permission is requested. (the android OS will try to match the behavior as in the Api level 22)
     *
     * target SDK  23    ||||   your app runs on Android version (API level 23 or higher)   ----> runtime permission is requested. (the android OS will try to match the behavior as in the Api level 22)
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------
     *                                                          In case Of (App runs on API level < targetSdk)
     *                                                          ----------------------------------------------
     * target SDK  23    ||||   your app runs on Android version (API level 22)             ----> runtime permission is not requested.
     *
     * Here is the reference -> https://vtsen.hashnode.dev/minsdk-vs-targetsdk-vs-compilesdk
     */
    targetSdk = build.BuildConfig.TARGET_SDK_VERSION

    /**
     * versionCode - an integer value that represents the version of your app
     */
    versionCode = release.ReleaseConfig.VERSION_CODE

    /**
     * versionName - string value that represents the user-visible version of your app.
     * It can be any string but is usually based on <major>.<minor>.<point> version format.
     * It doesn't need to match the version code.
     */
    versionName = release.ReleaseConfig.VERSION_NAME

    /**
     * testInstrumentationRunner - specify the library to run the instrumented test
     */
    testInstrumentationRunner = tests.TestBuildConfig.TEST_INSTRUMENTATION_RUNNER

    /**
     * vectorDrawable - set "useSupportLibrary = true" to enable the vector drawable support for your app
     */
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // Signing Configurations
  // ───────────────────────────────────────────────────────────────────────────────
  signingConfigs {
    /**
     * Calls the `create` method from `BuildSigning` to define signing configurations.
     * - `Release` → Used for production releases (signed with a secure keystore).
     * - `ReleaseExternalQa` → Used for QA releases (separate keystore for testing).
     * - `Debug` → Used for development and testing (default debug keystore).
     *
     * **Why Use `BuildSigning` Instead of Defining Signing Manually?**
     * - Centralizes signing logic for **better maintainability**.
     * - Ensures all build types are **properly signed** without duplication.
     */
    sigining.BuildSigning.Release(project).create(this)
    sigining.BuildSigning.ReleaseExternalQa(project).create(this)
    sigining.BuildSigning.Debug(project).create(this)
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // Build Types Configuration
  // ───────────────────────────────────────────────────────────────────────────────
  buildTypes {
    /**
     * `Release` Build Type:
     * - Used for **production releases** (uploaded to Google Play Store).
     * - Enables **ProGuard** to optimize and obfuscate the APK.
     * - Uses the `RELEASE` signing configuration.
     */
    BuildCreator.Release(project).create(this).apply {
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      /* proguardFiles(
         getDefaultProguardFile("proguard-android-optimize.txt"),
         "proguard-rules.pro", // Local per-module rules
         File(rootProject.rootDir, "build-system/proguard/proguard-common-rules.pro"), // Shared rules ✅
       )*/
      signingConfig = signingConfigs.getByName(sigining.SigningTypes.RELEASE)
    }

    /**
     * `Debug` Build Type:
     * - Used for **development and testing**.
     * - Uses the default **debug keystore**.
     * - **ProGuard is disabled** to allow debugging.
     */
    BuildCreator.Debug(project).create(this).apply {
      signingConfig = signingConfigs.getByName(sigining.SigningTypes.DEBUG)
    }

    /**
     * `ReleaseExternalQa` Build Type:
     * - Used for **QA testing** before production release.
     * - Uses a separate QA keystore for **internal testing**.
     */
    BuildCreator.ReleaseExternalQa(project).create(this).apply {
      signingConfig = signingConfigs.getByName(sigining.SigningTypes.RELEASE_EXTERNAL_QA)
    }
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // Product Flavors Configuration
  // ───────────────────────────────────────────────────────────────────────────────
  /**
   * Defines **flavor dimensions** to categorize product flavors.
   * - `APP` → Groups app-based flavors (e.g., `Client`, `Driver`).
   * - `STORE` → Groups store-based flavors (e.g., `Google Play`, `Huawei Store`).
   */
  flavorDimensions.add(BuildDimensions.APP)
  flavorDimensions.add(BuildDimensions.STORE)
  productFlavors {
    /**
     * Defines different **product flavors** to generate multiple APK variants.
     * - `Google` → Google Play Store distribution.
     * - `Huawei` → Huawei AppGallery distribution.
     * - `Client` → Client-side version of the app.
     * - `Driver` → Driver-side version of the app.
     *
     * **Why Use `BuildFlavor` Instead of Defining Flavors Manually?**
     * - Centralizes flavor logic for **easier maintenance**.
     * - Ensures **consistent naming and configurations**.
     */
    BuildFlavor.Google.create(this)
    BuildFlavor.Huawei.create(this)
    BuildFlavor.Client.create(this)
    BuildFlavor.Driver.create(this)
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // Build Features Configuration
  // ───────────────────────────────────────────────────────────────────────────────
  buildFeatures {
    /**
     * Enables the generation of the `BuildConfig` class.
     * - `BuildConfig` is an automatically generated class that contains constants defined in `build.gradle.kts`.
     * - **Example Usage:**
     *   ```kotlin
     *   val appId = BuildConfig.APPLICATION_ID
     *   val version = BuildConfig.VERSION_NAME
     *   ```
     * - **Why Enable `buildConfig`?**
     *   - Stores **app metadata** (e.g., version name, API keys).
     *   - Improves **build-time optimizations** by enabling conditional logic.
     */
    buildConfig = true
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // Java Compilation Options
  // ───────────────────────────────────────────────────────────────────────────────
  compileOptions {
    /**
     * compileOptions {} Block -> This specifies options related to compiling your Java code.
     */

    /**
     *  specifies the Java version that your code uses.
     */
    /**
     * Specifies the **Java source compatibility version**.
     * - Defines the Java language level your code is **written in**.
     * - **Example:**
     *   - `JavaVersion.VERSION_17` → Allows Java 17 features.
     *   - `JavaVersion.VERSION_11` → Uses Java 11 features.
     * - **Why Set `sourceCompatibility`?**
     *   - Ensures **new Java features** (e.g., Records, Sealed Classes) are available.
     *   - Improves **readability** and **efficiency** of code.
     */
    sourceCompatibility = JavaVersion.VERSION_17

    /**
     * specifies the Java runtime version that your code will be executed on.
     */
    /**
     * Specifies the **Java runtime compatibility version**.
     * - Defines the **JVM version** that will execute the compiled Java code.
     * - **Example:**
     *   - `JavaVersion.VERSION_17` → Code runs on **JVM 17**.
     *   - `JavaVersion.VERSION_11` → Code runs on **JVM 11**.
     * - **Why Set `targetCompatibility`?**
     *   - Ensures your compiled code runs **on a specific JVM version**.
     *   - Prevents compatibility issues when running the app on different environments.
     */
    targetCompatibility = JavaVersion.VERSION_17
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // Kotlin Compilation Options
  // ───────────────────────────────────────────────────────────────────────────────
  kotlinOptions {
    /**
     * kotlinOptions {} Block
     */

    /**
     * This specifies options related to compiling your Kotlin code.
     *
     * jvmTarget - specifies the Java Virtual Machine (JVM) version your code will be compiled for. In short, it compiles your code to byte code that is compatible with the JVM version that you specified.
     * If you don't specify any compile / Kotlin options here, the default value will be used which could be different for a different version of the build Gradle plugin.
     *
     * Technically, JVM is for desktop app. For Android app, the runtime is called Dalvik Virtual Machine (DVM) which has been replaced by Android Runtime (ART).
     *
     * If you are curious about the Kotlin compilation process for Android app, you can read this article.
     * https://vtsen.hashnode.dev/android-vs-desktop-app-kotlin-compilation-process
     *
     *
     * Here is another reference : https://vtsen.hashnode.dev/android-vs-desktop-app-kotlin-compilation-process
     */
    /**
     * Specifies **Kotlin JVM Target Version**.
     * - Determines the Java Virtual Machine (JVM) version for compiled Kotlin code.
     * - **Example:**
     *   ```kotlin
     *   kotlinOptions {
     *       jvmTarget = "17"
     *   }
     *   ```
     * - **Why Set `jvmTarget`?**
     *   - Compiles Kotlin code into **bytecode compatible** with the specified JVM version.
     *   - Enables **Kotlin interoperability** with Java 17 features.
     *   - Prevents compatibility issues when running **Kotlin-Java mixed projects**.
     * - **More Info on Kotlin Compilation:**
     *   - https://vtsen.hashnode.dev/android-vs-desktop-app-kotlin-compilation-process
     */

    jvmTarget = "17"
  }

  // ───────────────────────────────────────────────────────────────────────────────
  // APK Packaging Options
  // ───────────────────────────────────────────────────────────────────────────────
  packaging {
    /**
     * packagingOptions {} Block
     * This specifies certain resources to exclude from the Android package - APK or Android bundle files.
     */
    /**
     * `packagingOptions {}` - Defines **resource exclusion rules**.
     * - Prevents unnecessary resources from being included in the final APK/AAB.
     * - **Reduces app size** and improves **build performance**.
     */

    resources {
      /**
       * resources - specifies the set of resources that should be processed during the packaging process
       *
       * In the above example, it excludes the following license files:
       * META-INF/AL2.0
       * META-INF/LGPL2.1
       *
       * The purpose is to reduce the Android package file size.
       */
      /**
       * **Excludes Unnecessary License Files**:
       * - META-INF/AL2.0 → Apache License 2.0
       * - META-INF/LGPL2.1 → GNU Lesser General Public License
       * - **Why Exclude These Files?**
       *   - They **increase APK size** but aren't needed in runtime.
       *   - They **don't affect app behavior** if removed.
       * - **Example Before Exclusion:**
       *   ```plaintext
       *   APK Size: 50MB
       *   Contains: META-INF/AL2.0, META-INF/LGPL2.1
       *   ```
       * - **Example After Exclusion:**
       *   ```plaintext
       *   APK Size: 49MB (1MB reduction)
       *   ```
       */
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }

  /**
   * **Enable Data Binding**
   * - `dataBinding = true` enables **Android Data Binding** in the module.
   * - Data Binding allows you to bind UI components in XML layouts directly to data sources.
   * - Eliminates the need for `findViewById()`, improving code readability and performance.
   * - Required for using `DataBindingUtil`, `ViewDataBinding`, and `BindingAdapters`.
   *
   * **Example Use Case:**
   * 1. Declare a `<layout>` tag in your XML:
   *    ```xml
   *    <layout xmlns:android="http://schemas.android.com/apk/res/android">
   *        <data>
   *            <variable name="user" type="com.example.User" />
   *        </data>
   *        <TextView android:text="@{user.name}" />
   *    </layout>
   *    ```
   * 2. Access the binding object in your Activity/Fragment:
   *    ```kotlin
   *    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
   *    binding.user = User("John Doe")
   *    ```
   *
   * **Note:**
   * - If using ViewBinding instead of Data Binding, use `viewBinding = true` instead.
   * - Data Binding is useful for MVVM architecture and working with LiveData.
   */
  buildFeatures {
    dataBinding = true
  }
}

dependencies {
  /**
   * dependencies {} Block
   *
   * - This declares the dependencies/libraries that your code is needed.
   * - implementation - specifies the library version that should be included in the Android package (APK/ Android bundle).
   *
   * Note: If you have unused dependencies in your Android project, it is better to remove them. This can help reduce the size of the Android package (APK/ Android bundle) and improve build times.
   */

  /**
   * `dependencies {}` Block
   * - This declares the **external libraries and dependencies** needed in your project.
   * - Dependencies can be:
   *   - **implementation** → Used by the app at runtime.
   *   - **api** → Exposes the library to dependent modules.
   *   - **testImplementation** → Used for unit testing.
   *   - **androidTestImplementation** → Used for Android instrumented tests.
   *
   * **Why Manage Dependencies Here?**
   * - **Centralizes** all dependencies for maintainability.
   * - **Prevents duplication** across modules.
   * - **Improves build performance** by reducing unused libraries.
   *
   * **Best Practice:**
   * - Remove unused dependencies to **reduce APK size** and **improve build times**.
   */
  dependencies.presentationModule()
  dependencies.viewModelCasesModule()
  dependencies.loginModule()
  dependencies.remoteDataModule()

  dependencies.defaultLibraries()
  dependencies.jetpackViewModelAndLifecycle()
  dependencies.navigationComponent()

  dependencies.dependencyInjectionHilt()
  dependencies.coroutines()
  dependencies.networking()
}
