package com.samir.bluearchitecture.viewmodelcases.activityScopeFlow.activity

import android.view.LayoutInflater
import com.samir.bluearchitecture.presentation.activity.BaseActivity
import com.samir.bluearchitecture.viewmodelcases.R
import com.samir.bluearchitecture.viewmodelcases.databinding.ActivityScopeVmactivityBinding

class ScopeVMActivity : BaseActivity() {
  override fun inflateBinding(inflater: LayoutInflater): ActivityScopeVmactivityBinding {
    return ActivityScopeVmactivityBinding.inflate(inflater)
  }

  override fun getAnimationPopUpView(): Pair<Int, Int> {
    return Pair(com.samir.bluearchitecture.ui.R.anim.slide_in_from_bottom, com.samir.bluearchitecture.ui.R.anim.slide_out_to_top)
  }

  override fun getContainer(): Int {
    return R.id.main_container
  }

  override fun initializeViews() {}
}
