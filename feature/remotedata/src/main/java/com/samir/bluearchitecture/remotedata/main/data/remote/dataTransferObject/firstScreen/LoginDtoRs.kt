package com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginDtoRs(
  @SerializedName("title")
  val chemistID: String,

  @SerializedName("descriapation")
  val errorMessage: String,

  @SerializedName("image")
  val isSuccess: Boolean,
)
