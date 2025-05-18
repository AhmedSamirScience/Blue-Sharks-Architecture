package com.samir.bluearchitecture.offlinedata.presentation.prefDataStoreFlow.fragment.firstScreen

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.databinding.FragmentPrefDataStoreFirstBinding
import com.samir.bluearchitecture.offlinedata.domain.model.ServerTime
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.ui.helpers.input.TextInputEditTextUtils
import com.samir.bluearchitecture.ui.utils.logging.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PrefDataStoreFirstFragment : BaseFragment<PrefDataStoreFirstViewModel, FragmentPrefDataStoreFirstBinding>() {

  //region initialization
  override fun getContentView(): Int = R.layout.fragment_pref_data_store_first
  override fun getAllViews(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton, baseViewBinding.insertDataBaseButton, baseViewBinding.edtUserId, baseViewBinding.edtServerTime, baseViewBinding.tilServerTime, baseViewBinding.edtTimeZone, baseViewBinding.tilTimeZone, baseViewBinding.tilUserId, baseViewBinding.navigateToSecondFragmentButton)
  override fun getViewIndicatorProgress(): View = requireActivity().findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.imgLoading)
  override fun initializeListeners(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton, baseViewBinding.insertDataBaseButton, baseViewBinding.navigateToSecondFragmentButton)
  override fun getClickListener(): View.OnClickListener = this
  override fun initializeViewModel() {
    val viewModel: PrefDataStoreFirstViewModel by viewModels()
    baseViewModel = viewModel
  }
  override fun subscribeObservers() {
    loginObserver()
    saveServerTimeObserver()
    saveUserId()
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
      baseViewBinding.insertDataBaseButton -> {
        if (!TextInputEditTextUtils.isEmptyTextInputEditText(baseViewBinding.edtUserId, getString(R.string.firstFragment_noData), requireContext()) &&
          !TextInputEditTextUtils.isEmptyTextInputEditText(baseViewBinding.edtServerTime, getString(R.string.firstFragment_noData), requireContext()) &&
          !TextInputEditTextUtils.isEmptyTextInputEditText(baseViewBinding.edtTimeZone, getString(R.string.firstFragment_noData), requireContext())
        ) {
          Toast.makeText(requireContext(), "API calling started", Toast.LENGTH_SHORT).show()
          baseViewModel.saveServerTime(
            ServerTime(
              serverTime = baseViewBinding.edtServerTime.text.toString(),
              timezone = baseViewBinding.edtTimeZone.text.toString(),
            ),
          )
          baseViewModel.saveUserId(
            baseViewBinding.edtUserId.text.toString(),
          )
        }
      }
      baseViewBinding.navigateToSecondFragmentButton -> {
        val direction = PrefDataStoreFirstFragmentDirections.actionPrefDataStoreFirstFragmentToPrefDataStoreSecFragment()
        findNavController().navigate(direction)
      }
    }
  }

  //region Observers
  private fun loginObserver() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.loginStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@PrefDataStoreFirstFragment, message = "loginObserver (Success): ${result.data}")
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            enableAllViews() // 🧿 🧿 🧿 Enable all views after success message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Error -> {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            Logger.e(fragment = this@PrefDataStoreFirstFragment, message = "loginObserver (Error): ${result.message}")
            enableAllViews() // 🧿 🧿 🧿 Enable all views after error message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@PrefDataStoreFirstFragment, message = "loginObserver (Loading): loading")
            disableAllViews() // 🧿 🧿 🧿 Disable all views while loading ⛔ ⛔ ⛔
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@PrefDataStoreFirstFragment, message = "loginObserver (Idle): idle")
            enableAllViews() // 🧿 🧿 🧿 Enable all views when idle ⛔ ⛔ ⛔
          }
        }
      }
    }
  }
  private fun saveServerTimeObserver() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.saveServerTimeState.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@PrefDataStoreFirstFragment, message = "saveServerTimeObserver (Success)")
            Toast.makeText(requireContext(), "Server time saved successfully", Toast.LENGTH_SHORT).show()
            enableAllViews()
          }
          is LiveDataResource.Error -> {
            Logger.e(fragment = this@PrefDataStoreFirstFragment, message = "saveServerTimeObserver (Error): ${result.message}")
            Toast.makeText(requireContext(), "Failed to save server time", Toast.LENGTH_SHORT).show()
            enableAllViews()
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@PrefDataStoreFirstFragment, message = "saveServerTimeObserver (Loading)")
            disableAllViews()
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@PrefDataStoreFirstFragment, message = "saveServerTimeObserver (Idle)")
            enableAllViews()
          }
        }
      }
    }
  }

  private fun saveUserId() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.saveUserIdState.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@PrefDataStoreFirstFragment, message = "saveUserIdObserver (Success)")
            Toast.makeText(requireContext(), "User ID saved successfully", Toast.LENGTH_SHORT).show()
            enableAllViews()
          }
          is LiveDataResource.Error -> {
            Logger.e(fragment = this@PrefDataStoreFirstFragment, message = "saveUserIdObserver (Error): ${result.message}")
            Toast.makeText(requireContext(), "Failed to save User ID", Toast.LENGTH_SHORT).show()
            enableAllViews()
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@PrefDataStoreFirstFragment, message = "saveUserIdObserver (Loading)")
            disableAllViews()
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@PrefDataStoreFirstFragment, message = "saveUserIdObserver (Idle)")
            enableAllViews()
          }
        }
      }
    }
  }
  //endregion
}
