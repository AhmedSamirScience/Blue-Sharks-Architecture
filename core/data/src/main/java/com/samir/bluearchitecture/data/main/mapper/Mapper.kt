package com.samir.bluearchitecture.data.main.mapper

import com.samir.bluearchitecture.data.main.response.ErrorResponse
import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper

/**
 * Maps a [ErrorResponse] from the data layer into a domain-layer [ErrorMessageMapper].
 *
 * This extension function is part of the clean architecture transformation layer,
 * converting data-specific models into domain-safe, UI-friendly representations.
 *
 * ---
 * ✅ PURPOSE:
 * - Decouples domain logic from Retrofit/Gson-specific data models.
 * - Ensures default fallback values (e.g., empty message or list) to avoid null-related crashes.
 * - Makes the error ready for use in `ViewModel` or UI error rendering.
 *
 * ---
 * ✅ MAPPING STRATEGY:
 * - `codeMapper`: Provided as input parameter, usually HTTP status or app-defined error code.
 * - `messageMapper`: Uses the non-null `errorMessage`, or empty string if null.
 * - `errorFieldListMapper`: Uses the non-null list or falls back to an empty list.
 *
 * ---
 * @receiver [ErrorResponse] The raw error model parsed from the API response.
 * @param code An integer representing the HTTP or custom error code.
 * @return A fully-initialized [ErrorMessageMapper] for use in domain/UI layers.
 *
 * @see com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
 */
fun ErrorResponse.toDomain(code: Int): ErrorMessageMapper {
  return ErrorMessageMapper(
    codeMapper = code,
    messageMapper = errorMessage.orEmpty(),
    errorFieldListMapper = errorFieldList ?: emptyList(),
  )
}
