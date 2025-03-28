package com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f1ActivityVM

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentActivityVMFirstBinding

class ActivityVMFirstFragment : BaseFragment<ActivityVMFirstViewModel, FragmentActivityVMFirstBinding>() {
  override fun getContentView(): Int {
    return R.layout.fragment_activity_v_m_first
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
    val viewModel: ActivityVMFirstViewModel by viewModels()
    baseViewModel = viewModel
  }

  override fun subscribeObservers() {}

  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.firstFragmentButton -> {
        val direction = ActivityVMFirstFragmentDirections.actionActivityVMFirstFragmentToActivityVMSecondFragment()
        findNavController().navigate(direction)
      }
    }
  }
}
