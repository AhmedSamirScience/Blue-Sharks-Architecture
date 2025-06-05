package com.samir.bluearchitecture.offlinedata.presentation.prefDataStoreFlow.fragment.secondScreen

import androidx.annotation.Keep
import androidx.lifecycle.viewModelScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.domain.model.ServerTime
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.GetServerTimeUseCase
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.GetUserIdUseCase
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class PrefDataStoreSecViewModel @Inject constructor(
  private val getServerTimeUseCase: GetServerTimeUseCase,
  private val getUserIdUseCase: GetUserIdUseCase,

) : BaseViewModel() {
  override fun stop() {
  }

  private val _getServerTimeStateFlow = MutableStateFlow<LiveDataResource<ServerTime?>>(LiveDataResource.Idle())
  val getServerTimeStateFlow: StateFlow<LiveDataResource<ServerTime?>> = _getServerTimeStateFlow
  fun getServerTime() {
    viewModelScope.launch {
      getServerTimeUseCase.execute(
        input = Unit,
        success = {
          Logger.d(viewModel = this@PrefDataStoreSecViewModel, message = "getServerTime (Success) -> $it")
          _getServerTimeStateFlow.value = LiveDataResource.Success(it)
        },
        error = {
          Logger.e(viewModel = this@PrefDataStoreSecViewModel, message = "getServerTime (Error) -> ${it.messageMapper}")
          _getServerTimeStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@PrefDataStoreSecViewModel, message = "getServerTime (Loading)...")
          _getServerTimeStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@PrefDataStoreSecViewModel, message = "getServerTime (Idle)")
          _getServerTimeStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }

  private val _getUserIdStateFlow = MutableStateFlow<LiveDataResource<String>>(LiveDataResource.Idle())
  val getUserIdStateFlow: StateFlow<LiveDataResource<String>> = _getUserIdStateFlow
  fun getUserId() {
    viewModelScope.launch {
      getUserIdUseCase.execute(
        input = Unit,
        success = {
          Logger.d(viewModel = this@PrefDataStoreSecViewModel, message = "getUserId (Success): $it")
          _getUserIdStateFlow.value = LiveDataResource.Success(it)
        },
        error = {
          Logger.e(viewModel = this@PrefDataStoreSecViewModel, message = "getUserId (Error): ${it.messageMapper}")
          _getUserIdStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },
        loading = {
          Logger.v(viewModel = this@PrefDataStoreSecViewModel, message = "getUserId (Loading)...")
          _getUserIdStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@PrefDataStoreSecViewModel, message = "getUserId (Idle)")
          _getUserIdStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }
}
