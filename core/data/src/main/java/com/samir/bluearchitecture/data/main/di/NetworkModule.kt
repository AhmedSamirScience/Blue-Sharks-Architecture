package com.samir.bluearchitecture.data.main.di

import android.content.Context
import com.google.gson.Gson
import com.samir.bluearchitecture.data.main.OkHttpClientProvider
import com.samir.bluearchitecture.data.main.connectivity.NetworkMonitorImplementer
import com.samir.bluearchitecture.data.main.connectivity.NetworkMonitorInterface
import com.samir.bluearchitecture.data.main.constants.BASE_URL
import com.samir.bluearchitecture.data.main.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.CONNECT_TIMEOUT
import com.samir.bluearchitecture.data.main.constants.CUSTOM_LOGGING_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.HEADER_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.LOGGING_INTERCEPTOR_TAG
import com.samir.bluearchitecture.data.main.constants.READ_TIMEOUT
import com.samir.bluearchitecture.data.main.constants.WRITE_TIMEOUT
import com.samir.bluearchitecture.data.main.factory.ServiceFactory
import com.samir.bluearchitecture.data.main.okhttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Hilt module responsible for providing networking-related dependencies such as
 * Gson, OkHttpClient, Retrofit, and service factories.
 *
 * All provided instances are scoped to the [SingletonComponent] (application-level).
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  /**
   * Provides a singleton instance of [StringResourceProvider] for dependency injection.
   *
   * This method is annotated with `@Provides` and `@Singleton`, allowing Hilt to know how
   * to create and cache a single instance of [StringResourceProvider] throughout the application's lifecycle.
   *
   * ---
   * ### 🧩 Dependencies:
   * - **@ApplicationContext**: Injects the application-level [Context], ensuring the provider has access
   *   to application-wide resources (e.g., string resources) without leaking an Activity or Fragment.
   *
   * ---
   * ### ✅ Use Case:
   * This provider is useful for accessing localized string resources in a testable and centralized way
   * across ViewModels, UseCases, or other components that should not directly depend on Android framework classes.
   *
   * ---
   * ### 📦 Example Injection:
   * ```kotlin
   * @Inject lateinit var stringProvider: StringResourceProvider
   * val title = stringProvider.getString(R.string.welcome_title)
   * ```
   *
   * @param context Application context injected by Hilt via `@ApplicationContext`.
   * @return A singleton instance of [StringResourceProvider].
   */
  @Singleton
  @Provides
  fun provideResourceProvider(@ApplicationContext context: Context): StringResourceProvider {
    return StringResourceProvider(context)
  }

  /**
   * Provides a singleton [Gson] instance for JSON serialization/deserialization.
   */
  @Provides
  @Singleton
  fun provideGson(): Gson = Gson()

  /**
   * Provides a singleton implementation of [NetworkMonitorInterface] to observe
   * network connectivity changes using the application context.
   *
   * @param context Application-level context injected by Hilt.
   */
  @Provides
  @Singleton
  fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitorInterface {
    return NetworkMonitorImplementer(context)
  }

  /**
   * Provides a singleton [OkHttpClientProviderInterface] used to construct OkHttpClient
   * instances in a centralized and modular way.
   */
  @Provides
  @Singleton
  fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
    return OkHttpClientProvider()
  }

  /**
   * Builds and provides a fully-configured [OkHttpClient] with interceptors injected by tag.
   *
   * Interceptors:
   * - `headerInterceptor`: Adds headers to each request (e.g. auth tokens).
   * - `connectivityInterceptor`: Checks for active network connection.
   * - `okHttpLoggingInterceptor`: Logs network calls using OkHttp's built-in logger.
   * - `customLoggingInterceptor`: Logs network calls using custom format.
   *
   * Other Config:
   * - Retries on failure.
   * - Times out after 60 seconds.
   *
   * @param okHttpClientProvider Helper class to prepare OkHttp client builders.
   */
  @Provides
  @Singleton
  fun provideOkHttpCallFactory(
    @Named(LOGGING_INTERCEPTOR_TAG) okHttpLoggingInterceptor: Interceptor,
    @Named(CUSTOM_LOGGING_INTERCEPTOR_TAG) customLoggingInterceptor: Interceptor,
    @Named(HEADER_INTERCEPTOR_TAG) headerInterceptor: Interceptor,
    @Named(CONNECTIVITY_INTERCEPTOR_TAG) connectivityInterceptor: Interceptor,
    okHttpClientProvider: OkHttpClientProviderInterface,
  ): OkHttpClient {
    return okHttpClientProvider.getOkHttpClient()
      .addInterceptor(headerInterceptor)
      .addInterceptor(connectivityInterceptor)
      .addInterceptor(okHttpLoggingInterceptor)
      .addInterceptor(customLoggingInterceptor)
      .retryOnConnectionFailure(true)
      /**
       * Sets the maximum time to wait while connecting to the server.
       * This includes DNS resolution, TCP handshake, and SSL negotiation.
       *
       * - Default best practice: 10 seconds
       * - Increase only for poor network conditions or file transfers
       */
      .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
      /**
       * Sets the maximum time to wait for the server to respond **after connection**.
       * It starts counting once the request is sent and the connection is established.
       *
       * - Default best practice: 30 seconds
       * - Increase for slow APIs or large responses
       */
      .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
      /**
       * Sets the maximum time to wait while sending the request body to the server.
       * It’s especially important when uploading files or large payloads.
       *
       * - Default best practice: 15 seconds
       * - Increase for file uploads or slow upstream bandwidth
       */
      .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
      /**
       * Prevents automatic redirects (HTTP 3xx), allowing you to handle them manually.
       */
      .followRedirects(false)
      /**
       * Disables automatic following of HTTPS-to-HTTPS redirects.
       * Useful if you want strict control over redirect behavior.
       */
      .followSslRedirects(false)
      .build()
  }

  /**
   * Provides a singleton [Retrofit] instance configured with:
   * - Base URL for the API
   * - Gson converter
   * - Injected OkHttp client
   *
   * @param okHttpClient The preconfigured OkHttp client from [provideOkHttpCallFactory].
   */
  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  /**
   * Provides a singleton instance of [ServiceFactory], which is used to create
   * Retrofit service interfaces across modules.
   *
   * @param retrofit The configured [Retrofit] instance.
   */
  @Provides
  @Singleton
  fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
    return ServiceFactory(retrofit)
  }
}
