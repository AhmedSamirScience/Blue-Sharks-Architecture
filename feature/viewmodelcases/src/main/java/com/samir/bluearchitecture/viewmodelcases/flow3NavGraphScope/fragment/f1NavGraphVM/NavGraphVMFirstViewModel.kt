package com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f1NavGraphVM

import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavGraphVMFirstViewModel : BaseViewModel() {
  /**
   * Called when ViewModel should release resources or stop processes.
   * In this case, it's empty as no cleanup is needed.
   */
  override fun stop() {
  }

  /**
   * Backing property for the counter value.
   * Starts at 0 and is incremented via [increment].
   *
   * 🔒 Private Mutable StateFlow
   */
  private val _counter = MutableStateFlow(0)

  /**
   * Exposed immutable version of [_counter].
   * Can be safely collected from the UI.
   *
   * 🔓 Public Read-Only StateFlow
   */
  val counter: StateFlow<Int> = _counter

  /**
   * Increments the counter by 1.
   *
   * 🧪 Call this method from the UI (e.g., button click) to emit a new value.
   * Each emission will automatically trigger UI collectors.
   */
  fun increment() {
    _counter.value++
  }
}
