package com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f2NavGraphVM

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentNavGraphVMSecondBinding
import com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f1NavGraphVM.NavGraphVMFirstViewModel
import kotlinx.coroutines.launch

/**
 * 📦 NavGraphVMSecondFragment
 *
 * ✅ Purpose:
 * This fragment demonstrates the continuation of a shared ViewModel (`NavGraphVMFirstViewModel`)
 * scoped to a specific navigation graph (`nav_navgraph_vm`). It participates in the same
 * graph-based ViewModel scope as `NavGraphVMFirstFragment`.
 *
 * ✅ ViewModel Scope:
 * - The ViewModel is scoped to the `nav_navgraph_vm` navigation graph.
 * - This is achieved via:
 * ```kotlin
 * val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_vm)
 * val viewModel: NavGraphVMFirstViewModel by viewModels({ backStackEntry })
 * ```
 * - Ensures shared state across all fragments within the graph.
 *
 * ✅ Key Interactions:
 * - Increments a shared counter using the ViewModel.
 * - Navigates to another destination (`nav_navgraph_second_vm`) through Safe Args.
 *
 * ✅ Lifecycle-Safe StateFlow Collection:
 * - Uses `repeatOnLifecycle` to collect the `counter` value only while the fragment is visible,
 *   ensuring efficient and safe UI updates.
 *
 * ✅ Buttons:
 * - `incrementCounterButton`: increases the counter in the ViewModel
 * - `toAnotherActivityButton`: navigates to another navigation graph destination
 *
 * ✅ Architecture:
 * - Extends a custom `BaseFragment` with support for ViewModel and ViewBinding injection
 * - Maintains clean, decoupled MVVM structure
 */
class NavGraphVMSecondFragment : BaseFragment<NavGraphVMFirstViewModel, FragmentNavGraphVMSecondBinding>() {

  /**
   * Specifies the layout resource associated with this fragment.
   */
  override fun getContentView(): Int {
    return R.layout.fragment_nav_graph_v_m_second
  }

  /**
   * No additional views are initialized in this fragment.
   */
  override fun getAllViews(): List<View> = emptyList()

  /**
   * Returns the interactive views handled by `onClick()`.
   */
  override fun initializeListeners(): List<View> = listOf(baseViewBinding.incrementCounterButton, baseViewBinding.toAnotherActivityButton)

  /**
   * Returns the common click listener for this fragment.
   */
  override fun getClickListener(): View.OnClickListener = this

  /**
   * Initializes the shared ViewModel using a scoped NavBackStackEntry for the
   * navigation graph. This replaces `navGraphViewModels()` to avoid lifecycle timing issues.
   */
  override fun initializeViewModel() {
    /*val viewModel: NavGraphVMFirstViewModel by navGraphViewModels(R.navigation.nav_navgraph_vm)
    baseViewModel = viewModel*/
    val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_vm)
    val viewModel: NavGraphVMFirstViewModel by viewModels({ backStackEntry })
    baseViewModel = viewModel
  }

  /**
   * Observes the `counter` StateFlow from the shared ViewModel and updates the UI binding
   * using a lifecycle-safe `repeatOnLifecycle` pattern.
   */
  override fun subscribeObservers() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        baseViewModel.counter.collect { count ->
          baseViewBinding.myItem = count
        }
      }
    }
  }

  /**
   * Handles click events for the UI buttons in this fragment.
   */
  override fun onClick(v: View?) {
    when (v) {
      // ➕ Increment the counter via the shared ViewModel
      baseViewBinding.incrementCounterButton -> {
        baseViewModel.increment()
      }

      // 🔁 Navigate to another destination within or outside the nav graph
      baseViewBinding.toAnotherActivityButton -> {
        val direction = NavGraphVMSecondFragmentDirections.actionNavGraphVMSecondFragmentToNavNavgraphSecondVm()
        findNavController().navigate(direction)
      }
    }
  }
}
