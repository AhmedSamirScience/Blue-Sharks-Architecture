package com.samir.bluearchitecture.remotedata.main.domain.model

data class Login(
  val chemistID: String,
  val errorMessage: String,
) {
  fun isDataValid(): Boolean {
    return chemistID.isNotBlank() || errorMessage.isNotBlank()
  }
}
