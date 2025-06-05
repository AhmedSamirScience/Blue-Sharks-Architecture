package com.samir.bluearchitecture.remotedata.main.flow1Basic.fragment.f1BasicRD

import androidx.annotation.Keep
import androidx.lifecycle.viewModelScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.remotedata.main.domain.model.Login
import com.samir.bluearchitecture.remotedata.main.domain.useCase.LoginUseCase
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class BasicRDFirstViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
) : BaseViewModel() {
  override fun stop() {}

  private val _loginStateFlow = MutableStateFlow<LiveDataResource<Login>>(LiveDataResource.Idle())
  val loginStateFlow: StateFlow<LiveDataResource<Login>> = _loginStateFlow
  fun login(request: LoginRq) {
    viewModelScope.launch {
      loginUseCase.execute(
        input = request,
        success = {
          Logger.d(viewModel = this@BasicRDFirstViewModel, message = "login (Success) -> $it")
          _loginStateFlow.value = LiveDataResource.Success(it)
        },
        error = {
          Logger.e(viewModel = this@BasicRDFirstViewModel, message = "login (Error) -> ${it.messageMapper}")
          _loginStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@BasicRDFirstViewModel, message = "login (Loading) ...")
          _loginStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@BasicRDFirstViewModel, message = "login (Idle)")
          _loginStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }
}
