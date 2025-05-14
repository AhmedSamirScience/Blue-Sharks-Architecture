package com.samir.bluearchitecture.data.main.remote.constants

import com.samir.bluearchitecture.data.BuildConfig

//region --- Dispatcher Qualifiers ---
/**
 * Used as `@Named` tags to differentiate coroutine dispatchers injected via Hilt.
 * These tags allow the injection of specific dispatchers (Main, Default, IO)
 * to be scoped to use cases, repositories, or background workers.
 */

// / Represents the main thread, typically used for UI operations.
const val DISPATCHER_MAIN_TAG = "dispatcherMain"

// / Represents the default dispatcher, optimized for CPU-intensive work.
const val DISPATCHER_DEFAULT_TAG = "dispatcherDefault"

// / Represents the IO dispatcher, optimized for disk or network IO operations.
const val DISPATCHER_IO_TAG = "dispatcherIO"
//endregion

//region --- Language and Header Interceptors ---
/**
 * These constants are used as `@Named` qualifiers in Hilt modules
 * to distinguish different types of interceptors or providers.
 */

// / Qualifier for providing the `() -> Locale` lambda function representing the current app language.
const val LANGUAGE_TAG = "Language"

// / Qualifier for the OkHttp `HeaderInterceptor` that adds headers like Accept-Language, User-Agent, etc.
const val HEADER_INTERCEPTOR_TAG = "HeaderInterceptor"
//endregion

//region  --- Connectivity and Logging Interceptors ---

// / Qualifier for the OkHttp `ConnectivityInterceptor` that blocks requests when offline.
const val CONNECTIVITY_INTERCEPTOR_TAG = "ConnectivityInterceptor"

// / Qualifier for the OkHttp `HttpLoggingInterceptor` used for network debugging in development builds.
const val LOGGING_INTERCEPTOR_TAG = "OkHttpLoggingInterceptor"

// / Qualifier for the `AuthenticationInterceptor` that adds authentication headers to requests.
const val CUSTOM_LOGGING_INTERCEPTOR_TAG = "customLoggingInterceptor"
//endregion

//region --- Api Remote Data ---
const val TAG_API_LOGGER = "MyAppLogger (Api)" // Tag used for logcat output
const val BASE_URL = BuildConfig.BASE_URL // Tag used for logcat output
const val CONNECT_TIMEOUT: Long = 10 // 10 seconds
const val READ_TIMEOUT: Long = 30 // 30 seconds
const val WRITE_TIMEOUT: Long = 15 // 30 seconds
//endregion

//endregion
