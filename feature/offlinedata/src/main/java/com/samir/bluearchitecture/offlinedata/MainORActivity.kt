package com.samir.bluearchitecture.offlinedata

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.offlinedata.databinding.ActivityMainOractivityBinding
import com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.activity.MainRoomDataBaseActivity

class MainORActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainOractivityBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainOractivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.basicRemoteDataButton.setOnClickListener {
      val intent = Intent(this, MainRoomDataBaseActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
      )
      startActivity(intent, options.toBundle())
    }
  }
}
