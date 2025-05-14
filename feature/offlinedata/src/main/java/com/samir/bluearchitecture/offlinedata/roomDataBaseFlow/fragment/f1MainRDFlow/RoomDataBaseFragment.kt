package com.samir.bluearchitecture.offlinedata.roomDataBaseFlow.fragment.f1MainRDFlow

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.databinding.FragmentRoomDataBaseBinding
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomDataBaseFragment : BaseFragment<RoomDataBaseViewModel, FragmentRoomDataBaseBinding>() {

  //region initialization
  override fun getContentView(): Int = R.layout.fragment_room_data_base
  override fun getAllViews(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton)
  override fun getViewIndicatorProgress(): View = requireActivity().findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.imgLoading)
  override fun initializeListeners(): List<View> = listOf<View>(baseViewBinding.fireBasicApiButton)
  override fun getClickListener(): View.OnClickListener = this
  override fun initializeViewModel() {
    val viewModel: RoomDataBaseViewModel by viewModels()
    baseViewModel = viewModel
  }
  override fun subscribeObservers() {
    loginObserver()
  }

  //endregion
  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.fireBasicApiButton -> {
       /* baseViewModel.login(
          LoginRq(
            username = "2379",
            password = "12345",
            deviceToken = "12345",
          ),
        )*/
        Toast.makeText(requireContext(), "API calling started", Toast.LENGTH_SHORT).show()
      }
    }
  }

  //region Observers
  private fun loginObserver() {
    /*viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.loginStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@BasicRDFirstFragment, message = "loginObserver (i w): ${result.data}")
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            enableAllViews() // 🧿 🧿 🧿 Enable all views after success message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Error -> {
            Logger.e(fragment = this@BasicRDFirstFragment, message = "loginObserver (Error): ${result.message}")
            enableAllViews() // 🧿 🧿 🧿 Enable all views after error message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@BasicRDFirstFragment, message = "loginObserver (Loading): loading")
            disableAllViews() // 🧿 🧿 🧿 Disable all views while loading ⛔ ⛔ ⛔
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@BasicRDFirstFragment, message = "loginObserver (Idle): idle state")
            enableAllViews() // 🧿 🧿 🧿 Enable all views when idle ⛔ ⛔ ⛔
          }
        }
      }
    }*/
  }
  //endregion
}
