package com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f1ActivityVM

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentActivityVMFirstBinding
import kotlinx.coroutines.launch

/**
 * 🧠 ActivityVMFirstFragment
 *
 * ✅ Purpose:
 * This fragment demonstrates the usage of a **shared ViewModel scoped to the hosting Activity**.
 * This allows the ViewModel (`ActivityVMFirstViewModel`) to be accessed across multiple fragments
 * (e.g., this one and `ActivityVMSecondFragment`) and retain state during fragment transitions.
 *
 * ✅ Use Case:
 * - Shared state between fragments (e.g., a counter that both fragments can access and update).
 * - Simplified communication between fragments without using `FragmentResult`, `NavBackStackEntry`, or callbacks.
 *
 * ✅ Key Concepts Demonstrated:
 * - `by activityViewModels()`: Retrieves a ViewModel that is scoped to the Activity.
 * - Lifecycle-aware collection of `StateFlow` using `repeatOnLifecycle`.
 * - Safe navigation using `NavController`.
 * - Modular, reusable base fragment pattern.
 *
 * ✅ Example Scenario:
 * - The user increments a counter in Fragment A.
 * - The user navigates to Fragment B.
 * - The counter retains its value in Fragment B due to shared ViewModel scope.
 */
class ActivityVMFirstFragment : BaseFragment<ActivityVMFirstViewModel, FragmentActivityVMFirstBinding>() {
  /**
   * Inflates the layout for this fragment using DataBinding.
   */
  override fun getContentView(): Int = R.layout.fragment_activity_v_m_first

  /**
   * Returns a list of views to apply view-related actions (e.g., animations, visibility).
   * Here, we return an empty list because all interactions are handled through listeners.
   */
  override fun getAllViews(): List<View> = emptyList()

  /**
   * Initializes and returns the list of clickable views for this fragment.
   */
  override fun initializeListeners(): List<View> =
    listOf(baseViewBinding.firstFragmentButton, baseViewBinding.increaseButton)

  /**
   * Defines the click listener handler for all interactive views.
   */
  override fun getClickListener(): View.OnClickListener = this

  /**
   * Initializes the shared ViewModel instance scoped to the parent Activity.
   *
   * 🔄 This ensures both ActivityVMFirstFragment and ActivityVMSecondFragment access
   * the same instance of ActivityVMFirstViewModel.
   */
  override fun initializeViewModel() {
    val viewModel: ActivityVMFirstViewModel by activityViewModels()
    baseViewModel = viewModel
  }

  /**
   * Subscribes to UI state emitted from the ViewModel's `counter` StateFlow.
   *
   * 🌀 The value is bound to the layout via the `myItem` binding variable.
   * 🧠 This value is retained across fragment transitions or configuration changes.
   */
  override fun subscribeObservers() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        baseViewModel.counter.collect { count ->
          baseViewBinding.myItem = CounterClass(count)
        }
      }
    }
  }

  /**
   * Handles user interaction events from the registered views.
   */
  override fun onClick(v: View?) {
    when (v) {
      // 🔀 Navigates to second fragment, which shares the same ViewModel instance
      baseViewBinding.firstFragmentButton -> {
        val direction = ActivityVMFirstFragmentDirections
          .actionActivityVMFirstFragmentToActivityVMSecondFragment()
        findNavController().navigate(direction)
      }

      // ➕ Increments the shared counter in the ViewModel
      baseViewBinding.increaseButton -> {
        baseViewModel.increment()
      }
    }
  }
}
