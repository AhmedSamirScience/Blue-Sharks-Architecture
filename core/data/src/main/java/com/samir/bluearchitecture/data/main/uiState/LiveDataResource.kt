package com.samir.bluearchitecture.data.main.uiState

/**
 * Reference:
 * https://medium.com/@summitkumar/unlocking-the-power-of-sealed-classes-in-kotlin-design-patterns-and-better-code-organization-5627d73a4903
 */


/**
 * A sealed class representing various UI states commonly used with [Live Data] to communicate
 * data loading, success, or error states to the UI layer.
 *
 * ---
 * ### ✅ Purpose:
 * `LiveDataResource` simplifies the representation of asynchronous operations by wrapping the result
 * along with its status (e.g., loading, success, or error) in a strongly-typed and exhaustive way,
 * leveraging Kotlin's sealed class capabilities.
 *
 * ---
 * ### 🔄 Common Usage with LiveData:
 * ```kotlin
 * val _state = MutableLiveData<LiveDataResource<User>>()
 * _state.value = LiveDataResource.Loading()
 * repository.getUser().onSuccess {
 *     _state.value = LiveDataResource.Success(it)
 * }.onFailure {
 *     _state.value = LiveDataResource.Error(it.message ?: "Unknown Error")
 * }
 * ```
 *
 * ---
 * ### 📦 Subclasses:
 * - [Idle]: Initial state or unused state.
 * - [Loading]: Represents a loading state, typically during API calls or DB operations.
 * - [Success]: Operation completed successfully with optional data.
 * - [Error]: Generic error state with a message.
 * - [ErrorResponse]: Specialized error state for handling structured error responses (e.g., API errors).
 *
 * @param T The type of data expected in the success case.
 * @property data Optional data returned in case of success.
 * @property message Optional error message in case of error.
 *
 * @see androidx.lifecycle.LiveData
 * @see kotlin sealed
 */
sealed class LiveDataResource<T>(val data: T? = null, val message: String? = null) {

  /**
   * Represents an initial or idle state where no action has been taken yet.
   */
  class Idle<T> : LiveDataResource<T>()

  /**
   * Represents a successful operation with optional data.
   * @param data The result of the successful operation.
   */
  class Success<T>(data: T?) : LiveDataResource<T>(data)

  /**
   * Represents an error with a structured or backend response message.
   * @param message The error message from API or known source.
   */
  class ErrorResponse<T>(message: String) : LiveDataResource<T>(null, message)

  /**
   * Represents a generic error with a message.
   * @param message A user-friendly or technical error message.
   */
  class Error<T>(message: String) : LiveDataResource<T>(null, message)

  /**
   * Represents a loading state during an ongoing operation.
   */
  class Loading<T> : LiveDataResource<T>()
}
