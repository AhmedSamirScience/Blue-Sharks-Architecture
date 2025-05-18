package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import androidx.annotation.Keep
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import javax.inject.Inject
@Keep
class SaveUserIdUseCase @Inject constructor(
  private val repository: AuthRepository,
) : AsyncUseCase<String, Unit>() {
  override suspend fun run(input: String): OutCome<Unit> {
    return repository.saveUserId(input)
  }
}
