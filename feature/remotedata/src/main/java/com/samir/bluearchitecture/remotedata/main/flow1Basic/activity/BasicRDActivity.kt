package com.samir.bluearchitecture.remotedata.main.flow1Basic.activity

import android.view.LayoutInflater
import com.samir.bluearchitecture.presentation.activity.BaseActivity
import com.samir.bluearchitecture.remotedata.R
import com.samir.bluearchitecture.remotedata.databinding.ActivityBasicRdactivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicRDActivity : BaseActivity() {
  override fun inflateBinding(inflater: LayoutInflater): ActivityBasicRdactivityBinding {
    return ActivityBasicRdactivityBinding.inflate(inflater)
  }

  override fun getAnimationPopUpView(): Pair<Int, Int> {
    return Pair(com.samir.bluearchitecture.ui.R.anim.slide_in_from_bottom, com.samir.bluearchitecture.ui.R.anim.slide_out_to_top)
  }

  override fun getContainer(): Int {
    return R.id.main_container
  }

  override fun initializeViews() {}
}
