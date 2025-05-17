package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject

class CacheLoginOfflineUseCase @Inject constructor(
  private val repository: AuthRepository,
) : AsyncUseCase<LoginEntity, Unit>() {

  override suspend fun run(input: LoginEntity): OutCome<Unit> {
    return repository.cacheLogin(
      login = LoginEntity(
        userId = input.userId.orEmpty(),
        userName = input.userName, // (assuming you're just simulating a Login from LoginRq)
      ),
    )
  }
}
