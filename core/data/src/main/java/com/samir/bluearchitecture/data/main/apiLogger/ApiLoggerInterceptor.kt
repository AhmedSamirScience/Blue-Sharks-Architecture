package com.samir.bluearchitecture.data.main.apiLogger

import android.util.Log
import com.samir.bluearchitecture.data.main.constants.TAG_API_LOGGER
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
/**
 * `ApiLoggerInterceptor` is a custom [Interceptor] used to log HTTP request and response details
 * for debugging API calls made through OkHttp.
 *
 * ### Features:
 * - Logs HTTP method, URL, headers, and request body.
 * - Measures request duration in milliseconds.
 * - Logs HTTP response code, message, headers, and body.
 *
 * ### Usage:
 * This interceptor is typically added to the OkHttpClient for development and debugging purposes.
 *
 * ```kotlin
 * val client = OkHttpClient.Builder()
 *     .addInterceptor(ApiLoggerInterceptor())
 *     .build()
 * ```
 *
 * ⚠️ **Warning**:
 * - This interceptor logs entire request and response bodies which may contain sensitive data.
 *   Use only in **debug builds**.
 * - Large response bodies or streaming content may impact performance or memory usage.
 *
 * @constructor Creates a new instance of [ApiLoggerInterceptor].
 */
class ApiLoggerInterceptor : Interceptor {
  /**
   * Intercepts the HTTP chain to log detailed request and response information.
   *
   * @param chain The OkHttp [Interceptor.Chain] to proceed with the request.
   * @return The [Response] from the server.
   */
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val startTime = System.nanoTime()

    // Log request details
    val requestLog = StringBuilder().apply {
      appendLine("⬆️ API REQUEST")
      appendLine("➡️ ${request.method} ${request.url}")
      appendLine("Headers:")
      request.headers.forEach { appendLine("${it.first}: ${it.second}") }

      val body = request.body
      if (body != null) {
        val buffer = Buffer()
        body.writeTo(buffer)
        val charset = body.contentType()?.charset(Charsets.UTF_8) ?: Charsets.UTF_8
        appendLine("Body:")
        appendLine(buffer.readString(charset))
      }
    }
    Log.d(TAG_API_LOGGER, requestLog.toString())

    // Execute request
    val response = chain.proceed(request)
    val endTime = System.nanoTime()
    val durationMs = (endTime - startTime) / 1_000_000

    // Log response details
    val responseBody = response.body
    val responseBodyString = responseBody.let {
      val source = it.source()
      source.request(Long.MAX_VALUE) // Read the full body into memory
      val buffer = source.buffer
      val charset = it.contentType()?.charset(Charsets.UTF_8) ?: Charsets.UTF_8
      buffer.clone().readString(charset)
    }

    val responseLog = StringBuilder().apply {
      appendLine("⬇️ API RESPONSE")
      appendLine("⬅️ ${response.code} ${response.message} (${durationMs}ms) ${response.request.url}")
      appendLine("Headers:")
      response.headers.forEach { appendLine("${it.first}: ${it.second}") }
      appendLine("Body:")
      appendLine(responseBodyString)
    }

    Log.d(TAG_API_LOGGER, responseLog.toString())

    return response
  }
}
