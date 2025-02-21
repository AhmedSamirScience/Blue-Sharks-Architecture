package dependencies

object Dependencies {
    //region default
    const val ANDROID_CORE = "androidx.core:core-ktx:${DependenciesVersions.ANDROID_CORE}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${DependenciesVersions.APP_COMPAT}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${DependenciesVersions.ANDROID_MATERIAL}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${DependenciesVersions.CONSTRAINT_LAYOUT}"
    const val JUNIT = "junit:junit:${DependenciesVersions.JUNIT}"
    const val TEST_EXT = "androidx.test.ext:junit:${DependenciesVersions.TEST_EXT}"
    const val TEST_ESPRESSO = "androidx.test.espresso:espresso-core:${DependenciesVersions.TEST_ESPRESSO}"
    //endregion

    //region Navigation Graph for Kotlin language implementation
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.NAV_VERSION}"
    const val NAVIGATION_KTX = "androidx.navigation:navigation-ui-ktx:${DependenciesVersions.NAV_VERSION}"
    //endregion

    //region viewModel dependencies
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${DependenciesVersions.LEGACY_SUPPORT}"
    const val LIFECYCLE_LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${DependenciesVersions.LIFECYCLE_LIVE_DATA}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.LIFECYCLE_VIEWMODEL}"
    //const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${dependencies.DependenciesVersions.lifecycleViewModelCompose}"
    //endregion

    //region design margins
    const val INTUIT_SDB = "com.intuit.sdp:sdp-android:${DependenciesVersions.DESIGN_MARGINS}"
    const val INTUIT_SSP = "com.intuit.ssp:ssp-android:${DependenciesVersions.DESIGN_MARGINS}"
    //endregion

    // region gif drawable
    const val GIF_DRAWABLE = "pl.droidsonroids.gif:android-gif-drawable:${DependenciesVersions.GIF_DRAWABLE}"
    //endregion

    // region gif lifecycleRuntimeKtx
    /**
     * this is to access life cycle scope
     */
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFECYCLE_RUNTIME_KTX}"
    //endregion

    //region dagger Hilt
    const val DAGGER_HILT_ANDROID = "com.google.dagger:hilt-android:${DependenciesVersions.DAGGER_HILT_ANDROID}"
    //const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${dependencies.DependenciesVersions.HILT_LIFECYCLE_VIEWMODEL}"

    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-compiler:${DependenciesVersions.DAGGER_HILT_COMPILER}"
    const val ANDROID_HILT_COMPILER = "androidx.hilt:hilt-compiler:${DependenciesVersions.ANDROID_HILT_COMPILER}"
    //endregion

    //region Coroutines
    const val KOTLINX_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependenciesVersions.KOTLINX_COROUTINE_CORE}"
    const val KOTLINX_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependenciesVersions.KOTLINX_COROUTINES_ANDROID}"
    //endregion

    //region retrofit
    const val RETROFIT_SQUARE_UP = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT_SQUARE_UP}"
    const val RETROFIT_SQUARE_UP_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT_SQUARE_UP_CONVERTER_GSON}"
    const val OKHTTP_SQUARE_UP = "com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP_SQUARE_SQUARE_UP}"
    const val INTERCEPTOR_SQUARE_UP = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP_SQUARE_SQUARE_UP}"
    //endregion
}