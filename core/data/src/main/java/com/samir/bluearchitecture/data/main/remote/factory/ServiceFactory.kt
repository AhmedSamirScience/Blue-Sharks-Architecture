package com.samir.bluearchitecture.data.main.remote.factory

import retrofit2.Retrofit

/**
 * A factory class responsible for creating Retrofit service interfaces.
 *
 * This class wraps a [Retrofit] instance and provides a generic `create()` method
 * to instantiate API interfaces, promoting reusability and consistency across modules.
 *
 * ---
 * ✅ PURPOSE:
 * - Avoids duplicating `retrofit.create(...)` throughout the codebase.
 * - Centralizes Retrofit service creation to improve testability and DI.
 * - Makes it easy to swap out or mock Retrofit during testing.
 *
 * ---
 * ✅ USAGE EXAMPLE:
 * ```kotlin
 * val authApi = serviceFactory.create(AuthApi::class.java)
 * val userApi = serviceFactory.create(UserApi::class.java)
 * ```
 *
 * ---
 * @param retrofit The configured Retrofit instance used to create service implementations.
 */

class ServiceFactory(private val retrofit: Retrofit) {

  /**
   * Creates an implementation of the given Retrofit service interface.
   *
   * @param T The Retrofit service interface type.
   * @param service The [Class] of the Retrofit service interface.
   * @return An implementation of the specified Retrofit service.
   */
  fun <T> create(service: Class<T>): T {
    return retrofit.create(service)
  }
}
