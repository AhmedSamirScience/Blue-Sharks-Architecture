package com.samir.bluearchitecture

import android.app.Application
import com.samir.bluearchitecture.presentation.activity.ActivityLifecycleLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
  override fun onCreate() {
    super.onCreate()
    registerActivityLifecycleCallbacks(ActivityLifecycleLogger)
  }
}
