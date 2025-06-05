package com.samir.bluearchitecture.remotedata.main.flow1Basic.fragment.f1BasicRD

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.remotedata.R
import com.samir.bluearchitecture.remotedata.databinding.FragmentBasicRDFirstBinding
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasicRDFirstFragment : BaseFragment<BasicRDFirstViewModel, FragmentBasicRDFirstBinding>() {

  //region initialization
  override fun getContentView(): Int = R.layout.fragment_basic_r_d_first
  override fun getAllViews(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton, baseViewBinding.navigateToSecondFragmentButton)
  override fun getViewIndicatorProgress(): View = requireActivity().findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.imgLoading)
  override fun initializeListeners(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton, baseViewBinding.navigateToSecondFragmentButton)
  override fun getClickListener(): View.OnClickListener = this
  override fun initializeViewModel() {
    val viewModel: BasicRDFirstViewModel by viewModels()
    baseViewModel = viewModel
  }
  override fun subscribeObservers() {
    loginObserver()
  }

  //endregion
  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.fireBasicApiButton -> {
        baseViewModel.login(
          LoginRq(
            username = "2379",
            password = "12345",
            deviceToken = "12345",
          ),
        )
        Toast.makeText(requireContext(), "API calling started", Toast.LENGTH_SHORT).show()
      }
      baseViewBinding.navigateToSecondFragmentButton -> {
        val action = BasicRDFirstFragmentDirections.actionBasicRDFirstFragmentToBasicRmoteDSecondFragment()
        findNavController().navigate(action)
      }
    }
  }

  //region Observers
  private fun loginObserver() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.loginStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@BasicRDFirstFragment, message = "loginObserver (Success): ${result.data}")
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            enableAllViews() // 🧿 🧿 🧿 Enable all views after success message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Error -> {
            Logger.e(fragment = this@BasicRDFirstFragment, message = "loginObserver (Error): ${result.message}")
            enableAllViews() // 🧿 🧿 🧿 Enable all views after error message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@BasicRDFirstFragment, message = "loginObserver (Loading): loading state")
            disableAllViews() // 🧿 🧿 🧿 Disable all views while loading ⛔ ⛔ ⛔
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@BasicRDFirstFragment, message = "loginObserver (Idle): idle state")
            enableAllViews() // 🧿 🧿 🧿 Enable all views when idle ⛔ ⛔ ⛔
          }
        }
      }
    }
  }
  //endregion
}
