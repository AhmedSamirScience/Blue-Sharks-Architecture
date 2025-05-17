package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject

class GetLastLoginOfflineUseCase @Inject constructor(
  private val repository: AuthRepository,
) : AsyncUseCase<LoginEntity, LoginEntity>() {

  override suspend fun run(input: LoginEntity): OutCome<LoginEntity> {
    return repository.getLastLogin()
  }
}
