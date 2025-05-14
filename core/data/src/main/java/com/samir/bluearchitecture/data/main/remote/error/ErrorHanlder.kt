package com.samir.bluearchitecture.data.main.remote.error

import com.google.gson.Gson
import com.samir.bluearchitecture.data.main.remote.response.ErrorResponse

/**
 * Returns a default [ErrorResponse] instance used as a fallback when error parsing fails.
 *
 * ### Purpose:
 * - Prevents the app from crashing due to unexpected or malformed error responses.
 * - Can be used as a safe fallback when no error body is available from the server.
 *
 * @return A default [ErrorResponse] with empty message, code, and error list.
 */
fun getDefaultErrorResponse() = ErrorResponse("-", "-", emptyList())

/**
 * Attempts to parse the raw error body string into a structured [ErrorResponse] using [Gson].
 *
 * ### Behavior:
 * - If parsing is successful, the deserialized [ErrorResponse] is returned.
 * - If parsing fails (e.g., due to malformed JSON), a fallback [getDefaultErrorResponse()] is returned instead.
 *
 * ### Usage:
 * Typically used in networking layers to interpret HTTP 4xx/5xx error payloads returned by APIs.
 *
 * @param gson The Gson instance used to perform the deserialization.
 * @param errorBodyString The raw JSON string representing the error body from the server.
 * @return Parsed [ErrorResponse] or default fallback if parsing fails.
 */
fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
  try {
    gson.fromJson(errorBodyString, ErrorResponse::class.java)
  } catch (e: Exception) {
    getDefaultErrorResponse()
  }
