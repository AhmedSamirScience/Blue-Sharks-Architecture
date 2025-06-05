package com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen

import androidx.annotation.Keep
import com.samir.bluearchitecture.data.main.remote.di.StringResourceProvider
import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.result.mapOrElse
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.model.Login
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import com.samir.bluearchitecture.utils.logging.Logger
import javax.inject.Inject
@Keep
class LoginUseCase @Inject constructor(
  private val repository: AuthRepository,
  private val stringResourceProvider: StringResourceProvider,
) : AsyncUseCase<LoginRq, Login>() {
  override suspend fun run(input: LoginRq): OutCome<Login> {
    return repository.login(input).mapOrElse { login ->
      Logger.d(useCase = this@LoginUseCase::class.java, message = "loginUseCase (working): $login")
      if (login.isDataValid()) {
        if (login.errorMessage == "Wrong Email or Password") {
          OutCome.error(ErrorMessageMapper(1, stringResourceProvider.getString(R.string.firstFragment_loginFailed), emptyList()))
        } else {
          OutCome.success(login)
        }
      } else {
        OutCome.error(ErrorMessageMapper(1, stringResourceProvider.getString(R.string.app_error_invalid_data_received), emptyList()))
      }
    }
  }
}
