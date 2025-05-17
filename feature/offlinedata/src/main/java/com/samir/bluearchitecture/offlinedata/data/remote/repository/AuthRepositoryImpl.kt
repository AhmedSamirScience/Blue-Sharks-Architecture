package com.samir.bluearchitecture.offlinedata.data.remote.repository

import com.samir.bluearchitecture.data.main.remote.source.NetworkDataSource
import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.offlinedata.data.dataSource.dao.LoginDao
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.data.remote.apiService.AuthApi
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.mapper.LoginRsMapper
import com.samir.bluearchitecture.offlinedata.domain.model.Login
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val networkDataSource: NetworkDataSource<AuthApi>,
  private val loginDao: LoginDao,

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

  override suspend fun cacheLogin(login: LoginEntity): OutCome<Unit> {
    return try {
      loginDao.insert(LoginEntity(login.userId, login.userName))
      OutCome.success(Unit)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(
          codeMapper = -1,
          messageMapper = e.localizedMessage ?: "Failed to save login",
          errorFieldListMapper = emptyList(),
        ),
      )
    }
  }

  override suspend fun getLastLogin(): OutCome<LoginEntity> {
    return loginDao.getLastLogin()?.let {
      OutCome.success(LoginEntity(it.userId, it.userName))
    } ?: OutCome.empty()
  }

  override suspend fun getAllLogins(): OutCome<List<LoginEntity>> {
    return try {
      val logins = loginDao.getAllLogins()
      OutCome.success(logins)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(
          codeMapper = -1,
          messageMapper = e.localizedMessage ?: "Failed to load logins",
          errorFieldListMapper = emptyList(),
        ),
      )
    }
  }
  //endregion
}
