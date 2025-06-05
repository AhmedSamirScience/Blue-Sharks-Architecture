package com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f1MainRDFlow

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.offlinedata.databinding.FragmentRoomDataBaseBinding
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.ui.helpers.input.TextInputEditTextUtils
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomDataBaseFragment : BaseFragment<RoomDataBaseViewModel, FragmentRoomDataBaseBinding>() {

  //region initialization
  override fun getContentView(): Int = R.layout.fragment_room_data_base
  override fun getAllViews(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton, baseViewBinding.insertDataBaseButton, baseViewBinding.edtUserId, baseViewBinding.edtUserName, baseViewBinding.tilUserName, baseViewBinding.tilUserId, baseViewBinding.navigateToSecondFragmentButton)
  override fun getViewIndicatorProgress(): View = requireActivity().findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.imgLoading)
  override fun initializeListeners(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton, baseViewBinding.insertDataBaseButton, baseViewBinding.navigateToSecondFragmentButton)
  override fun getClickListener(): View.OnClickListener = this
  override fun initializeViewModel() {
    val viewModel: RoomDataBaseViewModel by viewModels()
    baseViewModel = viewModel
  }
  override fun subscribeObservers() {
    loginObserver()
    cacheLoginObserver()
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
          !TextInputEditTextUtils.isEmptyTextInputEditText(baseViewBinding.edtUserName, getString(R.string.firstFragment_noData), requireContext())
        ) {
          Toast.makeText(requireContext(), "API calling started", Toast.LENGTH_SHORT).show()
          baseViewModel.cacheLogin(
            LoginEntity(
              userId = baseViewBinding.edtUserId.text.toString(),
              userName = baseViewBinding.edtUserName.text.toString(),
            ),
          ) }
      }
      baseViewBinding.navigateToSecondFragmentButton -> {
        val direction = RoomDataBaseFragmentDirections.actionRoomDataBaseFragmentToRoomDBSecondFragment()
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
            Logger.d(fragment = this@RoomDataBaseFragment, message = "loginObserver (Success): ${result.data}")
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            enableAllViews() // 🧿 🧿 🧿 Enable all views after success message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Error -> {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            Logger.e(fragment = this@RoomDataBaseFragment, message = "loginObserver (Error): ${result.message}")
            enableAllViews() // 🧿 🧿 🧿 Enable all views after error message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@RoomDataBaseFragment, message = "loginObserver (Loading): loading")
            disableAllViews() // 🧿 🧿 🧿 Disable all views while loading ⛔ ⛔ ⛔
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@RoomDataBaseFragment, message = "loginObserver (Idle): idle")
            enableAllViews() // 🧿 🧿 🧿 Enable all views when idle ⛔ ⛔ ⛔
          }
        }
      }
    }
  }
  private fun cacheLoginObserver() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.cacheLoginStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@RoomDataBaseFragment, message = "cacheLoginObserver (Success): inserted data ${result.data}")
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            enableAllViews() // 🧿 🧿 🧿 Enable all views after success message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Error -> {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            Logger.e(fragment = this@RoomDataBaseFragment, message = "cacheLoginObserver (Error): : ${result.message}")
            enableAllViews() // 🧿 🧿 🧿 Enable all views after error message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@RoomDataBaseFragment, message = "cacheLoginObserver (Loading)...")
            disableAllViews() // 🧿 🧿 🧿 Disable all views while loading ⛔ ⛔ ⛔
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@RoomDataBaseFragment, message = "cacheLoginObserver (Idle)")
            enableAllViews() // 🧿 🧿 🧿 Enable all views when idle ⛔ ⛔ ⛔
          }
        }
      }
    }
  }
  //endregion
}
