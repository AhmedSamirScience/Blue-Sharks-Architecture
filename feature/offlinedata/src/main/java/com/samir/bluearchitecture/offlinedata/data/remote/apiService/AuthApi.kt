package com.samir.bluearchitecture.offlinedata.data.remote.apiService

import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginDtoRs
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface AuthApi {

  //region Login Screen (Example)
  @POST("posts")
  suspend fun login(@Body request: LoginRq): Response<LoginDtoRs>
  //endregion
}
