package com.samir.bluearchitecture.offlinedata.roomDataBaseFlow.activity

import android.view.LayoutInflater
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.databinding.ActivityMainRoomDataBaseBinding
import com.samir.bluearchitecture.presentation.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainRoomDataBaseActivity : BaseActivity() {
  override fun inflateBinding(inflater: LayoutInflater): ActivityMainRoomDataBaseBinding {
    return ActivityMainRoomDataBaseBinding.inflate(inflater)
  }

  override fun getAnimationPopUpView(): Pair<Int, Int> {
    return Pair(com.samir.bluearchitecture.ui.R.anim.slide_in_from_bottom, com.samir.bluearchitecture.ui.R.anim.slide_out_to_top)
  }

  override fun getContainer(): Int {
    return R.id.main_container
  }

  override fun initializeViews() {}
}
