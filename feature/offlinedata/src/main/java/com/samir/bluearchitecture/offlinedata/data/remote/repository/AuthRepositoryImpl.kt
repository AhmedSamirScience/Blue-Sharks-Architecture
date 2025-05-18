package com.samir.bluearchitecture.offlinedata.data.remote.repository

import com.samir.bluearchitecture.data.main.dataSource.EncryptedPreferencesDataStoreManager
import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.CryptoHelper
import com.samir.bluearchitecture.data.main.remote.source.NetworkDataSource
import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.offlinedata.data.dataSource.dao.LoginDao
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.data.remote.apiService.AuthApi
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.mapper.LoginRsMapper
import com.samir.bluearchitecture.offlinedata.domain.model.Login
import com.samir.bluearchitecture.offlinedata.domain.model.ServerTime
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val networkDataSource: NetworkDataSource<AuthApi>,
  private val loginDao: LoginDao,
  private val cryptoHelper: CryptoHelper,
  private val prefManager: EncryptedPreferencesDataStoreManager,
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
      val encryptedName = cryptoHelper.encrypt(login.userName)
      val entity = LoginEntity(userId = login.userId, userName = encryptedName)
      loginDao.insert(entity)
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
      val result = loginDao.getAllLogins().map {
        it.copy(userName = cryptoHelper.decrypt(it.userName))
      }
      OutCome.success(result)
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

  //region Preference Data Store
  override suspend fun saveUserId(id: String): OutCome<Unit> {
    return try {
      prefManager.saveEncryptedString("user_id", id)
      OutCome.success(Unit)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(
          codeMapper = -1,
          messageMapper = e.localizedMessage ?: "Failed to save user ID",
          errorFieldListMapper = emptyList(),
        ),
      )
    }
  }

  override suspend fun getUserId(): OutCome<String> {
    return try {
      val result = prefManager.getDecryptedString("user_id")
      OutCome.success(result)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(
          codeMapper = -2,
          messageMapper = e.localizedMessage ?: "Failed to retrieve user ID",
          errorFieldListMapper = emptyList(),
        ),
      )
    }
  }

  override suspend fun saveLatitude(latitude: Double): OutCome<Unit> {
    return try {
      prefManager.saveEncryptedDouble("latitude", latitude)
      OutCome.success(Unit)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-1, e.localizedMessage ?: "Failed to save latitude", emptyList()),
      )
    }
  }

  override suspend fun getLatitude(): OutCome<Double> {
    return try {
      val result = prefManager.getDecryptedDouble("latitude")
      OutCome.success(result)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-2, e.localizedMessage ?: "Failed to get latitude", emptyList()),
      )
    }
  }

  override suspend fun saveLoginCount(count: Int): OutCome<Unit> {
    return try {
      prefManager.saveEncryptedInt("login_count", count)
      OutCome.success(Unit)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-1, e.localizedMessage ?: "Failed to save login count", emptyList()),
      )
    }
  }

  override suspend fun getLoginCount(): OutCome<Int> {
    return try {
      val count = prefManager.getDecryptedInt("login_count")
      OutCome.success(count)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-2, e.localizedMessage ?: "Failed to get login count", emptyList()),
      )
    }
  }

  override suspend fun saveServerTime(time: ServerTime): OutCome<Unit> {
    return try {
      prefManager.saveEncryptedObject("server_time", time)
      OutCome.success(Unit)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-1, e.localizedMessage ?: "Failed to save server time", emptyList()),
      )
    }
  }

  override suspend fun getServerTime(): OutCome<ServerTime?> {
    return try {
      val result = prefManager.getDecryptedObject<ServerTime>("server_time")
      if (result != null) {
        OutCome.success(result)
      } else {
        OutCome.empty()
      }
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-2, e.localizedMessage ?: "Failed to get server time", emptyList()),
      )
    }
  }

  override suspend fun clearAll(): OutCome<Unit> {
    return try {
      prefManager.clearAll()
      OutCome.success(Unit)
    } catch (e: Exception) {
      OutCome.error(
        ErrorMessageMapper(-1, e.localizedMessage ?: "Failed to clear preferences", emptyList()),
      )
    }
  }
  //endregion
}
