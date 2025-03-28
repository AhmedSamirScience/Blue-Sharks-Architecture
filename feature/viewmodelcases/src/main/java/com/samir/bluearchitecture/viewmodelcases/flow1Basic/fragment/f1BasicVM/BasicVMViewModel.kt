package com.samir.bluearchitecture.viewmodelcases.flow1Basic.fragment.f1BasicVM

import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * 🧠 ViewModel for BasicVMFragment — Demonstrates ViewModel + StateFlow integration
 *
 * ✅ Purpose:
 * This ViewModel is used to manage and expose a simple counter state using Kotlin's `StateFlow`.
 * It demonstrates a unidirectional data flow from ViewModel → UI, and is designed to survive
 * configuration changes while maintaining the latest state.
 *
 * ✅ Key Features:
 * - Holds a mutable counter value that is exposed as an immutable `StateFlow`
 * - Provides a public `increment()` method to update the counter
 * - Safe to collect from Fragments using lifecycle-aware constructs like `repeatOnLifecycle`
 *
 * ✅ Usage Scenario:
 * Ideal for demonstrating:
 * - How `StateFlow` works inside a ViewModel
 * - How Fragments collect updates safely
 * - How state survives rotation and configuration changes
 *
 * 🧪 Example Usage (in Fragment):
 * ```kotlin
 * lifecycleScope.launch {
 *     viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
 *         viewModel.counter.collect { count ->
 *             textView.text = "Counter: $count"
 *         }
 *     }
 * }
 * ```
 */
class BasicVMViewModel : BaseViewModel() {

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
