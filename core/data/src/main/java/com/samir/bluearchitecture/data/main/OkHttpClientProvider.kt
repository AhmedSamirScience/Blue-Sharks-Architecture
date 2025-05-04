package com.samir.bluearchitecture.data.main

import com.samir.bluearchitecture.data.main.okhttp.OkHttpClientProviderInterface
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

/**
 * [OkHttpClientProvider] is a concrete implementation of [OkHttpClientProviderInterface]
 * responsible for providing a customized [OkHttpClient.Builder] and controlling network
 * request execution across the application.
 *
 * This class helps centralize:
 * - Creation of [OkHttpClient] instances with shared configurations.
 * - Cancellation of all ongoing or queued HTTP requests using OkHttp's [Dispatcher].
 *
 * ---
 * ✅ KEY RESPONSIBILITIES:
 * - Attach a shared [Dispatcher] to all OkHttp clients created via [getOkHttpClient()].
 * - Enable global request cancellation through [cancelAllRequests()].
 *
 * ---
 * ✅ USAGE SCENARIOS:
 * - Injected into your networking DI module (e.g., Hilt [Network Module]).
 * - Used in logout flows, navigation transitions, or retries to cancel pending HTTP calls.
 */
class OkHttpClientProvider : OkHttpClientProviderInterface {

  // Shared dispatcher instance used to control request execution and cancellation
  private var dispatcher = Dispatcher()

  /**
   * Returns a new [OkHttpClient.Builder] instance that shares the same [dispatcher].
   *
   * This ensures that all clients built through this provider can be centrally controlled
   * for task execution limits and cancellation.
   *
   * @return [OkHttpClient.Builder] with shared dispatcher.
   * @throws RuntimeException if the builder initialization fails.
   */
  override fun getOkHttpClient(): OkHttpClient.Builder {
    try {
      val builder = OkHttpClient.Builder()
      builder.dispatcher(dispatcher)
      return builder
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  /**
   * Cancels all currently executing and queued HTTP requests handled by the shared [dispatcher].
   *
   * This is typically used in the following scenarios:
   * - When a user logs out and you want to cancel inflight network requests.
   * - When navigating away from a screen that initiated long-running calls.
   * - When retrying a failed request and you want to discard previous attempts.
   */
  override fun cancelAllRequests() {
    dispatcher.cancelAll()
  }
}
