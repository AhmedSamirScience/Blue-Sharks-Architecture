package com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.fragment.f2ActivityVM

import android.view.View
import androidx.fragment.app.viewModels
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentActivityVMSecondBinding

class ActivityVMSecondFragment : BaseFragment<ActivityVMSecondViewModel, FragmentActivityVMSecondBinding>() {
  override fun getContentView(): Int {
    return R.layout.fragment_activity_v_m_second
  }

  override fun getAllViews(): List<View> {
    return emptyList()
  }

  override fun initializeListeners(): List<View> {
    return emptyList()
  }

  override fun getClickListener(): View.OnClickListener {
    return this
  }

  override fun initializeViewModel() {
    val viewModel: ActivityVMSecondViewModel by viewModels()
    baseViewModel = viewModel
  }

  override fun subscribeObservers() {}

  override fun onClick(v: View?) {}
}
