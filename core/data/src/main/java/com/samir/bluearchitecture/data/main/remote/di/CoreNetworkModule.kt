package com.samir.bluearchitecture.data.main.remote.di

import android.content.Context
import com.samir.bluearchitecture.data.main.remote.error.ErrorMessageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ✅ Core Hilt module for networking-related singleton dependencies.
 *
 * This module is installed into the `SingletonComponent`, making all
 * its provided dependencies application-scoped.
 *
 * ---
 *
 * ### 💡 Purpose:
 * - Centralizes global network-related utilities.
 * - Currently provides a singleton instance of `ErrorMessageProvider`
 *   used to translate error responses into localized user-friendly messages.
 *
 * ---
 *
 * ### 💡 Usage Example:
 * Inject `ErrorMessageProvider` wherever needed:
 * ```kotlin
 * @Inject lateinit var errorMessageProvider: ErrorMessageProvider
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

  /**
   * Provides a singleton instance of [ErrorMessageProvider], responsible
   * for translating technical error codes or exceptions into user-readable
   * error messages using localized resources.
   *
   * @param context Application context for accessing string resources.
   * @return A shared instance of [ErrorMessageProvider].
   */
  @Provides
  @Singleton
  fun provideErrorMessageProvider(@ApplicationContext context: Context): ErrorMessageProvider {
    return ErrorMessageProvider(context)
  }
}
