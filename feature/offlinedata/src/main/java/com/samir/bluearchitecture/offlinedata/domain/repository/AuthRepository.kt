package com.samir.bluearchitecture.offlinedata.domain.repository

import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.model.Login
import com.samir.bluearchitecture.offlinedata.domain.model.ServerTime

interface AuthRepository {

  //region First Screen
  suspend fun login(request: LoginRq): OutCome<Login>

  suspend fun cacheLogin(login: LoginEntity): OutCome<Unit>

  suspend fun getLastLogin(): OutCome<LoginEntity>

  suspend fun getAllLogins(): OutCome<List<LoginEntity>>
  //endregion

  //region Preference Data Store
  // region 🔐 String
  suspend fun saveUserId(id: String): OutCome<Unit>
  suspend fun getUserId(): OutCome<String>
  // endregion

  // region 🔢 Primitive Numbers
  suspend fun saveLatitude(latitude: Double): OutCome<Unit>
  suspend fun getLatitude(): OutCome<Double>

  suspend fun saveLoginCount(count: Int): OutCome<Unit>
  suspend fun getLoginCount(): OutCome<Int>
  // endregion

  // region 🧩 Objects
  suspend fun saveServerTime(time: ServerTime): OutCome<Unit>
  suspend fun getServerTime(): OutCome<ServerTime?>
  // endregion

  // region 🧼 Clear
  suspend fun clearAll(): OutCome<Unit>
  // endregion
  //endregion
}
