package com.samir.bluearchitecture.workerManager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.remotedata.main.data.remote.dataTransferObject.firstScreen.LoginRq
import com.samir.bluearchitecture.remotedata.main.domain.useCase.LoginUseCase
import com.samir.bluearchitecture.utils.logging.Logger
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

// ✅ Annotates the worker class for Hilt injection
@HiltWorker
class LoginWorker @AssistedInject constructor(
  @Assisted appContext: Context, // Application context provided at runtime
  @Assisted workerParams: WorkerParameters, // WorkManager parameters (e.g. input data, attempt count)
  private val loginUseCase: LoginUseCase, // Injected use case to perform login operation
) : CoroutineWorker(appContext, workerParams) { // Base class that supports suspend functions

  override suspend fun doWork(): Result {
    // ℹ️ Logs when the worker starts execution
    Logger.i(worker = LoginWorker::class, message = "doWork: STARTED")

    // 🔐 Extract required input fields from the worker's inputData
    val username = inputData.getString("username") ?: return Result.failure()
    val password = inputData.getString("password") ?: return Result.failure()
    val deviceToken = inputData.getString("deviceToken") ?: "android"

    // 🚀 Executes login use case with collected inputs
    val result = loginUseCase.run(
      LoginRq(
        username = username,
        password = password,
        deviceToken = deviceToken,
      ),
    )

    // ✅ Handles the outcome and schedules the next job if needed
    return when (result) {
      is OutCome.Success -> {
        Logger.d(worker = LoginWorker::class, message = "SUCCESS → chemistID = ${result.data.chemistID}, error = ${result.data.errorMessage}")
        scheduleNext(applicationContext, inputData) // 🔁 Reschedule the next run
        Result.success()
      }
      is OutCome.Error -> {
        Logger.e(worker = LoginWorker::class, message = "ERROR → message = ${result.errorMessage().messageMapper}")
        /*if (runAttemptCount < 10) {
          return Result.retry() // ⛔ Retry logic on known errors
        } else {
          return Result.failure()
        }*/
        return Result.retry() // 🟡 Try again later (up to 10 times)
      }
      is OutCome.Empty -> {
        Logger.e(worker = LoginWorker::class, message = "EMPTY → Login result was empty")
        /*if (runAttemptCount < 10) {
          return Result.retry() // ⛔ Retry logic on known errors
        } else {
          return Result.failure()
        }*/
        return Result.retry() // 🟡 Try again later (up to 10 times)
      }
      else -> {
        Logger.e(worker = LoginWorker::class, message = "UNKNOWN → Login result was unknown")
        Result.failure()
      }
    }
  }

  companion object {
    // 🏷 Used to uniquely identify and manage the scheduled work
    const val UNIQUE_NAME = "login_worker"

    // ⏲️ Schedules this worker to re-run after a fixed delay
    fun scheduleNext(context: Context, inputData: Data) {
      val nextRequest = OneTimeWorkRequestBuilder<LoginWorker>()
        .setInputData(inputData) // Reuse input parameters
        .setInitialDelay(2, TimeUnit.MINUTES) // ⏳ Wait before re-running
        .setBackoffCriteria( // 🔁 Configure retry delay behavior
          BackoffPolicy.EXPONENTIAL, // or LINEAR
          15,
          TimeUnit.SECONDS, // Start retry delay at 15 seconds
        )
        .build()

      // 🔒 Enqueue the worker using a unique name to avoid duplication
      WorkManager.getInstance(context).enqueueUniqueWork(
        UNIQUE_NAME,
        ExistingWorkPolicy.REPLACE, // Replace if already exists
        nextRequest,
      )

      Logger.i(worker = LoginWorker::class, message = "📆 Scheduled next LoginWorker in 2 minutes.")
    }
  }
}
