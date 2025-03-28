package com.samir.bluearchitecture.viewmodelcases.flow1Basic.fragment.f2ConfiqChangeVM

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentConfigChangeVMBinding
import com.samir.bluearchitecture.viewmodelcases.flow1Basic.fragment.f1BasicVM.BasicVMViewModel
import kotlinx.coroutines.launch

/**
 * 🧪 ConfigChangeVMFragment
 *
 * Demonstrates the difference between:
 * - A value stored **outside** the ViewModel (`nonRetainedCounter`), which is lost on configuration change.
 * - A value inside a **ViewModel-backed StateFlow (`count`)**, which survives configuration changes.
 */
class ConfigChangeVMFragment : BaseFragment<ConfigChangeVMViewModel, FragmentConfigChangeVMBinding>() {

  /**
   * 🔴 Non-retained counter — this value is stored in the Fragment instance directly.
   * 🔥 Will reset to 100 on configuration changes (e.g., screen rotation).
   */
  private var nonRetainedCounter = 100
  override fun getContentView(): Int {
    return R.layout.fragment_config_change_v_m
  }

  override fun getAllViews(): List<View> {
    return emptyList()
  }

  override fun initializeListeners(): List<View> {
    return listOf<View>(baseViewBinding.incrementCounterButton)
  }

  override fun getClickListener(): View.OnClickListener {
    return this
  }

  override fun initializeViewModel() {
    val viewModel: ConfigChangeVMViewModel by viewModels()
    baseViewModel = viewModel
  }

  /**
   * ✅ Observe ViewModel's StateFlow to update UI with a retained value.
   */
  override fun subscribeObservers() {
    /**
     * 📦 Lifecycle-aware collection of StateFlow from ViewModel
     *
     * ✅ Purpose:
     * Safely collect data from a `StateFlow` (`counter`) inside the Fragment, without risking memory leaks or
     * unnecessary recomputation during lifecycle changes (like config change or backstack).
     *
     * ✅ Structure Breakdown:
     *
     * 🔹 lifecycleScope.launch { ... }
     * → Launches a coroutine tied to the Fragment’s lifecycle.
     * → Automatically cancels the coroutine when Fragment is destroyed.
     *
     * 🔹 viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) { ... }
     * → Ensures the `collect` block only runs **when the Fragment's view lifecycle is STARTED or RESUMED**.
     * → When the Fragment goes to STOPPED or DESTROYED, the collection automatically pauses.
     * → This is more efficient and avoids trying to update views when they're not visible.
     *
     * 🔹 (baseViewModel as? BasicVMViewModel)?.counter?.collect { count -> ... }
     * → We cast the base ViewModel to the actual implementation (`BasicVMViewModel`) to access its `counter` StateFlow.
     * → Collects values emitted from `counter` and updates the UI accordingly.
     *
     * ✅ Benefits:
     * - Prevents memory leaks
     * - Avoids collecting when UI is not visible (saves performance)
     * - Ensures your UI updates are **safe and lifecycle-aware**
     * - Fully supports process death + recreation (if StateHandle or saved state is used)
     *
     * ✅ Output Example:
     * Updates `titleTextView` with text: `"Counter: 3"` as values change from the `ViewModel`.
     */
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        (baseViewModel as? ConfigChangeVMViewModel)?.count?.collect { current ->
          // This value is retained across configuration changes
          baseViewBinding.myItem = current
        }
      }
    }
  }

  /**
   * Called when the fragment becomes active. Useful for restoring view state.
   * Here, we restore the non-retained counter to the screen.
   */
  override fun onFragmentActive() {
    super.onFragmentActive()
    baseViewBinding.textViewNonRetainedCount.text = "Count Retained (Layout) = " + nonRetainedCounter.toString()

    val viewModelFragmentOne: BasicVMViewModel by viewModels()
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        (viewModelFragmentOne as? BasicVMViewModel)?.counter?.collect { count ->
          baseViewBinding.textViewNonRetainedFirstFragmentCount.text = "Count Retained (View Model First Fragment) = " + count.toString()
        }
      }
    }
  }

  /**
   * Handles click events, updates both retained and non-retained counters.
   */
  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.incrementCounterButton -> {
        // ✅ ViewModel value: survives rotation
        baseViewModel.increment()

        // 🔴 Non-ViewModel value: gets reset on rotation
        nonRetainedCounter += 10
        baseViewBinding.textViewNonRetainedCount.text = "Count Retained (Layout) = " + nonRetainedCounter.toString()
      }
    }
  }
}
