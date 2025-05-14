package com.samir.bluearchitecture.remotedata.main.data.remote.repository

import com.samir.bluearchitecture.data.main.remote.source.NetworkDataSource
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.remotedata.main.data.remote.apiService.AuthApi
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.remotedata.main.domain.mapper.LoginRsMapper
import com.samir.bluearchitecture.remotedata.main.domain.model.Login
import com.samir.bluearchitecture.remotedata.main.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val networkDataSource: NetworkDataSource<AuthApi>,
) : AuthRepository {

  //region First Screen (Example)
  override suspend fun login(request: LoginRq): OutCome<Login> {
    return networkDataSource.performRequest(
      request = {
        login(request)
      },
      onSuccess = { response, _ ->
        OutCome.success(LoginRsMapper().buildFrom(response))
      },
    )
  }
  //endregion
}
