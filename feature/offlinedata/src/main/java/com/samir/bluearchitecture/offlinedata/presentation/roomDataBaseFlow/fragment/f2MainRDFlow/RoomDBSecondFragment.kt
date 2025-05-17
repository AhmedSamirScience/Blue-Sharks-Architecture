package com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samir.bluearchitecture.data.main.remote.uiState.LiveDataResource
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.databinding.FragmentRoomDBSecondBinding
import com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow.adapter.LoginListAdapter
import com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow.clickEvent.LoginListClickListener
import com.samir.bluearchitecture.presentation.fragment.BaseFragment
import com.samir.bluearchitecture.ui.utils.logging.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class RoomDBSecondFragment :
  BaseFragment<RoomDBSecondViewModel, FragmentRoomDBSecondBinding>(),
  LoginListClickListener {

  //region initialization
  override fun getContentView(): Int = R.layout.fragment_room_d_b_second
  override fun getAllViews(): List<View> = listOf<View>(baseViewBinding.recyclerView, baseViewBinding.getDataBaseLoginListButton)
  override fun initializeListeners(): List<View> = listOf<View>(baseViewBinding.getDataBaseLoginListButton)
  override fun getClickListener(): View.OnClickListener = this
  override fun initializeViewModel() {
    val viewModel: RoomDBSecondViewModel by viewModels()
    baseViewModel = viewModel
  }
  override fun subscribeObservers() {
    getAllLoginDataObserver()
  }
  //endregion

  override fun onClick(v: View?) {
    when (v) {
      baseViewBinding.getDataBaseLoginListButton -> {
        baseViewModel.getAllLogins()
      }
    }
  }

  //region Recycler View
  private fun initLoginListAdapterRecycler(myList: List<LoginEntity>, recyclerView: RecyclerView) {
    recyclerView.layoutManager = GridLayoutManager(context, 1)

    val loginListAdapter = LoginListAdapter().apply {
      submitMyList(myList, this@RoomDBSecondFragment)
    }
    recyclerView.visibility = View.VISIBLE
    recyclerView.adapter = loginListAdapter
    recyclerView.startLayoutAnimation()
  }
  override fun onItemClicked(itemSelected: LoginEntity) {}

  //endregion
  //region Observers
  private fun getAllLoginDataObserver() {
    viewLifecycleOwner.lifecycleScope.launch {
      baseViewModel.getAllLoginsStateFlow.collect { result ->
        when (result) {
          is LiveDataResource.Success -> {
            Logger.d(fragment = this@RoomDBSecondFragment, message = "getAllLoginDataObserver (Success): ${result.data}")
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            result.data?.let { initLoginListAdapterRecycler(it, baseViewBinding.recyclerView) }
            enableAllViews() // 🧿 🧿 🧿 Enable all views after success message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Error -> {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            Logger.e(fragment = this@RoomDBSecondFragment, message = "getAllLoginDataObserver (Error): ${result.message}")
            enableAllViews() // 🧿 🧿 🧿 Enable all views after error message is displayed  ⛔ ⛔ ⛔
          }
          is LiveDataResource.Loading -> {
            Logger.v(fragment = this@RoomDBSecondFragment, message = "getAllLoginDataObserver (Loading): loading")
            disableAllViews() // 🧿 🧿 🧿 Disable all views while loading ⛔ ⛔ ⛔
          }
          is LiveDataResource.Idle -> {
            Logger.v(fragment = this@RoomDBSecondFragment, message = "getAllLoginDataObserver (Idle): idle state")
            enableAllViews() // 🧿 🧿 🧿 Enable all views when idle ⛔ ⛔ ⛔
          }
        }
      }
    }
  }
  //endregion
}
