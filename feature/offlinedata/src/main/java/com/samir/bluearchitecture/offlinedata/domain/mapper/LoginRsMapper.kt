package com.samir.bluearchitecture.offlinedata.domain.mapper

import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginDtoRs
import com.samir.bluearchitecture.offlinedata.domain.model.Login

/**
 * Mapper class responsible for converting a remote [LoginDtoRs] (data transfer object)
 * into a domain-level [Login] model.
 *
 * ---
 * ### ✅ Purpose:
 * This abstraction ensures that the domain layer remains decoupled from the network layer
 * by transforming network response data into domain-friendly structures.
 *
 * ---
 * ### 🔄 Use Case Example:
 * ```kotlin
 * val mapper = LoginRsMapper()
 * val domainModel = mapper.buildFrom(responseDto)
 * ```
 *
 * @see LoginDtoRs
 * @see Login
 */
class LoginRsMapper {

  /**
   * Maps a nullable [LoginDtoRs] response into a non-null domain-level [Login] object.
   *
   * - Null-safe: Returns an empty-string fallback for null fields.
   * - Simple one-to-one mapping, used as a bridge between remote and domain layers.
   *
   * @param response The API response DTO returned from the remote layer.
   * @return A [Login] domain object with mapped and defaulted values.
   */
  fun buildFrom(response: LoginDtoRs?): Login {
    return Login(
      chemistID = response?.chemistID ?: "",
      errorMessage = response?.errorMessage ?: "",
    )
  }
}
