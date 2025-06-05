package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import com.samir.bluearchitecture.data.main.remote.di.StringResourceProvider
import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.result.mapOrElse
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import com.samir.bluearchitecture.utils.logging.Logger
import javax.inject.Inject

class GetAllLoginsOfflineUseCase @Inject constructor(
  private val repository: AuthRepository,
  private val stringResourceProvider: StringResourceProvider,
) : AsyncUseCase<Unit, List<LoginEntity>>() {

  override suspend fun run(input: Unit): OutCome<List<LoginEntity>> {
    return repository.getAllLogins().mapOrElse { logins ->
      Logger.d(useCase = this@GetAllLoginsOfflineUseCase::class.java, message = "loginUseCase (working): $logins")
      if (logins.isNotEmpty()) {
        OutCome.success(logins)
      } else {
        OutCome.error(ErrorMessageMapper(1, stringResourceProvider.getString(R.string.firstFragment_noDataFound), emptyList()))
      }
    }
  }
}
