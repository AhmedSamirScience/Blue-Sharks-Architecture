package com.samir.bluearchitecture.domain.main.usecase

import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome

/**
 * `UseCase<R>` defines a contract for handling different outcomes (`Success`, `Empty`, or `Error`)
 * of a domain-layer operation in a clean architecture setup.
 *
 * It is typically implemented by classes that extend [AsyncUseCase], which provides concrete logic
 * for executing business use cases and dispatching their results to appropriate handlers.
 *
 * ---
 * ### 🔍 Type Parameter:
 * - `R` → The type of the result expected when the use case completes successfully.
 *
 * ---
 * ### ✅ Responsibilities:
 * - Provide three distinct entry points for each outcome returned by [OutCome]:
 *   - `onSuccess` → when the operation succeeds and returns data
 *   - `onEmpty` → when the operation completes with no result (e.g., empty list)
 *   - `onError` → when the operation fails with an error
 *
 * ---
 * ### 🧠 Why This Interface Exists:
 * - Promotes inversion of control between business logic (`run()`) and response handling.
 * - Enables any use case to dispatch results uniformly without being coupled to the caller's implementation.
 * - Forms the foundation for structured response handling in your coroutine-based domain use cases.
 *
 * ---
 * ### ✅ Usage Context:
 * This interface is **not meant to be used directly**, but is instead implemented internally
 * by [AsyncUseCase] to link business logic (`run(input)`) with outcome response handling (`execute()`).
 *
 * ---
 * ### Example:
 * Here's how [AsyncUseCase] utilizes this interface under the hood:
 *
 * ```kotlin
 * override suspend fun onSuccess(success: OutCome.Success<User>) {
 *     // Dispatch result to UI layer
 *     updateUi(success.data)
 * }
 *
 * override suspend fun onError(errorMessageMapper: ErrorMessageMapper) {
 *     // Show error dialog or toast
 *     showError(errorMessageMapper.messageMapper)
 * }
 * ```
 */
interface UseCase<R> {

  /**
   * Called when the use case returns a [OutCome.Success] result.
   *
   * @param success The success wrapper containing the result data.
   */
  suspend fun onSuccess(success: OutCome.Success<R>)

  /**
   * Called when the use case returns a [OutCome.Empty] result.
   *
   * This typically means there was no data to return (e.g., an empty list, or a "no content" response).
   */
  suspend fun onEmpty()

  /**
   * Called when the use case returns a [OutCome.Error] result.
   *
   * @param errorMessageMapper A domain-level error model describing the error state.
   */
  suspend fun onError(errorMessageMapper: ErrorMessageMapper)
}
