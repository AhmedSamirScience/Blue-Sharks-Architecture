package com.samir.bluearchitecture.data.main.uiState

/**
 * A sealed class that models the result of a data operation (e.g., API or database request).
 *
 * ---
 * ### ✅ Purpose:
 * `RequestResource` provides a structured way to represent the outcome of a request
 * by encapsulating either the data (`Success`) or an error state (`Error`, `ErrorResponse`)
 * in a type-safe and readable form.
 *
 * It is especially useful in ViewModels or UseCases to expose data-fetching states
 * while keeping the UI layer clean and decoupled from implementation details.
 *
 * ---
 * ### 📦 Usage Example:
 * ```kotlin
 * val result: RequestResource<User> = userRepository.fetchUser()
 * when (result) {
 *     is RequestResource.Success -> showUser(result.data)
 *     is RequestResource.Error -> showError(result.message)
 *     is RequestResource.ErrorResponse -> logServerError(result.message)
 * }
 * ```
 *
 * @param T The type of data expected in case of a successful operation.
 * @property data The data returned if the request was successful (nullable).
 * @property message An optional error message describing the failure (nullable).
 */
sealed class RequestResource<T>(val data: T? = null, val message: String? = null) {

  /**
   * Represents a successful result, carrying the retrieved [data].
   *
   * @param data The successfully retrieved data.
   */
  class Success<T>(data: T?) : RequestResource<T>(data)

  /**
   * Represents a backend error (e.g., server response with error).
   *
   * ---
   * ### 📝 Note:
   * Even though the operation failed, we might want to return a known error response,
   * useful for tracing, logging, or displaying structured messages.
   *
   * @param message The error message returned from the server or backend.
   */
  class ErrorResponse<T>(message: String?) : RequestResource<T>(null, message)

  /**
   * Represents a generic or unexpected failure, such as an exception or a network error.
   *
   * @param message A human-readable or debug-friendly error message.
   */
  class Error<T>(message: String) : RequestResource<T>(null, message)
}
