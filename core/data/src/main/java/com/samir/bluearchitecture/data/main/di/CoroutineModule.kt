package com.samir.bluearchitecture.data.main.di

import com.samir.bluearchitecture.data.main.constants.DISPATCHER_DEFAULT_TAG
import com.samir.bluearchitecture.data.main.constants.DISPATCHER_IO_TAG
import com.samir.bluearchitecture.data.main.constants.DISPATCHER_MAIN_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

/**
 * `CoroutineModule`
 *
 * A Dagger Hilt module that provides coroutine dispatchers as injectable dependencies.
 * Dispatchers are qualified with `@Named` to differentiate between Main, IO, and Default threads.
 *
 * This promotes consistency across the app and enables better testability by making
 * it easy to override dispatchers in unit tests.
 */
@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

  /**
   * Provides the Main dispatcher (`Dispatchers.Main`) which is optimized for UI operations.
   *
   * ### Use Cases:
   * - Interacting with the UI from ViewModels or Presenters.
   * - Posting results back to the UI after background work.
   *
   * ### Example:
   * ```kotlin
   * withContext(mainDispatcher) {
   *     textView.text = "Updated!"
   * }
   * ```
   *
   * @return [CoroutineDispatcher] bound to the main thread.
   */
  @Provides
  @Singleton
  @Named(DISPATCHER_MAIN_TAG)
  fun provideMainCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main

  /**
   * Provides the Default dispatcher (`Dispatchers.Default`) which is optimized for
   * CPU-intensive tasks such as sorting lists, performing calculations, or parsing large data.
   *
   * ### Use Cases:
   * - Data processing
   * - Complex business logic
   *
   * ### Example:
   * ```kotlin
   * withContext(defaultDispatcher) {
   *     val sortedList = largeList.sorted()
   * }
   * ```
   *
   * @return [CoroutineDispatcher] for heavy CPU-bound work.
   */
  @Provides
  @Singleton
  @Named(DISPATCHER_DEFAULT_TAG)
  fun provideDispatcherCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default

  /**
   * Provides the IO dispatcher (`Dispatchers.IO`) which is designed for IO-bound tasks
   * like network calls, reading/writing files, or database access.
   *
   * ### Use Cases:
   * - Making API requests with Retrofit
   * - Reading from or writing to disk
   * - Accessing a local database (e.g., Room)
   *
   * ### Example:
   * ```kotlin
   * withContext(ioDispatcher) {
   *     val users = apiService.getUsers()
   * }
   * ```
   *
   * @return [CoroutineDispatcher] optimized for IO operations.
   */
  @Provides
  @Singleton
  @Named(DISPATCHER_IO_TAG)
  fun provideIoCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
