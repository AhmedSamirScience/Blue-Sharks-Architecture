package com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.fragment.f2NavGraphVM

import android.view.View
import androidx.fragment.app.viewModels
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.FragmentNavGraphVMSecondBinding

class NavGraphVMSecondFragment : BaseFragment<NavGraphVMSecondViewModel, FragmentNavGraphVMSecondBinding>() {
  override fun getContentView(): Int {
    return R.layout.fragment_nav_graph_v_m_second
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
    val viewModel: NavGraphVMSecondViewModel by viewModels()
    baseViewModel = viewModel
  }

  override fun subscribeObservers() {}

  override fun onClick(v: View?) {}
}
