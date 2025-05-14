package com.samir.bluearchitecture.data.main.remote.okhttp

import okhttp3.OkHttpClient

/**
 * Interface definition for providing and managing [OkHttpClient] instances.
 *
 * This abstraction allows for decoupling the creation and control of [OkHttpClient] from consumers,
 * supporting testability, mocking, and central configuration in a modular architecture.
 */
interface OkHttpClientProviderInterface {

  /**
   * Returns a new [OkHttpClient.Builder] instance.
   *
   * ### Purpose:
   * - Allows consumers to add interceptors, timeouts, and configure HTTP behavior.
   * - Promotes reusable and centralized configuration of OkHttp.
   *
   * @return A fresh [OkHttpClient.Builder] to build a customized OkHttp client.
   */
  fun getOkHttpClient(): OkHttpClient.Builder

  /**
   * Cancels all in-flight or queued HTTP requests managed by the current [OkHttpClient] instance.
   *
   * ### Use Cases:
   * - Cancelling requests when a user logs out or leaves the screen.
   * - Preventing memory leaks or unnecessary background work.
   */
  fun cancelAllRequests()
}
