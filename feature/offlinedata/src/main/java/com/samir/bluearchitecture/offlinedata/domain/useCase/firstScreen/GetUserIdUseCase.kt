package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import androidx.annotation.Keep
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.result.mapOrElse
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject
@Keep
class GetUserIdUseCase @Inject constructor(
  private val repository: AuthRepository,
) : AsyncUseCase<Unit, String>() {
  override suspend fun run(input: Unit): OutCome<String> {
    return repository.getUserId().mapOrElse {
      OutCome.success(it)
    }
  }
}
