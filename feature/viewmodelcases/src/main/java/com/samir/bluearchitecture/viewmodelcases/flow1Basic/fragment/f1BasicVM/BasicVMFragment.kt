package com.samir.bluearchitecture.viewmodelcases.flow1Basic.fragment.f1BasicVM

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentBasicVMBinding
import kotlinx.coroutines.launch

class BasicVMFragment : BaseFragment<BasicVMViewModel, FragmentBasicVMBinding>() {
  override fun getContentView(): Int {
    return R.layout.fragment_basic_v_m
  }

  override fun getAllViews(): List<View> {
    return emptyList()
  }

  override fun initializeListeners(): List<View> {
    return listOf<View>(baseViewBinding.firstFragmentButton)
  }

  override fun getClickListener(): View.OnClickListener {
    return this
  }

  override fun initializeViewModel() {
    val viewModel: BasicVMViewModel by viewModels()
    baseViewModel = viewModel
  }

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
        (baseViewModel as? BasicVMViewModel)?.counter?.collect { count ->
          baseViewBinding.myItem = DisplayClass(count)
        }
      }
    }
  }

  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.firstFragmentButton -> {
        baseViewModel.increment()
        val direction = BasicVMFragmentDirections.actionBasicVMFragmentToConfigChangeVMFragment()
        findNavController().navigate(direction)
      }
    }
  }
}
