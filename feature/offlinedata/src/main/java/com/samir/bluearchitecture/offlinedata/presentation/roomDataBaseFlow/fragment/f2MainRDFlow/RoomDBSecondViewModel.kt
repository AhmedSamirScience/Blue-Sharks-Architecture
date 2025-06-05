package com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow

import androidx.annotation.Keep
import androidx.lifecycle.viewModelScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.domain.useCase.firstScreen.GetAllLoginsOfflineUseCase
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class RoomDBSecondViewModel @Inject constructor(
  private val getAllLoginsOfflineUseCase: GetAllLoginsOfflineUseCase,
) : BaseViewModel() {
  override fun stop() {
  }

  //region Get All Logins
  private val _getAllLoginsStateFlow = MutableStateFlow<LiveDataResource<List<LoginEntity>>>(LiveDataResource.Idle())
  val getAllLoginsStateFlow: StateFlow<LiveDataResource<List<LoginEntity>>> = _getAllLoginsStateFlow
  fun getAllLogins() {
    viewModelScope.launch {
      getAllLoginsOfflineUseCase.execute(
        input = Unit,
        success = { logins ->
          Logger.d(viewModel = this@RoomDBSecondViewModel, message = "getAllLogins (Success): $logins")
          _getAllLoginsStateFlow.value = LiveDataResource.Success(logins)
        },
        error = {
          Logger.e(viewModel = this@RoomDBSecondViewModel, message = "getAllLogins (Error): ${it.messageMapper}")
          _getAllLoginsStateFlow.value = LiveDataResource.Error(it.messageMapper)
        },

        loading = {
          Logger.v(viewModel = this@RoomDBSecondViewModel, message = "getAllLogins (Loading): loading")
          _getAllLoginsStateFlow.value = LiveDataResource.Loading()
        },
        idle = {
          Logger.v(viewModel = this@RoomDBSecondViewModel, message = "getAllLogins (Idle): idle state")
          _getAllLoginsStateFlow.value = LiveDataResource.Idle()
        },
      )
    }
  }
  //endregion
}
