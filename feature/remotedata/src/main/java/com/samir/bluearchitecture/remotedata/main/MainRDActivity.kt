package com.samir.bluearchitecture.remotedata.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.remotedata.databinding.ActivityMainRdactivityBinding
import com.samir.bluearchitecture.remotedata.main.flow1Basic.activity.BasicRDActivity

class MainRDActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainRdactivityBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainRdactivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.basicRemoteDataButton.setOnClickListener {
      val intent = Intent(this, BasicRDActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
      )
      startActivity(intent, options.toBundle())
    }
  }
}
