package com.samir.bluearchitecture.presentation.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samir.bluearchitecture.ui.utils.logging.Logger

/**
 * Logs lifecycle events of all activities using the custom Logger class.
 * Automatically registers when you initialize it in your Application class.
 */
object ActivityLifecycleLogger : Application.ActivityLifecycleCallbacks {

  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    Logger.i(activity = activity as? AppCompatActivity, message = "🟢 onCreate")
  }

  override fun onActivityStarted(activity: Activity) {
    Logger.i(activity = activity as? AppCompatActivity, message = "🟡 onStart")
  }

  override fun onActivityResumed(activity: Activity) {
    Logger.i(activity = activity as? AppCompatActivity, message = "✅ onResume")
  }

  override fun onActivityPaused(activity: Activity) {
    Logger.i(activity = activity as? AppCompatActivity, message = "⚠️ onPause")
  }

  override fun onActivityStopped(activity: Activity) {
    Logger.i(activity = activity as? AppCompatActivity, message = "🛑 onStop")
  }

  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    Logger.i(activity = activity as? AppCompatActivity, message = "💾 onSaveInstanceState")
  }

  override fun onActivityDestroyed(activity: Activity) {
    Logger.i(activity = activity as? AppCompatActivity, message = "❌ onDestroy")
  }
}
