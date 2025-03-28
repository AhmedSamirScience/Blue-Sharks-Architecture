package com.samir.bluearchitecture.viewmodelcases

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.viewmodelcases.databinding.ActivityMainVmactivityBinding
import com.samir.bluearchitecture.viewmodelcases.flow1Basic.activity.BasicVMActivity
import com.samir.bluearchitecture.viewmodelcases.flow2ActivityScope.activity.ScopeVMActivity
import com.samir.bluearchitecture.viewmodelcases.flow3NavGraphScope.activity.NavGraphVMActivity

class MainVMActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainVmactivityBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainVmactivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.basicViewModelButton.setOnClickListener {
      val intent = Intent(this, BasicVMActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
      )
      startActivity(intent, options.toBundle())
    }
    binding.sharedViewModelActivitiesButton.setOnClickListener {
      val intent = Intent(this, ScopeVMActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
      )
      startActivity(intent, options.toBundle())
    }
    binding.sharedViewModelNavGraphButton.setOnClickListener {
      val intent = Intent(this, NavGraphVMActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
      )
      startActivity(intent, options.toBundle())
    }
  }
}
