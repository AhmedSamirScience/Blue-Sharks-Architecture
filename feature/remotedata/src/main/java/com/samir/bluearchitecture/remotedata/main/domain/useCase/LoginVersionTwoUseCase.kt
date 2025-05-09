package com.samir.bluearchitecture.remotedata.main.domain.useCase

import androidx.annotation.Keep
import com.samir.bluearchitecture.data.main.di.StringResourceProvider
import com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.domain.main.result.mapOrElse
import com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase
import com.samir.bluearchitecture.remotedata.R
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.remotedata.main.domain.model.Login
import com.samir.bluearchitecture.remotedata.main.domain.repository.AuthRepository
import com.samir.bluearchitecture.ui.utils.logging.Logger
import javax.inject.Inject
@Keep
class LoginVersionTwoUseCase @Inject constructor(
  private val repository: AuthRepository,
  private val stringResourceProvider: StringResourceProvider,
) : AsyncUseCase<LoginRq, Login>() {
  override suspend fun run(input: LoginRq): OutCome<Login> {
    return repository.login(input).mapOrElse { login ->
      Logger.d(useCase = this@LoginVersionTwoUseCase::class.java, message = "LoginVersionTwoUseCase (i w): $login")
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
