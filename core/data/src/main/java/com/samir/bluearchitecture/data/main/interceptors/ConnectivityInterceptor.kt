package com.samir.bluearchitecture.data.main.interceptors

import com.samir.bluearchitecture.data.main.connectivity.NetworkMonitorInterface
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * [ConnectivityInterceptor] is an OkHttp [Interceptor] that ensures
 * the device has an active network connection before any HTTP request is executed.
 *
 * This is part of the app's resilient networking layer — allowing it to fail fast
 * when the network is unavailable, instead of wasting time attempting unreachable requests.
 *
 * ---
 * ✅ RESPONSIBILITIES:
 * - Checks network availability via [NetworkMonitorInterface].
 * - Throws a custom [NoConnectivityException] if there’s no internet connection.
 * - Lets the request proceed as usual if the device is connected.
 *
 * ---
 * ✅ WHY USE THIS INTERCEPTOR:
 * - To avoid unnecessary API requests when offline.
 * - To trigger custom error handling (e.g., "no internet" UI messages).
 * - To help distinguish network failures from server/API failures.
 *
 * ---
 * ✅ USAGE:
 * - Injected via Hilt and added to OkHttpClient builder:
 *     OkHttpClient.Builder()
 *       .addInterceptor(connectivityInterceptor)
 *       .build()
 *
 * ---
 * @param networkMonitorInterface Abstracted interface to check current connectivity.
 */
class ConnectivityInterceptor(
  private val networkMonitorInterface: NetworkMonitorInterface,
) : Interceptor {

  /**
   * Intercepts the outgoing request to check for network connectivity before proceeding.
   *
   * @param chain The OkHttp [Interceptor.Chain] representing the current request.
   * @return [Response] if the device is online.
   * @throws [NoConnectivityException] if the device has no internet connection.
   */
  override fun intercept(chain: Interceptor.Chain): Response {
    // Check if the device is connected to the internet
    if (networkMonitorInterface.hasConnectivity()) {
      // Proceed with the original request as normal
      return chain.proceed(chain.request())
    } else {
      // Block the request and throw a connectivity-specific exception
      throw NoConnectivityException
    }
  }
}

/**
 * A custom exception used by [ConnectivityInterceptor] when there is no internet connection.
 *
 * Extends [IOException] so that it behaves like a network error in OkHttp’s retry and error
 * handling mechanisms. This allows the app to gracefully catch it and show appropriate UI feedback.
 */
object NoConnectivityException : IOException()
