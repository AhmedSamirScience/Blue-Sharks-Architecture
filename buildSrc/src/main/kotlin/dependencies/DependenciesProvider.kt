package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import tests.TestDependencies

fun DependencyHandler.room() {
//    implementation(Dependencies.roomKtx)
//    implementation(Dependencies.roomRuntime)
//    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.dataStore() {
//    implementation(Dependencies.datastore)
//    implementation(Dependencies.kotlinCollections)
}
fun DependencyHandler.kotlinx() {
   // implementation(Dependencies.kotlinSerilaizations)
}

fun DependencyHandler.protoDataStore() {
//    implementation(Dependencies.datastore)
//    implementation(Dependencies.protoBufJavaLite)
//    implementation(Dependencies.protoBufKotlinLite)
}

fun DependencyHandler.chucker() {
//    releaseImplementation(Dependencies.chuckerRelease)
//    debugImplementation(Dependencies.chuckerDebug)
}

//fun DependencyHandler.retrofit() {
//    implementation(Dependencies.retrofit)
//    implementation(Dependencies.retrofitConverterGson)
//    implementation(Dependencies.retrofitKotlinCoroutinesAdapter)
//}

fun DependencyHandler.okHttp() {
//    implementation(Dependencies.okHttp)
//    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.hilt() {
//    implementation(Dependencies.hiltAndroid)
//    implementation(Dependencies.hiltCompose)
//    implementation(Dependencies.hiltNavigation)
//    kapt(Dependencies.hiltCompiler)
//    kapt(Dependencies.hiltAgp)
//    kapt(Dependencies.hiltCompilerKapt)

}

fun DependencyHandler.androidx() {
//    implementation(Dependencies.ANDROIDX_CORE)
//    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
//    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
//    implementation(Dependencies.ANDROIDX_UI)
//    implementation(Dependencies.ANDROIDX_UI_GRAPHICS)
//    implementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
//    implementation(Dependencies.ANDROIDX_MATERIAL3)
//    implementation(Dependencies.WORK_RUNTIME)
//    implementation(Dependencies.APP_COMPAT)
//    implementation(Dependencies.MATERIAL)
//    implementation(Dependencies.ANDROIDX_ACTIVITY)
//    implementation(Dependencies.COMPOSE_MATERIAL)
//    implementation(Dependencies.COMPOSE_COMPILER)
//    implementation(Dependencies.COMPOSE_RUNTIME)
//    implementation(Dependencies.navigation)
//    implementation(Dependencies.navigation2)
//    implementation(Dependencies.navigationFragmentKtx)
//    implementation(Dependencies.googleJson)
}

fun DependencyHandler.appModule() {
    moduleImplementation(project(":app"))
}

fun DependencyHandler.loginModule() {
    moduleImplementation(project(":feature:login"))
}

fun DependencyHandler.viewModelCasesModule() {
    moduleImplementation(project(":feature:viewmodelcases"))
}

fun DependencyHandler.dataModule() {
    moduleImplementation(project(":core:data"))
}

fun DependencyHandler.dataStoreModule() {
    moduleImplementation(project(":core:datastore"))
}

fun DependencyHandler.protoDataStoreModule() {
    moduleImplementation(project(":core:protodatastore"))
}

fun DependencyHandler.domainModule() {
    moduleImplementation(project(":core:domain"))
}

fun DependencyHandler.uiModule() {
    moduleImplementation(project(":core:ui"))
}
fun DependencyHandler.navigatorModule() {
    moduleImplementation(project(":core:navigator"))
}

fun DependencyHandler.presentationModule() {
    moduleImplementation(project(":core:presentation"))
}
fun DependencyHandler.paymentModule() {
    moduleImplementation(project(":core:payment"))
}

fun DependencyHandler.homeModule() {
    moduleImplementation(project(":features:home"))
}
fun DependencyHandler.signupModule() {
    moduleImplementation(project(":features:signup"))
}

fun DependencyHandler.testDeps() {
    //testImplementation(TestDependencies.ANDROIDX_JUNIT)
}

fun DependencyHandler.testImplDeps() {
    //androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    //androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    //androidTestImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST)
}

fun DependencyHandler.testDebugDeps() {
    //debugImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_MANIFEST)
}

////////////////////////////////////////////////////////

fun DependencyHandler.defaultLibraries() {
    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.TEST_EXT)
    androidTestImplementation(Dependencies.TEST_ESPRESSO)
}

fun DependencyHandler.navGraph() {
    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_KTX)
}

fun DependencyHandler.viewModel() {
    implementation(Dependencies.LEGACY_SUPPORT)
    implementation(Dependencies.LIFECYCLE_LIVE_DATA)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    //implementation(Dependencies.lifecycleViewModelCompose)
}

fun DependencyHandler.applyScalableDimensions() {
    implementation(Dependencies.INTUIT_SDB)
    implementation(Dependencies.INTUIT_SSP)
}

fun DependencyHandler.gifDrawable() {
    implementation(Dependencies.GIF_DRAWABLE)
}

fun DependencyHandler.lifecycleRuntimeKtx() {
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    //  implementation(Dependencies.hiltLifeCycleViewModel)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    kapt(Dependencies.ANDROID_HILT_COMPILER)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.KOTLINX_COROUTINES_CORE)
    implementation(Dependencies.KOTLINX_COROUTINES_ANDROID)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT_SQUARE_UP)
    implementation(Dependencies.RETROFIT_SQUARE_UP_CONVERTER_GSON)
    implementation(Dependencies.OKHTTP_SQUARE_UP)
    implementation(Dependencies.INTERCEPTOR_SQUARE_UP)
}


