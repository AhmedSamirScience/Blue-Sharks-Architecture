package com.samir.bluearchitecture.viewmodelcases.basicFlow.fragment.second

import android.view.View
import androidx.fragment.app.viewModels
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentConfigChangeVMBinding

class ConfigChangeVMFragment : BaseFragment<ConfigChangeVMViewModel, FragmentConfigChangeVMBinding>() {
  override fun getContentView(): Int {
    return R.layout.fragment_config_change_v_m
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
    val viewModel: ConfigChangeVMViewModel by viewModels()
    baseViewModel = viewModel
  }

  override fun subscribeObservers() {}

  override fun onClick(v: View?) {}
}
