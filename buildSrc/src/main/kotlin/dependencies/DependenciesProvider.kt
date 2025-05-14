package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project


    //region App & Feature Modules
    fun DependencyHandler.appModule() {
        moduleImplementation(project(":app"))
    }
    fun DependencyHandler.dataModule() {
        moduleImplementation(project(":core:data"))
    }
    fun DependencyHandler.domainModule() {
        moduleImplementation(project(":core:domain"))
    }
    fun DependencyHandler.presentationModule() {
        moduleImplementation(project(":core:presentation"))
    }
        fun DependencyHandler.uiModule() {
        moduleImplementation(project(":core:ui"))
    }
    fun DependencyHandler.uiModuleApi() {
        moduleApi(project(":core:ui"))
    }
    fun DependencyHandler.viewModelCasesModule() {
        moduleImplementation(project(":feature:viewmodelcases"))
    }
    fun DependencyHandler.loginModule() {
        moduleImplementation(project(":feature:login"))
    }
    fun DependencyHandler.remoteDataModule() {
        moduleImplementation(project(":feature:remotedata"))
    }
    fun DependencyHandler.offlineDataModule() {
        moduleImplementation(project(":feature:offlinedata"))
    }
    //endregion

    //region Libraries

    //region Core Android + UI Libraries
    /**
    * Includes core Android dependencies such as AppCompat, Material Components, and ConstraintLayout.
    * Also includes unit and instrumentation testing libraries.
    *
    * ✅ Use this in **every module that deals with UI** or needs basic Android platform features.
    * ✅ Includes testing setup for `test/` and `androidTest/`.
    */
    fun DependencyHandler.defaultLibraries() {
        //Android Core + UI Framework
        implementation(Dependencies.ANDROID_CORE)
        implementation(Dependencies.APP_COMPAT)
        implementation(Dependencies.ANDROID_MATERIAL)
        implementation(Dependencies.CONSTRAIN_LAYOUT)
        implementation(Dependencies.LEGACY_SUPPORT)

        //Testing (Unit + Instrumentation)
        testImplementation(Dependencies.JUNIT)
        androidTestImplementation(Dependencies.TEST_EXT)
        androidTestImplementation(Dependencies.TEST_ESPRESSO)
    }
    //endregion

    //region Jetpack Architecture Components
    /**
    * Adds lifecycle-aware components such as ViewModel, LiveData, and lifecycle-aware coroutine scopes.
    *
    * ✅ Use this in **any module that holds UI-related logic**, such as ViewModels or state management.
    * ✅ Required for `viewModelScope`, `lifecycleScope`, `LiveData.observe()`.
    */
    fun DependencyHandler.jetpackViewModelAndLifecycle() {
        implementation(Dependencies.LIFECYCLE_LIVE_DATA)
        implementation(Dependencies.LIFECYCLE_VIEWMODEL)
        implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    }
    //endregion

    //region Navigation Component
    /**
    * Adds Jetpack Navigation support for fragments and UI controls (e.g., Toolbar, BottomNav).
    *
    * ✅ Use this in **modules with UI that involve screen navigation** (Fragment-based or Compose).
    * ✅ Supports `findNavController()`, Safe Args, toolbar back navigation, and more.
    */
    fun DependencyHandler.navigationComponent() {
        implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
        implementation(Dependencies.NAVIGATION_KTX)
    }
    //endregion

    //region Dependency Injection (Hilt)
    /**
    * Adds Hilt support for dependency injection and annotation processing.
    *
    * ✅ Use this in **modules that require injection via `@Inject`, `@HiltViewModel`, or `@AndroidEntryPoint`**.
    * ✅ Works with ViewModels, Fragments, Activities, Services, and Workers.
    */
    fun DependencyHandler.dependencyInjectionHilt() {
        implementation(Dependencies.DAGGER_HILT_ANDROID)
        //  implementation(Dependencies.hiltLifeCycleViewModel)
        kapt(Dependencies.DAGGER_HILT_COMPILER)
        kapt(Dependencies.ANDROID_HILT_COMPILER)
    }
    //endregion

    //region Kotlin Coroutines
    /**
    * Adds core and Android-specific coroutine support.
    *
    * ✅ Use this in **modules that handle background tasks, async APIs, or flow collection**.
    * ✅ Enables `Dispatchers.Main`, `Dispatchers.IO`, `withContext`, `viewModelScope`, etc.
    */
    fun DependencyHandler.coroutines() {
        implementation(Dependencies.KOTLINX_COROUTINES_CORE)
        implementation(Dependencies.KOTLINX_COROUTINES_ANDROID)
    }
    //endregion

    //region Networking (Retrofit + OkHttp)
    /**
    * Adds Retrofit and OkHttp for HTTP networking.
    *
    * ✅ Use this in **data modules** or any module that needs to make REST API calls.
    * ✅ Includes JSON conversion and logging capabilities.
    */
    fun DependencyHandler.networking() {
        implementation(Dependencies.RETROFIT_SQUARE_UP)
        implementation(Dependencies.RETROFIT_SQUARE_UP_CONVERTER_GSON)
        implementation(Dependencies.OKHTTP_SQUARE_UP)
        implementation(Dependencies.INTERCEPTOR_SQUARE_UP)
    }
    //endregion

    //region UI Responsiveness (SDP & SSP)
    /**
    * Adds responsive layout and text size utilities.
    *
    * ✅ Use this in **UI modules** where screen size scaling matters (e.g., margin, padding, text size).
    * ✅ Useful for tablets, multi-resolution phones, and accessibility.
    */
    fun DependencyHandler.applyScalableDimensions() {
        //Design & Dimensions (Responsive UI)
        implementation(Dependencies.INTUIT_SDB)
        implementation(Dependencies.INTUIT_SSP)
    }
    //endregion

    //region Media & Animations
    /**
    * Adds native GIF drawable support for loading and controlling animated GIFs.
    *
    * ✅ Use this in **UI modules** that require GIF display (e.g., loading indicators, animated banners).
    */
    fun DependencyHandler.gifMedia() {
        implementation(Dependencies.GIF_DRAWABLE)
    }
    //endregion

    //endregion

    //region Worker Manager
    fun DependencyHandler.workerManager() {
        implementation(Dependencies.WORK_RUNTIME)
        implementation(Dependencies.HILT_WORK)
    }
    //endregion


    //region Room Data Base
    fun DependencyHandler.roomDatabase() {
        implementation(Dependencies.ROOM_RUNTIME)
        kapt(Dependencies.ROOM_COMPILER)
        implementation(Dependencies.ROOM_KTX)
    }
    //endregion


