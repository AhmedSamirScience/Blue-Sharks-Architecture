package com.samir.bluearchitecture.data.main.di

import android.content.Context
import com.samir.bluearchitecture.data.main.constants.LANGUAGE_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocaleModule {
  /**
   * Provides a singleton lambda function `() -> Locale` that dynamically returns the current
   * [Locale] used by the application, based on the system configuration.
   *
   * This function is useful in multi-language applications to consistently access the
   * user's current locale throughout the app. The returned lambda can be injected and
   * called from anywhere without needing to pass or inject a `Context` again.
   *
   * ---
   * ✅ PURPOSE:
   * - To determine the app’s active locale (`ar`, `en`, etc.) using the device's configuration.
   * - To expose the locale in a deferred/lazy way (as a function) so that it's evaluated
   *   each time it's needed — useful for runtime language changes.
   *
   * ---
   * ✅ WHY A FUNCTION `() -> Locale` INSTEAD OF A DIRECT `Locale`:
   * - Returning a lambda ensures you always get the latest locale at **call-time**.
   * - This supports dynamic language switching if your app allows users to change languages
   *   at runtime without needing to restart the app.
   * - It decouples classes that need a locale (like network interceptors, repositories, or UI components)
   *   from requiring access to `Context`.
   *
   * ---
   * ✅ CONTEXT INJECTION:
   * - The function receives `@ApplicationContext context: Context`, injected by Hilt.
   * - This context is tied to the application lifecycle and is safe to use in long-living
   *   dependencies like singletons.
   * - You **must not** create `Context` manually — Hilt manages it for you.
   *
   * ---
   * ✅ LOCALE DETECTION LOGIC:
   * - Uses `context.resources.configuration.locales[0]` to get the primary locale.
   * - This approach is compatible with Android N (API 24+) and up.
   * - If the current locale language is Arabic (`"ar"`), it returns a region-specific Arabic locale:
   *   `Locale("ar", "EG")`.
   * - For all other languages, it defaults to `Locale.ENGLISH`.
   *
   * ---
   * ✅ EXAMPLES OF USAGE:
   * - Adding `Accept-Language` headers to API calls:
   *   `localeProvider().language` → "en" or "ar"
   *
   * - Configuring localized number/date formatting
   * - Determining layout direction (LTR/RTL) in UI components
   *
   * ---
   * 🔄 FUTURE EXTENSION:
   * If your app supports a language override (e.g., stored in SharedPreferences or DataStore),
   * consider updating this function to:
   * - Check a saved language code first (e.g., "ar", "en")
   * - Fallback to system locale only if no preference is set
   *
   * ---
   * @param context Application context injected by Hilt using `@ApplicationContext`.
   * @return A singleton lambda function that, when invoked, returns the current [Locale].
   */
  @Provides
  @Singleton
  @Named(LANGUAGE_TAG)
  fun provideLanguage(@ApplicationContext context: Context): () -> Locale {
    return {
      val locale = context.resources.configuration.locales[0]
      when (locale.language) {
        "ar" -> Locale("ar", "EG")
        else -> Locale.ENGLISH
      }
    }
  }
}
