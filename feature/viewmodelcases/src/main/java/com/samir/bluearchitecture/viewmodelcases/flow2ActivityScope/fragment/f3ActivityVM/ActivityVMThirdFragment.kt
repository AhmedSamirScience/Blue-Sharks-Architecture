package com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f3ActivityVM

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentActivityVMThirdBinding
import com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f1ActivityVM.ActivityVMFirstViewModel
import kotlinx.coroutines.launch

class ActivityVMThirdFragment : BaseFragment<ActivityVMFirstViewModel, FragmentActivityVMThirdBinding> () {
  override fun getContentView(): Int = R.layout.fragment_activity_v_m_third

  override fun getAllViews(): List<View> = emptyList()

  override fun initializeListeners(): List<View> = listOf(baseViewBinding.incrementCounterButton)

  override fun getClickListener(): View.OnClickListener = this

  override fun initializeViewModel() {
    val viewModel: ActivityVMFirstViewModel by activityViewModels()
    baseViewModel = viewModel
  }

  override fun subscribeObservers() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        baseViewModel.counter.collect { count ->
          baseViewBinding.myItem = count
        }
      }
    }
  }

  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.incrementCounterButton -> {
        baseViewModel.increment()
      }
    }
  }
}
