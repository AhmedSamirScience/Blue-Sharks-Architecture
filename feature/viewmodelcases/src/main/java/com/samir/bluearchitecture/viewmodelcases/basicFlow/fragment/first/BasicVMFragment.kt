package com.samir.bluearchitecture.viewmodelcases.basicFlow.fragment.first

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentBasicVMBinding

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

  override fun subscribeObservers() {}

  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.firstFragmentButton -> {
        val direction = BasicVMFragmentDirections.actionBasicVMFragmentToConfigChangeVMFragment()
        findNavController().navigate(direction)
      }
    }
  }
}
