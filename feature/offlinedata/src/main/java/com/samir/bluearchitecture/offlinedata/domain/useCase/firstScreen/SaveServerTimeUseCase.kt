package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import androidx.annotation.Keep
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.domain.model.ServerTime
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject
@Keep
class SaveServerTimeUseCase @Inject constructor(
  private val repository: AuthRepository,
) : AsyncUseCase<ServerTime, Unit>() {
  override suspend fun run(input: ServerTime): OutCome<Unit> {
    return repository.saveServerTime(input)
  }
}
