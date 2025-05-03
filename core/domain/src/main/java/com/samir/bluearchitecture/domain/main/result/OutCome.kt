package com.samir.bluearchitecture.domain.main.result

import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.usecase.UseCase

/**
 * `OutCome<T>` is a sealed class that models the outcome of a business operation.
 *
 * It provides a clean, unified representation of possible results:
 * - `Success<T>`: operation completed with data.
 * - `Error<T>`: operation failed due to a domain/technical issue.
 * - `Empty<T>`: operation succeeded but returned no data.
 *
 * This structure is central to propagating results across the domain and presentation layers
 * in a maintainable, testable, and coroutine-friendly way.
 *
 * ---
 * ### 🔍 Type Parameter:
 * - `T`: The expected data type when the operation succeeds.
 *
 * ---
 * ### ✅ Primary Responsibilities:
 * - Standardize result handling across all domain use cases.
 * - Eliminate ambiguity in return values (`null`, exceptions, flags).
 * - Delegate result handling to [UseCase] interfaces using the `accept()` pattern.
 * - Support transformation and chaining using extension functions like `.map()` and `.doOnSuccess()`.
 *
 * ---
 * ### ✅ Integration:
 * This class is designed to work seamlessly with:
 * - [Async UseCase]: abstract base class for use cases.
 * - [UseCase]: the handler interface for reacting to result types.
 *
 * The `accept()` method calls the corresponding method on a [UseCase] implementation:
 * - `onSuccess()`, `onError()`, or `onEmpty()`.
 *
 * ---
 * ### 🧠 Usage Example:
 *
 * ```kotlin
 * suspend fun login(): OutCome<User> {
 *     return if (api.loginSuccess) {
 *         OutCome.success(user)
 *     } else {
 *         OutCome.error(ErrorMessageMapper(...))
 *     }
 * }
 *
 * // In ViewModel
 * loginUseCase.execute(..., success = { showUser(it) }, error = { showError(it.messageMapper) })
 * ```
 */
sealed class OutCome<T> {

  /**
   * Returns an [ErrorMessageMapper] if this is an [Error] outcome.
   *
   * Default is `null`. Subclasses may override this if they carry an error.
   */
  open fun errorMessage(): ErrorMessageMapper? = null

  /**
   * Dispatches the current outcome to the corresponding method on the given [UseCase].
   *
   * This pattern allows consistent handling of all outcomes without manual `when` expressions.
   *
   * @param useCase The handler that defines how to respond to different outcomes.
   */
  abstract suspend fun accept(useCase: UseCase<T>)

  //region Success

  /**
   * Represents a successful outcome, carrying a non-null result of type [T].
   */
  class Success<T>(val data: T) : OutCome<T>() {
    override suspend fun accept(useCase: UseCase<T>) {
      useCase.onSuccess(this)
    }
  }
  //endregion

  //region Error
  /**
   * Represents a failed outcome, carrying a domain-level error description.
   *
   * @param errorMessageMapper The mapped error information.
   */
  class Error<T>(private val errorMessageMapper: ErrorMessageMapper) : OutCome<T>() {
    override fun errorMessage(): ErrorMessageMapper = errorMessageMapper

    override suspend fun accept(useCase: UseCase<T>) {
      useCase.onError(errorMessageMapper)
    }
  }
  //endregion

  //region Empty
  /**
   * Represents a successful outcome with no data (e.g., HTTP 204 No Content).
   */
  class Empty<T> : OutCome<T>() {
    override suspend fun accept(useCase: UseCase<T>) {
      useCase.onEmpty()
    }
  }
  //endregion

  /**
   * Factory methods for convenient creation of [OutCome] instances.
   */
  companion object {

    /**
     * Creates a [Success] outcome.
     * @param data The result data to wrap.
     */
    fun <T> success(data: T) = Success(data)

    /**
     * Creates an [Error] outcome.
     * @param errorMessageMapper The mapped error to wrap.
     */
    fun <T> error(errorMessageMapper: ErrorMessageMapper) = Error<T>(errorMessageMapper)

    /**
     * Creates an [Empty] outcome.
     */
    fun <T> empty() = Empty<T>()
  }
}
