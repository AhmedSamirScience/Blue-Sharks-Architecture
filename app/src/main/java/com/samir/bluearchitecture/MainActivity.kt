package com.samir.bluearchitecture

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.databinding.ActivityMainBinding
import com.samir.bluearchitecture.viewmodelcases.MainViewModelActivity

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.sharedViewModelButton.setOnClickListener {
      val intent = Intent(this, MainViewModelActivity::class.java)
      startActivity(intent)
    }
  }
}
