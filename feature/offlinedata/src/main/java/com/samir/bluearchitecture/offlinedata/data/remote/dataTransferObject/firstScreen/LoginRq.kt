package com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class LoginRq(
  @SerializedName("title")
  var username: String?,

  @SerializedName("descriapation")
  var password: String,

  @SerializedName("image")
  var deviceToken: String,

)
