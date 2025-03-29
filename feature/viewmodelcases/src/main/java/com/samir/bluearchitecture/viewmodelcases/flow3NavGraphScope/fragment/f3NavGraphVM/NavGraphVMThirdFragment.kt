package com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f3NavGraphVM

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentNavGraphVMThirdBinding
import com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f1NavGraphVM.NavGraphVMFirstViewModel
import kotlinx.coroutines.launch

/**
 * 📦 NavGraphVMThirdFragment
 *
 * ✅ Purpose:
 * This fragment demonstrates continued use of a shared ViewModel (`NavGraphVMFirstViewModel`)
 * that is scoped to a **secondary navigation graph** (`nav_navgraph_second_vm`).
 *
 * It showcases how to:
 * - Scope a ViewModel to a specific graph using a back stack entry
 * - Observe a shared counter via StateFlow
 * - Modify the shared state from within this fragment
 *
 * ✅ ViewModel Scope:
 * The ViewModel is explicitly scoped to the `nav_navgraph_second_vm` navigation graph.
 * ```kotlin
 * val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_second_vm)
 * val viewModel: NavGraphVMFirstViewModel by viewModels({ backStackEntry })
 * ```
 * This ensures state is retained across fragments within that graph only.
 *
 * ✅ Responsibilities:
 * - Display the shared counter in the UI
 * - Provide a button to increment the counter
 *
 * ✅ Lifecycle Awareness:
 * Uses `repeatOnLifecycle` to observe StateFlow only while the view is in the `STARTED` state,
 * preventing memory leaks and unnecessary recomputations.
 *
 * ✅ UI Binding:
 * - `myItem` is bound to the counter value
 * - `incrementCounterButton` triggers state mutation via the ViewModel
 */
class NavGraphVMThirdFragment : BaseFragment<NavGraphVMFirstViewModel, FragmentNavGraphVMThirdBinding>() {

  /**
   * Inflates the third fragment layout.
   */
  override fun getContentView(): Int = R.layout.fragment_nav_graph_v_m_third

  /**
   * No special views to fetch outside the default binding.
   */
  override fun getAllViews(): List<View> = emptyList()

  /**
   * Defines which views will use the shared click listener.
   */
  override fun initializeListeners(): List<View> = listOf(baseViewBinding.incrementCounterButton)

  /**
   * Provides the shared click listener for handling UI interaction.
   */
  override fun getClickListener(): View.OnClickListener = this

  /**
   * Initializes the ViewModel using a manual NavBackStackEntry tied to the graph ID.
   *
   * This is used instead of `navGraphViewModels()` to avoid potential lifecycle timing issues.
   */
  override fun initializeViewModel() {
/*    val viewModel: NavGraphVMFirstViewModel by navGraphViewModels(R.navigation.nav_navgraph_second_vm)
    baseViewModel = viewModel*/
    val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_second_vm)
    val viewModel: NavGraphVMFirstViewModel by viewModels({ backStackEntry })
    baseViewModel = viewModel
  }

  /**
   * Observes the ViewModel's counter StateFlow and updates the binding accordingly.
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
   * Handles button clicks. Currently only supports incrementing the shared counter.
   */
  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.incrementCounterButton -> {
        baseViewModel.increment()
      }
    }
  }
}
