package com.samir.bluearchitecture.data.main.remote.di

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A utility class that provides access to string resources using a given [Context].
 *
 * ---
 * ### ✅ Purpose:
 * This class abstracts away the direct dependency on the Android [Context] when accessing string resources,
 * making it easier to use in ViewModels, UseCases, or other layers where direct context access is discouraged
 * or unavailable.
 *
 * ---
 * ### 🧩 Scope:
 * Annotated with [Singleton], this class is instantiated once and shared across the application,
 * ensuring memory efficiency and consistent behavior.
 *
 * ---
 * ### 📦 Example Usage:
 * ```kotlin
 * @Inject lateinit var stringProvider: StringResourceProvider
 * val message = stringProvider.getString(R.string.error_message)
 * ```
 *
 * @property context Application context used to retrieve string resources.
 */
@Singleton
class StringResourceProvider @Inject constructor(private val context: Context) {

  /**
   * Retrieves a string from the app's resources using the given resource ID.
   *
   * @param resId The string resource ID annotated with [StringRes].
   * @return The corresponding string value.
   */
  fun getString(@StringRes resId: Int): String = context.getString(resId)
}
