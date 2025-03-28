package com.samir.bluearchitecture

import android.app.Application
import com.samir.bluearchitecture.presentation.activity.ActivityLifecycleLogger

class MyApp : Application() {
  override fun onCreate() {
    super.onCreate()

    // Only enable lifecycle logs in debug builds
    if (BuildConfig.DEBUG) {
      registerActivityLifecycleCallbacks(ActivityLifecycleLogger)
    }
  }
}
