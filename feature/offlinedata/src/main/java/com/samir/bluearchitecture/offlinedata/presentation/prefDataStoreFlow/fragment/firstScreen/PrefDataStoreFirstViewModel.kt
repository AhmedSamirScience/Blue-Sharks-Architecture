package com.samir.bluearchitecture.offlinedata.presentation.prefDataStoreFlow.fragment.firstScreen

import androidx.annotation.Keep
import androidx.lifecycle.viewModelScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.model.Login
import com.samir.bluearchitecture.offlinedata.domain.model.ServerTime
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.LoginUseCase
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.SaveServerTimeUseCase
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.SaveUserIdUseCase
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class PrefDataStoreFirstViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
  private val saveServerTimeUseCase: SaveServerTimeUseCase,
  private val saveUserIdUseCase: SaveUserIdUseCase,
) : BaseViewModel() {
  override fun stop() {
  }

  //region Login
  private val _loginStateFlow = MutableStateFlow<LiveDataResource<Login>>(LiveDataResource.Idle())
  val loginStateFlow: StateFlow<LiveDataResource<Login>> = _loginStateFlow
  fun login(request: LoginRq) {
    viewModelScope.launch {
      loginUseCase.execute(
        input = request,
        success = {
          Logger.d(viewModel = this@PrefDataStoreFirstViewModel, message = "login (Success) -> $it")
          _loginStateFlow.value = LiveDataResource.Success(it)
        },
        error = {
          Logger.e(viewModel = this@PrefDataStoreFirstViewModel, message = "login (Error) -> ${it.messageMapper}")
          _loginStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@PrefDataStoreFirstViewModel, message = "login (Loading) ...")
          _loginStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@PrefDataStoreFirstViewModel, message = "login (Idle)")
          _loginStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }
  //endregion

  //region Save Server Time
  private val _saveServerTimeState = MutableStateFlow<LiveDataResource<Unit>>(LiveDataResource.Idle())
  val saveServerTimeState: StateFlow<LiveDataResource<Unit>> = _saveServerTimeState

  fun saveServerTime(serverTime: ServerTime) {
    viewModelScope.launch {
      saveServerTimeUseCase.execute(
        input = serverTime,
        success = {
          Logger.d(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveServerTime (Success)")
          _saveServerTimeState.value = LiveDataResource.Success(Unit)
        },
        error = {
          Logger.e(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveServerTime (Error) -> ${it.messageMapper}")
          _saveServerTimeState.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveServerTime (Loading)...")
          _saveServerTimeState.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveServerTime (Idle)")
          _saveServerTimeState.value = LiveDataResource.Idle()
        },
      )
    }
  }

  //endregion

  //region Save User Id
  private val _saveUserIdState = MutableStateFlow<LiveDataResource<Unit>>(LiveDataResource.Idle())
  val saveUserIdState: StateFlow<LiveDataResource<Unit>> = _saveUserIdState
  fun saveUserId(userId: String) {
    viewModelScope.launch {
      saveUserIdUseCase.execute(
        input = userId,
        success = {
          Logger.d(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveUserId (Success)")
          _saveUserIdState.value = LiveDataResource.Success(Unit)
        },
        error = {
          Logger.e(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveUserId (Error) -> ${it.messageMapper}")
          _saveUserIdState.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveUserId (Loading)...")
          _saveUserIdState.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@PrefDataStoreFirstViewModel, message = "SaveUserId (Idle)")
          _saveUserIdState.value = LiveDataResource.Idle()
        },
      )
    }
  }
  //endregion
}
