package com.samir.bluearchitecture.viewmodelcases.flow1Basic.fragment.f2ConfiqChangeVM

import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * 🔁 ViewModel for ConfigChangeVMFragment — Demonstrates StateFlow behavior during Configuration Changes
 *
 * ✅ Purpose:
 * This ViewModel is designed to test how `ViewModel` and `StateFlow` behave when configuration changes occur
 * (e.g., screen rotation). It starts with an initial count of 100 and increments the value by 10 on each trigger.
 *
 * ✅ Key Features:
 * - Holds a `StateFlow<Int>` called `count` starting at 100
 * - Offers `increment()` method to simulate state mutation
 * - State is preserved across config changes like screen rotation
 *
 * ✅ Ideal For:
 * - Validating `ViewModel` persistence across config changes
 * - Learning how to structure `StateFlow` emission logic
 * - Demonstrating view-model scoped coroutine collection
 *
 * ✅ Example Scenario:
 * - A button in `ConfigChangeVMFragment` calls `increment()`
 * - The UI observes the `count` via `repeatOnLifecycle`
 * - Rotate the device → ViewModel survives → same state shown
 *
 * ✅ UI Usage:
 * ```kotlin
 * lifecycleScope.launch {
 *     viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
 *         viewModel.count.collect { value ->
 *             textView.text = "Count: $value"
 *         }
 *     }
 * }
 * ```
 */
class ConfigChangeVMViewModel : BaseViewModel() {

  /**
   * Optional lifecycle-aware stop logic.
   * Currently unused.
   */
  override fun stop() {
  }

  /**
   * Private backing property for the count.
   * Initially set to 100.
   */
  private val _count = MutableStateFlow(100)

  /**
   * Public read-only StateFlow.
   * Observed from the UI for state updates.
   */
  val count: StateFlow<Int> = _count

  /**
   * Increments the current count value by 10.
   *
   * 🧪 Used to test whether state is retained across config changes.
   */
  fun increment() {
    _count.value += 10
  }
}
