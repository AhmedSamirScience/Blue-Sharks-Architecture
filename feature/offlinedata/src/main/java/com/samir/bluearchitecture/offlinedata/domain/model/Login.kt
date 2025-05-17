package com.samir.bluearchitecture.offlinedata.domain.model

data class Login(
  val chemistID: String,
  val errorMessage: String,
) {
  fun isDataValid(): Boolean {
    return chemistID.isNotBlank() || errorMessage.isNotBlank()
  }
}
