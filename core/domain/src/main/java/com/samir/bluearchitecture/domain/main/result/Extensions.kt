package com.samir.bluearchitecture.domain.main.result

import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

// /**
// * Executes the given [onSuccess] block **only** if the current [OutCome] is [OutCome.Success]
// * and the coroutine context is still active.
// *
// * This is useful for chaining UI or logic responses directly after success without manually checking the outcome.
// *
// * @param onSuccess A suspend function to invoke with the successful data.
// * @return The original [OutCome] to support chaining.
// */
// suspend fun <T> OutCome<T>.doOnSuccess(onSuccess: suspend (T) -> Unit): OutCome<T> {
//  if (this is OutCome.Success<T> && coroutineContext.isActive) {
//    onSuccess(this.data)
//  }
//  return this
// }

// /**
// * Executes the given [onEmpty] block **only** if the current [OutCome] is [OutCome.Empty]
// * and the coroutine context is active.
// *
// * Ideal for triggering empty state UI or fallback behavior.
// *
// * @param onEmpty A suspend function to invoke when the outcome is empty.
// * @return The original [OutCome] to allow fluent chaining.
// */
// suspend fun <T> OutCome<T>.doOnEmpty(onEmpty: suspend () -> Unit): OutCome<T> {
//  if (this is OutCome.Empty && coroutineContext.isActive) {
//    onEmpty()
//  }
//  return this
// }

// /**
// * Executes the given [onError] block if the current [OutCome] is not successful
// * and the coroutine context is still active.
// *
// * Typically used to trigger error dialogs, logs, or fallback logic.
// *
// * @param onError A suspend lambda to run when the outcome is [OutCome.Error].
// * @return The original [OutCome] to support method chaining.
// */
// suspend fun <T> OutCome<T>.doOnError(onError: () -> Unit): OutCome<T> {
//  if (!this.isSuccess() && coroutineContext.isActive) {
//    onError()
//  }
//  return this
// }

// /**
// * Maps the result of a successful [OutCome] into another type using the given [mapper] function.
// *
// * - If the outcome is `Success`, it transforms the data.
// * - If `Error`, it preserves the error.
// * - If `Empty`, it remains empty.
// *
// * @param mapper A suspend function to transform the success data.
// * @return A new [OutCome] with mapped result.
// */
// suspend fun <T, R> OutCome<T>.map(mapper: suspend (T) -> R): OutCome<R> {
//  return when (this) {
//    is OutCome.Success<T> -> OutCome.success(mapper(this.data))
//    is OutCome.Error<T> -> OutCome.error(this.errorMessage())
//    is OutCome.Empty<T> -> OutCome.empty()
//  }
// }

/**
 * Maps a successful [OutCome] to another [OutCome] using the given suspend [onSuccess] block.
 *
 * - Success → calls `onSuccess(data)` and returns its result.
 * - Error → returns the error as-is.
 * - Empty → returns empty as-is.
 *
 * Useful when the transformation itself may fail and return an `OutCome`.
 *
 * @param onSuccess A suspend function that returns a new `OutCome` based on the input data.
 * @return The resulting `OutCome<R>`.
 */
// suspend fun <T, R> OutCome<T>.mapOrElse(
//  onSuccess: suspend (T) -> OutCome<R>,
// ): OutCome<R> {
//  return when (this) {
//    is OutCome.Success -> onSuccess(this.data)
//    is OutCome.Error -> OutCome.error(this.errorMessage())
//    is OutCome.Empty -> OutCome.empty()
//  }
// }
suspend fun <T, R> OutCome<T>.mapOrElse(
  onSuccess: suspend (T) -> OutCome<R>,
): OutCome<R> {
  return when {
    this is OutCome.Success && coroutineContext.isActive -> onSuccess(this.data)
    this is OutCome.Success && !coroutineContext.isActive -> OutCome.empty() // or return this?
    this is OutCome.Error -> OutCome.error(this.errorMessage())
    this is OutCome.Empty -> OutCome.empty()
    else -> OutCome.empty()
  }
}

// /**
// * Combines this [OutCome] with another one lazily via the [lazy] function,
// * and merges their data into a new value using the [merger] function.
// *
// * This is especially useful when performing chained operations that should only proceed
// * if the first result is successful or empty, but not on error.
// *
// * - If both are `Success`, the data is merged.
// * - If one is `Empty`, the merger is called with `null` for the empty result.
// * - If either is `Error`, the error is propagated immediately.
// *
// * @param lazy A suspend function returning the second outcome.
// * @param merger A function that combines both results (nullable) into a new result.
// * @return A new [OutCome] containing the merged result, or error.
// */
// suspend fun <F, S, R> OutCome<F>.merge(
//  lazy: suspend () -> OutCome<S>,
//  merger: (F?, S?) -> R,
// ): OutCome<R> {
//  return when (this) {
//    is OutCome.Success<F> -> {
//      when (val second = lazy()) {
//        is OutCome.Success<S> -> OutCome.success(merger(this.data, second.data))
//        is OutCome.Empty<S> -> OutCome.success(merger(this.data, null))
//        is OutCome.Error<S> -> OutCome.error(second.errorMessage())
//      }
//    }
//
//    is OutCome.Error<F> -> OutCome.error(this.errorMessage())
//
//    is OutCome.Empty<F> -> {
//      when (val second = lazy()) {
//        is OutCome.Success<S> -> OutCome.success(merger(null, second.data))
//        is OutCome.Empty<S> -> OutCome.empty()
//        is OutCome.Error<S> -> OutCome.error(second.errorMessage())
//      }
//    }
//  }
// }
