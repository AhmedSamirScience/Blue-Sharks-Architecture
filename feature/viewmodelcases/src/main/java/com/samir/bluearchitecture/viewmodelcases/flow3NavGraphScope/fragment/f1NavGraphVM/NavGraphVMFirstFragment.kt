package com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f1NavGraphVM

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentNavGraphVMFirstBinding
import kotlinx.coroutines.launch

/**
 * 📦 NavGraphVMFirstFragment
 *
 * ✅ Purpose:
 * This fragment demonstrates how to scope a ViewModel to a specific **navigation graph**
 * using `getBackStackEntry(...)` + `viewModels()` — a reliable alternative to `navGraphViewModels()`.
 *
 * This is the **first destination** in the navigation graph `nav_navgraph_vm`, and it shares
 * a `NavGraphVMFirstViewModel` instance with other fragments within the same graph.
 *
 * ✅ ViewModel Scope:
 * The ViewModel is scoped to the `nav_navgraph_vm` navigation graph using:
 * ```kotlin
 * val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_vm)
 * val viewModel: NavGraphVMFirstViewModel by viewModels({ backStackEntry })
 * ```
 * This ensures the ViewModel survives across destinations in the graph but not beyond.
 *
 * ✅ Responsibilities:
 * - Displays a shared counter from the ViewModel
 * - Allows the user to increment the counter
 * - Navigates to a second fragment within the same graph
 *
 * ✅ Lifecycle Awareness:
 * - Uses `repeatOnLifecycle(Lifecycle.State.STARTED)` to safely collect StateFlow
 *   emissions only while the UI is visible.
 *
 * ✅ UI Components:
 * - `firstFragmentButton`: navigates to second screen
 * - `increaseButton`: triggers counter increment
 *
 * ✅ Integration:
 * - This class extends a `BaseFragment` with generic ViewModel/DataBinding support.
 * - Uses strongly-typed navigation via Safe Args.
 */
class NavGraphVMFirstFragment : BaseFragment<NavGraphVMFirstViewModel, FragmentNavGraphVMFirstBinding>() {

  /**
   * Specifies the layout to be inflated for this fragment using data binding.
   */
  override fun getContentView(): Int = R.layout.fragment_nav_graph_v_m_first

  /**
   * No additional views are handled outside of listeners.
   */
  override fun getAllViews(): List<View> = emptyList()

  /**
   * Returns the list of views that will trigger `onClick()` events.
   */
  override fun initializeListeners(): List<View> = listOf(baseViewBinding.firstFragmentButton, baseViewBinding.increaseButton)

  /**
   * Provides a single shared click listener for all clickable views.
   */
  override fun getClickListener(): View.OnClickListener = this

  /**
   * Initializes the ViewModel using a scoped `NavBackStackEntry` for the navigation graph.
   *
   * ⚠️ `navGraphViewModels()` is avoided here to prevent potential crashes
   * when the graph isn't yet on the back stack at the time of ViewModel initialization.
   */
  override fun initializeViewModel() {
    /*val viewModel: NavGraphVMFirstViewModel by navGraphViewModels(R.navigation.nav_navgraph_vm)
    baseViewModel = viewModel*/
    val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_vm)
    val viewModel: NavGraphVMFirstViewModel by viewModels({ backStackEntry })
    baseViewModel = viewModel
  }

  /**
   * Observes the `counter` StateFlow from the ViewModel and updates the UI binding.
   *
   * 📦 StateFlow is collected in a lifecycle-safe manner using `repeatOnLifecycle`.
   * This ensures the UI is only updated when the Fragment's view is at least STARTED.
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
   * Handles user interactions with the registered clickable views.
   */
  override fun onClick(v: View?) {
    when (v) {
      // 🔁 Navigates to the next fragment
      baseViewBinding.firstFragmentButton -> {
        val direction = NavGraphVMFirstFragmentDirections.actionNavGraphVMFirstFragmentToNavGraphVMSecondFragment()
        findNavController().navigate(direction)
      }

      // ➕ Increments the shared counter in the ViewModel
      baseViewBinding.increaseButton -> {
        baseViewModel.increment()
      }
    }
  }
}
