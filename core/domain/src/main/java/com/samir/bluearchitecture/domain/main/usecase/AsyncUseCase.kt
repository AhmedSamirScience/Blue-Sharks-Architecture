package com.samir.bluearchitecture.domain.main.usecase

import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome
import kotlinx.coroutines.delay

/**
 * `AsyncUseCase<I, R>` is an abstract base class designed to simplify the execution
 * of asynchronous domain-layer use cases that return a result wrapped in [OutCome].
 *
 * It encapsulates input handling, execution logic, and unified response callbacks
 * for success, error, and empty states — allowing consumers (e.g., ViewModels)
 * to use it in a clean, structured, coroutine-friendly manner.
 *
 * ---
 * ### 🔍 Type Parameters:
 * - `I` → Input type passed to the use case (e.g., `LoginRequest`)
 * - `R` → Result type expected on success (e.g., `UserProfile`)
 *
 * ---
 * ### ✅ Responsibilities:
 * - Defines an abstract `run()` method where business logic is implemented.
 * - Provides an `execute()` method to trigger the use case from external callers.
 * - Manages and dispatches stateful callbacks: `onSuccess`, `onEmpty`, `onError`.
 * - Bridges the result to the [UseCase] interface via the `accept()` contract.
 *
 * ---
 * ### ✅ Lifecycle:
 * 1. `execute(input, success, empty, error)` is called from a coroutine context.
 * 2. The `run(input)` function performs the business operation and returns an [OutCome].
 * 3. That [OutCome] internally calls `.accept(this)` → dispatches the result back to:
 *     - `onSuccess(success: OutCome.Success<R>)`
 *     - `onEmpty()`
 *     - `onError(errorMessageMapper: ErrorMessageMapper)`
 * 4. The corresponding lambda callback (`success`, `empty`, or `error`) is invoked.
 *
 * ---
 * ### 🧠 Why Use This?
 * - Reduces boilerplate for coroutine-based use cases.
 * - Offers consistent result handling across the domain layer.
 * - Easily integrates with state flows, LiveData, or direct callbacks in ViewModels.
 *
 * ---
 * ### ✅ Usage Example:
 *
 * ```kotlin
 * class GetUserProfileUseCase @Inject constructor(
 *     private val repository: UserRepository
 * ) : AsyncUseCase<String, UserProfile>() {
 *
 *     override suspend fun run(input: String): OutCome<UserProfile> {
 *         return repository.getUserProfileById(input)
 *     }
 * }
 * ```
 *
 * ```kotlin
 * viewModelScope.launch {
 *     getUserProfileUseCase.execute(
 *         input = "userId-123",
 *         success = { user -> /* render UI */ },
 *         empty = { /* show empty UI */ },
 *         error = { error -> /* show error message */ }
 *     )
 * }
 * ```
 */
abstract class AsyncUseCase<I, R> : UseCase<R> {

  // Callback for successful result
  private lateinit var success: suspend (R) -> Unit

  // Callback for empty result
  private lateinit var empty: suspend () -> Unit

  // Callback for failure result
  private lateinit var error: suspend (ErrorMessageMapper) -> Unit

  // Callback for loading state
  private lateinit var loading: suspend () -> Unit

  // Callback for idle state
  private lateinit var idle: suspend () -> Unit

  /**
   * Executes the use case with the given input and lifecycle callbacks.
   *
   * @param input Input value passed to the business logic.
   * @param success Called if [OutCome.Success] is returned.
   * @param empty Called if [OutCome.Empty] is returned.
   * @param error Called if [OutCome.Error] is returned.
   */
  suspend fun execute(
    input: I,
    success: suspend (R) -> Unit = {},
    empty: suspend () -> Unit = {},
    error: suspend (ErrorMessageMapper) -> Unit = {},
    loading: suspend () -> Unit = {},
    idle: suspend () -> Unit = {},
  ) {
    this.success = success
    this.empty = empty
    this.error = error
    this.loading = loading
    this.idle = idle

    loading()
    run(input).accept(this)
  }

  /**
   * Abstract business logic to be implemented by subclasses.
   *
   * @param input The input argument passed from `execute()`.
   * @return An [OutCome] representing success, empty, or error.
   */
  abstract suspend fun run(input: I): OutCome<R>

  /**
   * Called when an [OutCome.Success] is returned.
   */
  override suspend fun onSuccess(success: OutCome.Success<R>) {
    success(success.data)
    delay(100)
    idle()
  }

  /**
   * Called when an [OutCome.Empty] is returned.
   */
  override suspend fun onEmpty() {
    empty()
    delay(100)
    idle()
  }

  /**
   * Called when an [OutCome.Error] is returned.
   */
  override suspend fun onError(errorMessageMapper: ErrorMessageMapper) {
    error(errorMessageMapper)
    delay(100)
    idle()
  }
}
