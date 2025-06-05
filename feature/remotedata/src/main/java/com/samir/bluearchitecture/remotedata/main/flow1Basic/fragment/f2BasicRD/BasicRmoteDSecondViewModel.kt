package com.samir.bluearchitecture.remotedata.main.flow1Basic.fragment.f2BasicRD

import androidx.annotation.Keep
import androidx.lifecycle.viewModelScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.remotedata.main.domain.model.Login
import com.samir.bluearchitecture.remotedata.main.domain.useCase.LoginUseCase
import com.samir.bluearchitecture.remotedata.main.domain.useCase.LoginVersionTwoUseCase
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class BasicRmoteDSecondViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
  private val loginVersionTwoUseCase: LoginVersionTwoUseCase,
) : BaseViewModel() {
  override fun stop() {}

  private val _loginStateFlow = MutableStateFlow<LiveDataResource<Login>>(LiveDataResource.Idle())
  val loginStateFlow: StateFlow<LiveDataResource<Login>> = _loginStateFlow

  /**
   * Fires two login-related API use cases in parallel and emits the result to the UI layer.
   *
   * This method:
   * - Starts both `loginUseCase` and `loginVersionTwoUseCase` using `async` in parallel.
   * - Wraps the execution in `runCatching {}` to safely handle exceptions without crashing.
   * - Emits a `LiveDataResource` representing Loading, Success, or Error state.
   *
   * ---
   * ### 🔁 Flow:
   * 1. Emit `Loading` state.
   * 2. Launch both use cases concurrently using `coroutineScope` and `async`.
   * 3. Await both results with `await()`.
   * 4. Emit:
   *    - `Success` if both succeed.
   *    - `Error` if either one fails.
   *    - Catch-all error if an exception occurs.
   *
   * ---
   * ### 💡 Notes:
   * - `runCatching {}` safely catches all exceptions without the need for try/catch.
   * - `coroutineScope` ensures both jobs are cancelled if one fails.
   * - The result of each use case is expected to be an `OutCome<T>` (Success, Error, or Empty).
   *
   * @param request The login request payload containing username, password, and device token.
   */
  fun login(request: LoginRq) {
    viewModelScope.launch {
      // Emit loading state to show progress indicator
      _loginStateFlow.value = LiveDataResource.Loading()

      // Run both API calls in parallel and safely handle any exception
      val result = kotlin.runCatching {
        kotlinx.coroutines.coroutineScope {
          // Launch both login use cases concurrently
          val loginJob1 = async { loginUseCase.run(request) }
          val loginJob2 = async { loginVersionTwoUseCase.run(request) }

          // Wait for both to complete
          val result1 = loginJob1.await()
          val result2 = loginJob2.await()

          // Return both results as a Pair
          Pair(result1, result2)
        }
      }

      result.onSuccess { (res1, res2) -> // Handle success case if no exception occurred
        // You can combine them, prioritize one, or show both independently
        if (res1 is OutCome.Success && res2 is OutCome.Success) { // ✅ Both succeeded — emit one or combine them
          Logger.d(viewModel = this@BasicRmoteDSecondViewModel, message = "login (Success) -> ${res1.data}")
          _loginStateFlow.value = LiveDataResource.Success(res1.data)
        } else if (res1 is OutCome.Error) { // ❌ First use case failed
          Logger.e(viewModel = this@BasicRmoteDSecondViewModel, message = "login (Error) -> ${res1.errorMessage().messageMapper}")
          _loginStateFlow.value = LiveDataResource.Error(res1.errorMessage().messageMapper)
        } else if (res2 is OutCome.Error) { // ❌ Second use case failed
          Logger.e(viewModel = this@BasicRmoteDSecondViewModel, message = "login (Error) -> ${res2.errorMessage().messageMapper}")
          _loginStateFlow.value = LiveDataResource.Error(res2.errorMessage().messageMapper)
        } else { // 🟠 Neither succeeded but didn't fail clearly
          Logger.e(viewModel = this@BasicRmoteDSecondViewModel, message = "login (Unknown state) -> $res1 and $res2")
          _loginStateFlow.value = LiveDataResource.Error("Unknown state")
        }
      }.onFailure { e -> // Handle failure if an exception occurred (network, null pointer, etc.)
        Logger.e(viewModel = this@BasicRmoteDSecondViewModel, message = "login (Exception) -> ${e.message}")
        _loginStateFlow.value = LiveDataResource.Error(e.message ?: "Unexpected error")
      }
    }
  }
}
