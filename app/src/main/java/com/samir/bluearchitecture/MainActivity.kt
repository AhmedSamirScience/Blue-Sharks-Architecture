package com.samir.bluearchitecture

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.databinding.ActivityMainBinding
import com.samir.bluearchitecture.offlinedata.MainORActivity
import com.samir.bluearchitecture.remotedata.main.MainRDActivity
import com.samir.bluearchitecture.viewmodelcases.MainVMActivity

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.sharedViewModelButton.setOnClickListener {
      val intent = Intent(this, MainVMActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_top,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_bottom,
      )
      startActivity(intent, options.toBundle())
    }

    binding.remoteDataButton.setOnClickListener {
      val intent = Intent(this, MainRDActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_bottom,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_top,
      )
      startActivity(intent, options.toBundle())
    }

    binding.offlineDataButton.setOnClickListener {
      val intent = Intent(this, MainORActivity::class.java)
      val options = ActivityOptions.makeCustomAnimation(
        this,
        com.samir.bluearchitecture.ui.R.anim.slide_in_from_bottom,
        com.samir.bluearchitecture.ui.R.anim.slide_out_to_top,
      )
      startActivity(intent, options.toBundle())
    }
  }
}
