package com.samir.bluearchitecture.data.main.remote.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

// region === Header Keys ===

/**
 * HTTP header key used to indicate the expected response content type.
 * Expected value: [JSON_VALUE]
 */
const val ACCEPT_KEY = "Accept"

/**
 * HTTP header key used to declare the request body content type.
 * Expected value: [JSON_VALUE]
 */
const val CONTENT_TYPE_KEY = "Content-Type"

/**
 * HTTP header key used to indicate the user's preferred language.
 * Values: [ENGLISH_LANGUAGE_VALUE], [ARABIC_LANGUAGE_VALUE]
 */
const val ACCEPT_LANGUAGE_KEY = "Accept-Language"
// endregion

// region === Header Values ===

/**
 * Standard MIME type for JSON.
 * Used as the value for both [ACCEPT_KEY] and [CONTENT_TYPE_KEY].
 */
const val JSON_VALUE = "application/json"

/**
 * Arabic language and region identifier used in `Accept-Language`.
 */
const val ARABIC_LANGUAGE_VALUE = "ar-EG"

/**
 * English language and region identifier used in `Accept-Language`.
 */
const val ENGLISH_LANGUAGE_VALUE = "en-US"
// endregion

/**
 * An OkHttp [Interceptor] that appends standard HTTP headers to every outgoing request.
 *
 * This includes:
 * - `Accept` and `Content-Type` set to `application/json`.
 * - `Accept-Language` set based on the current app locale.
 * - `User-Agent` with app version, platform info, and device model.
 *
 * This interceptor ensures that requests comply with backend requirements for
 * content negotiation and localization.
 *
 * ---
 * ✅ HEADERS ADDED:
 * - `Accept`: application/json
 * - `Content-Type`: application/json
 * - `Accept-Language`: en-US or ar-EG (based on locale)
 * - `User-Agent`: AppName/1.0.0 (Android; 33; Pixel 6)
 *
 * ---
 * ✅ WHY IT'S USEFUL:
 * - Complies with backend header validation (Node.js middleware).
 * - Centralizes and standardizes all outbound header definitions.
 * - Supports localization by including the current language setting.
 *
 * ---
 * @param languageProvider Lambda that returns the current app [Locale].
 *                         Typically injected using Hilt and evaluated at call-time.
 * @param context Application context used to dynamically fetch app name, version, and device info.
 */
class HeaderInterceptor(
  private val languageProvider: () -> Locale,
  private val context: Context,

) : Interceptor {

  /**
   * Intercepts outgoing HTTP requests and attaches the necessary headers.
   *
   * @param chain The OkHttp request chain.
   * @return [Response] The HTTP response after the request proceeds with updated headers.
   */
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val builder = request.newBuilder()

    // Dynamically select the Accept-Language value
    val language = if (languageProvider() == Locale.ENGLISH) {
      ENGLISH_LANGUAGE_VALUE
    } else {
      ARABIC_LANGUAGE_VALUE
    }

    // ✅ Construct a valid User-Agent string
    val appName = context.applicationInfo.loadLabel(context.packageManager).toString()
    val version = context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "1.0.0"
    val os = "Android"
    val osVersion = android.os.Build.VERSION.SDK_INT.toString()
    val model = android.os.Build.MODEL
      .replace(Regex("[^A-Za-z0-9]"), "")
      .ifBlank { "UnknownDevice" } // If the result is blank, fallback

    val userAgent = "$appName/$version ($os; $osVersion; $model)"

    // Add all headers to the request
    builder
      .header("User-Agent", userAgent)
      .header(ACCEPT_KEY, JSON_VALUE)
      .header(CONTENT_TYPE_KEY, JSON_VALUE)
      .header(ACCEPT_LANGUAGE_KEY, language)

    return chain.proceed(builder.build())
  }
}
