package com.samir.bluearchitecture.remotedata.main.domain.repository

import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.remotedata.main.domain.model.Login

interface AuthRepository {

  //region First Screen
  suspend fun login(request: LoginRq): OutCome<Login>
  //endregion
}
