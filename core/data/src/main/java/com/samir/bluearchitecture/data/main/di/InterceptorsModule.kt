package com.samir.bluearchitecture.data.main.di

import android.content.Context
import com.samir.bluearchitecture.data.BuildConfig
import com.samir.bluearchitecture.data.main.apiLogger.ApiLoggerInterceptor
import com.samir.bluearchitecture.data.main.connectivity.NetworkMonitorInterface
import com.samir.bluearchitecture.data.main.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.CUSTOM_LOGGING_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.HEADER_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.LANGUAGE_TAG
import com.samir.bluearchitecture.data.main.constants.LOGGING_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.interceptors.ConnectivityInterceptor
import com.samir.bluearchitecture.data.main.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorsModule {

  /**
   * Provides a singleton instance of [ConnectivityInterceptor], which implements [Interceptor]
   * and is used to detect network availability before proceeding with an HTTP request.
   *
   * This interceptor helps prevent unnecessary API calls when there is no network connection,
   * and can be used to throw a custom exception, return an offline error, or trigger
   * retry logic in the app’s networking layer.
   *
   * ---
   * ✅ PURPOSE:
   * - Acts as a guard before the request reaches the server.
   * - Can be used to check if the device is offline and short-circuit the HTTP call.
   * - Useful for showing "No internet" dialogs or skipping execution during offline states.
   *
   * ---
   * ✅ DEPENDENCY: [NetworkMonitorInterface]
   * - This interface abstracts network monitoring logic.
   * - The implementation can use `ConnectivityManager`, `NetworkCallback`, or any third-party lib.
   * - This keeps the interceptor testable and platform-agnostic.
   *
   * ---
   * ✅ SCOPE:
   * - Marked as `@Singleton` because it does not need to be recreated each time.
   * - Also annotated with `@Named(CONNECTIVITY_INTERCEPTOR_TAG)` to distinguish it from other interceptors.
   *
   * ---
   * ✅ USAGE:
   * - Injected into OkHttp client setup to automatically handle network checks.
   * - Example: Add to the `OkHttpClient.Builder.addInterceptor(...)` list.
   *
   * ---
   * @param networkMonitorInterface Interface to check current network connectivity status.
   * @return An [Interceptor] instance that checks for network availability before request execution.
   */
  @Provides
  @Singleton
  @Named(CONNECTIVITY_INTERCEPTOR_TAG)
  fun provideConnectivityInterceptor(
    networkMonitorInterface: NetworkMonitorInterface,
  ): Interceptor {
    return ConnectivityInterceptor(
      networkMonitorInterface,
    )
  }

  /**
   * Provides a singleton instance of [HeaderInterceptor], which is responsible for adding
   * custom headers to every outgoing HTTP request — most importantly the `Accept-Language` header.
   *
   * This enables the backend to receive and respond with localized content based on the user's
   * current app language (e.g., "en", "ar").
   *
   * ---
   * ✅ PURPOSE:
   * - To attach headers like `Accept-Language` dynamically to all API requests.
   * - Centralizes header logic in a clean, reusable interceptor layer.
   * - Ensures that the API is always aware of the user’s active language.
   *
   * ---
   * ✅ DEPENDENCY: `@Named(LANGUAGE_TAG) languageProvider: () -> Locale`
   * - Injects a lambda function that provides the current [Locale] dynamically.
   * - This ensures that language changes at runtime are respected without needing to recreate
   *   the interceptor or re-initialize OkHttp.
   * - The lambda is provided via a separate module (e.g., `LocaleModule` or `AppConfigModule`).
   *
   * ---
   * ✅ SCOPE:
   * - Marked as `@Singleton` because the interceptor is stateless and can be reused.
   * - Uses `@Named(HEADER_INTERCEPTOR_TAG)` to distinguish it from other interceptors (e.g., logging, connectivity).
   *
   * ---
   * ✅ HEADER STRATEGY:
   * - The interceptor is expected to extract `languageProvider().language`, e.g., `"en"` or `"ar"`,
   *   and add it to the HTTP headers:
   *
   *     Accept-Language: en
   *
   * - This supports backend APIs that serve responses in multiple languages.
   *
   * ---
   * ✅ USAGE:
   * - Injected into your OkHttp client setup:
   *
   *     OkHttpClient.Builder()
   *       .addInterceptor(headerInterceptor)
   *       .build()
   *
   * - Works alongside other interceptors like authentication, connectivity, or logging.
   *
   * ---
   * @param languageProvider A lambda function that returns the current [Locale] used by the app.
   * @return An [Interceptor] that adds custom headers, including `Accept-Language`, to HTTP requests.
   */
  @Provides
  @Singleton
  @Named(HEADER_INTERCEPTOR_TAG)
  fun provideHeaderInterceptor(
    @Named(LANGUAGE_TAG) languageProvider: () -> Locale,
    @ApplicationContext context: Context,
  ): Interceptor {
    return HeaderInterceptor(
      languageProvider,
      context,
    )
  }

  /**
   * Provides an [HttpLoggingInterceptor] instance for network logging using OkHttp’s built-in logging utility.
   *
   * - **Debug Build**: Logs full request and response body (`Level.BODY`).
   * - **Release Build**: Disables logging (`Level.NONE`) to avoid leaking sensitive data.
   *
   * This interceptor is simple, human-readable, and useful during early development stages or quick inspections.
   *
   * ### Named Qualifier:
   * Use the constant [LOGGING_INTERCEPTOR_TAG] to inject this specific interceptor when needed.
   *
   * ```kotlin
   * @Named(LOGGING_INTERCEPTOR_TAG)
   * fun provideOkHttpLoggingInterceptor(): Interceptor
   * ```
   *
   * @return Configured [Interceptor] instance.
   */
  @Provides
  @Singleton
  @Named(LOGGING_INTERCEPTOR_TAG)
  fun provideOkHttpLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor.Level.BODY
    } else {
      HttpLoggingInterceptor.Level.NONE
    }

    return interceptor
  }

  /**
   * Provides a custom [Interceptor] implementation for advanced API request/response logging.
   *
   * - Uses [ApiLoggerInterceptor] to format logs with more structure and detail (headers, body, duration).
   * - In **release builds**, logging is disabled by returning a no-op interceptor that simply proceeds with the request.
   *
   * This is especially useful when custom formatting or behavior is required beyond OkHttp’s default.
   *
   * ### Named Qualifier:
   * Use the constant [CUSTOM_LOGGING_INTERCEPTOR_TAG] to inject this interceptor where needed.
   *
   * ```kotlin
   * @Named(CUSTOM_LOGGING_INTERCEPTOR_TAG)
   * fun provideApiLoggerInterceptor(): Interceptor
   * ```
   *
   * @return [ApiLoggerInterceptor] in debug mode; otherwise, a no-op interceptor.
   */
  @Provides
  @Singleton
  @Named(CUSTOM_LOGGING_INTERCEPTOR_TAG)
  fun provideApiLoggerInterceptor(): Interceptor {
    return if (BuildConfig.DEBUG) ApiLoggerInterceptor() else Interceptor { it.proceed(it.request()) }
  }
}
