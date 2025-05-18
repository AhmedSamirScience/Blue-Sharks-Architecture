package com.samir.bluearchitecture.offlinedata.presentation.prefDataStoreFlow.fragment.secondScreen

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.databinding.FragmentPrefDataStoreSecBinding
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.ui.utils.logging.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PrefDataStoreSecFragment : BaseFragment<PrefDataStoreSecViewModel, FragmentPrefDataStoreSecBinding>() {

  //region initialization
  override fun getContentView(): Int = R.layout.fragment_pref_data_store_sec
  override fun getAllViews(): List<View> = listOf<View>(baseViewBinding.getDataBaseLoginListButton, baseViewBinding.txvServerTime, baseViewBinding.txvTimeZone, baseViewBinding.txvUserId)
  override fun getViewIndicatorProgress(): View = requireActivity().findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.imgLoading)
  override fun initializeListeners(): List<View> = listOf<View>(baseViewBinding.getDataBaseLoginListButton)
  override fun getClickListener(): View.OnClickListener = this
  override fun initializeViewModel() {
    val viewModel: PrefDataStoreSecViewModel by viewModels()
    baseViewModel = viewModel
  }
  override fun subscribeObservers() {
    observeGetServerTime()
    observeGetUserId()
  }

  //endregion
  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.getDataBaseLoginListButton -> {
        Toast.makeText(requireContext(), "API calling started", Toast.LENGTH_SHORT).show()
        baseViewModel.getUserId()
        baseViewModel.getServerTime()
      }
    }
  }

  //region Observers
  private fun observeGetServerTime() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.getServerTimeStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@PrefDataStoreSecFragment, message = "observeGetServerTime (Success): ${result.data}")
            Toast.makeText(requireContext(), "Server time: ${result.data?.serverTime}", Toast.LENGTH_SHORT).show()

            baseViewBinding.txvServerTime.text = "Server Time:" + result.data?.serverTime.toString()
            baseViewBinding.txvTimeZone.text = "Time Zone:" + result.data?.timezone.toString()
            enableAllViews()
          }
          is LiveDataResource.Error -> {
            Logger.e(fragment = this@PrefDataStoreSecFragment, message = "observeGetServerTime (Error): ${result.message}")
            Toast.makeText(requireContext(), "Error fetching server time", Toast.LENGTH_SHORT).show()
            enableAllViews()
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@PrefDataStoreSecFragment, message = "observeGetServerTime (Loading)")
            disableAllViews()
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@PrefDataStoreSecFragment, message = "observeGetServerTime (Idle)")
            enableAllViews()
          }
        }
      }
    }
  }

  private fun observeGetUserId() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.getUserIdStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@PrefDataStoreSecFragment, message = "observeGetUserId (Success): ${result.data}")
            Toast.makeText(requireContext(), "User ID: ${result.data}", Toast.LENGTH_SHORT).show()
            enableAllViews()
            baseViewBinding.txvUserId.text = "User Id:" + result.data.toString()
          }
          is LiveDataResource.Error -> {
            Logger.e(fragment = this@PrefDataStoreSecFragment, message = "observeGetUserId (Error): ${result.message}")
            Toast.makeText(requireContext(), "Failed to load User ID", Toast.LENGTH_SHORT).show()
            enableAllViews()
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@PrefDataStoreSecFragment, message = "observeGetUserId (Loading)")
            disableAllViews()
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@PrefDataStoreSecFragment, message = "observeGetUserId (Idle)")
            enableAllViews()
          }
        }
      }
    }
  }

  //endregion
}
