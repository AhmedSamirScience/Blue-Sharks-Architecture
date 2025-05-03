package com.samir.bluearchitecture.domain.main.model

/**
 * `ErrorMessageMapper` is a domain-layer model that encapsulates error information
 * in a clean and consistent format across use cases, repositories, and UI layers.
 *
 * It acts as an abstraction over backend error responses, making them easier
 * to consume in the presentation layer and more testable in the domain layer.
 *
 * ---
 * ### ✅ Responsibilities:
 * - Represents error metadata such as code, message, and field-specific violations.
 * - Supports structured error handling with meaningful fallback logic.
 * - Decouples domain error structure from raw data or retrofit responses.
 *
 * ---
 * ### 🧩 Typically Mapped From:
 * - A `data.main.response.ErrorResponse` object via extension:
 *   ```kotlin
 *   ErrorResponse.toDomain(code: Int): ErrorMessageMapper
 *   ```
 *
 * ---
 * ### 🧠 Usage Example:
 *
 * ```kotlin
 * when (val result = useCase.execute(...)) {
 *   is OutCome.Error -> {
 *     val error = result.errorMessage()
 *     showMessage(error.messageMapper)
 *     if (error.codeMapper == 401) { logoutUser() }
 *   }
 * }
 * ```
 *
 * ---
 * @property codeMapper The backend or app-defined error code (e.g., 401, -2).
 * @property messageMapper A human-readable error message for the UI or logs.
 * @property errorFieldListMapper A list of field-level validation messages (e.g., missing email, wrong format).
 */
data class ErrorMessageMapper(
  val codeMapper: Int,
  val messageMapper: String,
  val errorFieldListMapper: List<String>,
) {
  /**
   * Overrides the default `toString()` method.
   *
   * Returning an empty string prevents accidental logging of sensitive messages.
   * Customize this if you want structured debug output.
   */
  override fun toString(): String {
    return ""
  }
}
