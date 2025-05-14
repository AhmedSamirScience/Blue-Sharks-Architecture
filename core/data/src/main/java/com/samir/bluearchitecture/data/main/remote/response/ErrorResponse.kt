package com.samir.bluearchitecture.data.main.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Represents a structured error response returned by the backend API.
 *
 * This class is used for deserializing error payloads from failed HTTP responses (e.g., 400, 422, 500).
 * It helps in building domain-level error models or user-facing error messages.
 *
 * ---
 * ✅ PURPOSE:
 * - Acts as a DTO for server-side validation or exception messages.
 * - Enables parsing and mapping of structured error information.
 * - Often used in conjunction with Retrofit + Gson.
 *
 * ---
 * ✅ COMMON USAGE:
 * Used inside error parsing logic (e.g., `getErrorResponse(...)`) or in a `NetworkDataSource`.
 *
 * @see com.samir.bluearchitecture.data.main.error.getErrorResponse
 */
data class ErrorResponse(

  /**
   * The backend-defined error code (as a string).
   *
   * This may correspond to business logic error codes or validation tags.
   * Example: `"USER_NOT_FOUND"`, `"INVALID_PASSWORD"`, `"403"`, etc.
   */
  @SerializedName("errorCode")
  val errorCode: String?,

  /**
   * The human-readable message returned from the server.
   *
   * This is often localized or designed to be shown directly to users.
   * Example: `"Invalid email or password."`
   */
  @SerializedName("errorMessage")
  val errorMessage: String?,

  /**
   * A list of field-level validation errors, if any.
   *
   * Typically returned when the API performs form validation (e.g., `email`, `password` fields).
   * Used to show specific messages next to form inputs.
   * Example: `["email is required", "password must be 8+ chars"]`
   */
  @SerializedName("errorFieldList")
  val errorFieldList: List<String>?,
)
