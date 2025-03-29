package com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f2ActivityVM

import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentActivityVMSecondBinding
import com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.activity.ScopeVMSecondActivity
import com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f1ActivityVM.ActivityVMFirstViewModel
import kotlinx.coroutines.launch

class ActivityVMSecondFragment : BaseFragment<ActivityVMFirstViewModel, FragmentActivityVMSecondBinding>() {
  /**
   * Sets the layout resource to be inflated using Data Binding.
   */
  override fun getContentView(): Int = R.layout.fragment_activity_v_m_second

  /**
   * Returns a list of views involved in general setup or custom animation logic.
   * In this case, it's empty.
   */
  override fun getAllViews(): List<View> = emptyList()

  /**
   * Defines the clickable views for which the fragment should listen to click events.
   */
  override fun initializeListeners(): List<View> = listOf(baseViewBinding.incrementCounterButton, baseViewBinding.toAnotherActivityButton)

  /**
   * Supplies the View.OnClickListener instance used across interactive views.
   */
  override fun getClickListener(): View.OnClickListener = this

  /**
   * Retrieves the ViewModel scoped to the parent Activity using `activityViewModels`.
   *
   * 🔄 This ensures both ActivityVMFirstFragment and ActivityVMSecondFragment
   * operate on the **same instance** of ActivityVMFirstViewModel.
   */
  override fun initializeViewModel() {
    val viewModel: ActivityVMFirstViewModel by activityViewModels()
    baseViewModel = viewModel
  }

  /**
   * Observes the `counter` StateFlow from the shared ViewModel and updates the UI
   * through Data Binding every time the value changes.
   *
   * This observation is lifecycle-safe and only active when the view is STARTED.
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
   * Handles click events for the UI.
   *
   * ➕ Increments the shared counter, which updates all observers including Fragment A.
   */
  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.incrementCounterButton -> {
        baseViewModel.increment()
      }
      baseViewBinding.toAnotherActivityButton -> {
        val intent = Intent(requireContext(), ScopeVMSecondActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(
          requireContext(),
          com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
          com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
        )
        startActivity(intent, options.toBundle())
      }
    }
  }
}
