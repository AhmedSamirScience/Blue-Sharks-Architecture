package com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f1MainRDFlow

import androidx.annotation.Keep
import androidx.lifecycle.viewModelScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.domain.model.Login
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.CacheLoginOfflineUseCase
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.LoginUseCase
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.ui.utils.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class RoomDataBaseViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
  private val cacheLoginOfflineUseCase: CacheLoginOfflineUseCase,
) : BaseViewModel() {
  override fun stop() {
  }

  //region Cash Login
  private val _cacheLoginStateFlow = MutableStateFlow<LiveDataResource<Boolean>>(LiveDataResource.Idle())
  val cacheLoginStateFlow: StateFlow<LiveDataResource<Boolean>> = _cacheLoginStateFlow
  fun cacheLogin(request: LoginEntity) {
    viewModelScope.launch {
      cacheLoginOfflineUseCase.execute(
        input = request,
        success = {
          Logger.d(viewModel = this@RoomDataBaseViewModel, message = "cacheLogin (Success) -> data hase been inserted $it")
          _cacheLoginStateFlow.value = LiveDataResource.Success(true)
        },
        error = {
          Logger.e(viewModel = this@RoomDataBaseViewModel, message = "cacheLogin (Error) -> ${it.messageMapper}")
          _cacheLoginStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@RoomDataBaseViewModel, message = "cacheLogin (Loading) ...")
          _cacheLoginStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@RoomDataBaseViewModel, message = "cacheLogin Idle")
          _cacheLoginStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }
  //endregion

  //region Login
  private val _loginStateFlow = MutableStateFlow<LiveDataResource<Login>>(LiveDataResource.Idle())
  val loginStateFlow: StateFlow<LiveDataResource<Login>> = _loginStateFlow
  fun login(request: LoginRq) {
    viewModelScope.launch {
      loginUseCase.execute(
        input = request,
        success = {
          Logger.d(viewModel = this@RoomDataBaseViewModel, message = "login (Success) -> $it")
          _loginStateFlow.value = LiveDataResource.Success(it)
        },
        error = {
          Logger.e(viewModel = this@RoomDataBaseViewModel, message = "login (Error) -> ${it.messageMapper}")
          _loginStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@RoomDataBaseViewModel, message = "login (Loading) ...")
          _loginStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@RoomDataBaseViewModel, message = "login (Idle)")
          _loginStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }
  //endregion
}
