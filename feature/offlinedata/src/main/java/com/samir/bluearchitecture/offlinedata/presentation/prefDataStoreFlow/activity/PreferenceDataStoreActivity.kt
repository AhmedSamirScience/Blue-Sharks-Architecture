package com.samir.bluearchitecture.offlinedata.presentation.prefDataStoreFlow.activity

import android.view.LayoutInflater
import com.samir.bluearchitecture.offlinedata.R
import com.samir.bluearchitecture.offlinedata.databinding.ActivityPreferenceDataStoreBinding
import com.samir.bluearchitecture.presentation.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferenceDataStoreActivity : BaseActivity() {
  override fun inflateBinding(inflater: LayoutInflater): ActivityPreferenceDataStoreBinding {
    return ActivityPreferenceDataStoreBinding.inflate(inflater)
  }

  override fun getAnimationPopUpView(): Pair<Int, Int> {
    return Pair(com.samir.bluearchitecture.ui.R.anim.slide_in_from_bottom, com.samir.bluearchitecture.ui.R.anim.slide_out_to_top)
  }

  override fun getContainer(): Int {
    return R.id.main_container
  }

  override fun initializeViews() {}
}
