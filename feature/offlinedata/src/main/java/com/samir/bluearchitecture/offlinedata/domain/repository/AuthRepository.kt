package com.samir.bluearchitecture.offlinedata.domain.repository

import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.model.Login

interface AuthRepository {

  //region First Screen
  suspend fun login(request: LoginRq): OutCome<Login>

  suspend fun cacheLogin(login: LoginEntity): OutCome<Unit>

  suspend fun getLastLogin(): OutCome<LoginEntity>

  suspend fun getAllLogins(): OutCome<List<LoginEntity>>

  //endregion
}
