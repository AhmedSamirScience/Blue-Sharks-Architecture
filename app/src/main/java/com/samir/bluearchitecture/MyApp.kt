package com.samir.bluearchitecture

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.BackoffPolicy
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.samir.bluearchitecture.presentation.activity.ActivityLifecycleLogger
import com.samir.bluearchitecture.workerManager.LoginWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

// Marks this Application class as the root of Hilt’s dependency graph
@HiltAndroidApp
class MyApp : Application(), Configuration.Provider {

  // Hilt will provide an instance of HiltWorkerFactory
  @Inject
  lateinit var workerFactory: HiltWorkerFactory

  // Configure WorkManager to use Hilt for injecting dependencies into workers
  override val workManagerConfiguration: Configuration
    get() = Configuration.Builder()
      .setWorkerFactory(workerFactory) // Plug in HiltWorkerFactory
      .build()

  override fun onCreate() {
    super.onCreate()

    // Register lifecycle logger for all activities (for debug, analytics, or navigation tracking)
    registerActivityLifecycleCallbacks(ActivityLifecycleLogger)

    // Schedule login-related background work
    enqueueApiWorker(this)
  }

  // Schedules a unique LoginWorker with the required input data
  private fun enqueueApiWorker(context: Context) {
    val inputData = workDataOf(
      "username" to "2379",
      "password" to "12345",
      "deviceToken" to "android",
    )

    // ✅ Build a one-time worker request for LoginWorker
    val request = OneTimeWorkRequestBuilder<LoginWorker>()
      .setInputData(inputData) // 📨 Pass the username, password, and deviceToken as input
      // 🔁 Configure retry behavior in case this worker fails and returns Result.retry()
      .setBackoffCriteria(
        BackoffPolicy.EXPONENTIAL, // ⏳ Retry delay will double each time (e.g., 15s → 30s → 60s ...)
        15, // 🔁 Initial retry delay is 15 seconds
        TimeUnit.SECONDS, // ⌛ Time unit for the delay is in seconds
      )
      // 🧱 Finally, build the request
      .build()

    // Enqueue the worker uniquely (prevents duplication by name)
    WorkManager.getInstance(context).enqueueUniqueWork(
      LoginWorker.UNIQUE_NAME, // Unique name used to avoid re-enqueuing the same work
      ExistingWorkPolicy.KEEP, // If work already exists, keep the current one and ignore this
      request, // The actual work request
    )
  }
}
