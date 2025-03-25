package com.samir.bluearchitecture.viewmodelcases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.viewmodelcases.databinding.ActivityMainViewModelBinding

class MainViewModelActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainViewModelBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_view_model)
  }
}
