package com.samir.bluearchitecture.ui.utils.logging

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

object Logger {

  private const val TAG = "MyAppLogger" // Default tag for log messages

  // Helper function to format the log message with Activity, Fragment, ViewModel, and Use Case names
  private fun formatMessage(activity: AppCompatActivity?, fragment: Fragment?, viewModel: ViewModel?, useCase: Class<*>?, message: String): String {
    val activityName = activity?.javaClass?.simpleName ?.let {
      "🚢 $it"
    }
    val fragmentName = fragment?.javaClass?.simpleName?.let {
      "🏀 $it"
    }
    val viewModelName = viewModel?.javaClass?.simpleName?.let {
      "🪟 $it"
    }
    val useCaseName = useCase?.simpleName?.let {
      "🎲 $it"
    }

    val parts = listOfNotNull(activityName, fragmentName, viewModelName, useCaseName)
    return buildString {
      append("[")
      append(parts.joinToString(" · ")) // Optional separator between parts
      append("]: ")
      append(message)
    }
  }

  // Logs debug messages
  fun d(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
    // if (BuildConfig.DEBUG)
    Log.d(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
  }

  // Logs info messages
  fun i(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
    // if (BuildConfig.DEBUG)
    Log.i(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
  }

  // Logs warning messages
  fun w(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
    // if (BuildConfig.DEBUG)
    Log.w(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
  }

  fun wtf(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
    // if (BuildConfig.DEBUG)
    Log.wtf(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
  }

  // Logs error messages
  fun e(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String, throwable: Throwable? = null) {
    if (throwable != null) {
      // if (BuildConfig.DEBUG)
      Log.e(TAG, formatMessage(activity, fragment, viewModel, useCase, message), throwable)
    } else {
      // if (BuildConfig.DEBUG)
      Log.e(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
    }
  }

  // Logs verbose messages
  fun v(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
    // if (BuildConfig.DEBUG)
    Log.v(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
  }

  // Logs to a custom destination, e.g., a file or remote server
  fun customLog(destination: (String, String) -> Unit, activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
    // if (BuildConfig.DEBUG)
    destination(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
  }
}

/**
 * (this for disabling the logger)
 * object Logger {
 *
 *     private const val TAG = "MyAppLogger"
 *
 *     private fun formatMessage(
 *         activity: AppCompatActivity?,
 *         fragment: Fragment?,
 *         viewModel: ViewModel?,
 *         useCase: Class<*>?,
 *         message: String
 *     ): String {
 *         val activityName = activity?.javaClass?.simpleName ?: ""
 *         val fragmentName = fragment?.javaClass?.simpleName ?: ""
 *         val viewModelName = viewModel?.javaClass?.simpleName ?: ""
 *         val useCaseName = useCase?.simpleName ?: ""
 *         return "[$activityName$fragmentName$viewModelName$useCaseName]: $message"
 *     }
 *
 *     fun d(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
 *         if (BuildConfig.LOG_ENABLED) Log.d(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
 *     }
 *
 *     fun i(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
 *         if (BuildConfig.LOG_ENABLED) Log.i(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
 *     }
 *
 *     fun w(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
 *         if (BuildConfig.LOG_ENABLED) Log.w(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
 *     }
 *
 *     fun e(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String, throwable: Throwable? = null) {
 *         if (BuildConfig.LOG_ENABLED) {
 *             if (throwable != null) {
 *                 Log.e(TAG, formatMessage(activity, fragment, viewModel, useCase, message), throwable)
 *             } else {
 *                 Log.e(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
 *             }
 *         }
 *     }
 *
 *     fun v(activity: AppCompatActivity? = null, fragment: Fragment? = null, viewModel: ViewModel? = null, useCase: Class<*>? = null, message: String) {
 *         if (BuildConfig.LOG_ENABLED) Log.v(TAG, formatMessage(activity, fragment, viewModel, useCase, message))
 *     }
 * }
 *
 */

/**
 * Example Scenario in Activity, Fragment, and ViewModel
 * Here’s how you might use the Logger in an Activity, Fragment, and ViewModel together:
 *
 * (In MainActivity)
 * class MainActivity : AppCompatActivity() {
 *
 *     private lateinit var viewModel: ExampleViewModel
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_main)
 *
 *         viewModel = ViewModelProvider(this).get(ExampleViewModel::class.java)
 *
 *         Logger.d(activity = this, message = "Activity started")
 *
 *         val fragment = ExampleFragment()
 *         supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
 *
 *         viewModel.performAction()
 *     }
 * }
 *
 *
 * (In ExampleFragment)
 * class ExampleFragment : Fragment() {
 *
 *     private lateinit var viewModel: ExampleViewModel
 *
 *     override fun onActivityCreated(savedInstanceState: Bundle?) {
 *         super.onActivityCreated(savedInstanceState)
 *
 *         viewModel = ViewModelProvider(requireActivity()).get(ExampleViewModel::class.java)
 *
 *         Logger.d(activity = activity as? AppCompatActivity, fragment = this, message = "Fragment created")
 *         viewModel.performAction()
 *     }
 * }
 *
 *
 * (In ExampleUseCase)
 * class FetchUserProfileUseCase @Inject constructor(
 *     private val repository: UserRepository,
 *     private val resourceProvider: ResourceProvider
 * ) {
 *     operator fun invoke(userId: String): Flow<RequestResource<UserProfile>> = flow {
 *         try {
 *             // Log the start of the use case execution
 *             Logger.i(useCase = this@FetchUserProfileUseCase::class.java, message = "Fetching user profile for userId: $userId")
 *         } catch (e: Exception) {
 *             Logger.e(useCase = this@FetchUserProfileUseCase::class.java, message = "Unexpected error occurred while fetching user profile for userId: $userId", throwable = e)
 *             emit(RequestResource.Error(message = resourceProvider.getString(R.string.error_unexpected_exception)))
 *         }
 *     }
 * }
 *
 * (Example Log Output)
 * With the above setup, the log output might look like this:
 *
 * E/MyAppLogger: [UnknownActivity][NoFragment][NoViewModel][UseCase][If you want more now it is easy]: HttpException: Unauthorized
 * E/MyAppLogger: [UnknownActivity][NoFragment][NoViewModel][UseCase][If you want more now it is easy]: SocketTimeoutException: Timeout reached
 * E/MyAppLogger: [UnknownActivity][NoFragment][NoViewModel][UseCase][If you want more now it is easy]: IOException: Network error
 * E/MyAppLogger: [UnknownActivity][NoFragment][NoViewModel][UseCase][If you want more now it is easy]: Unexpected Exception: Some unexpected error
 * I/MyAppLogger: [UnknownActivity][NoFragment][NoViewModel][UseCase][If you want more now it is easy]: Successfully fetched server time
 *
 *
 * Summary
 * This updated Logger class now supports logging from ViewModel instances in addition to Activity and Fragment classes.
 * The logger can automatically include the names of the Activity, Fragment, and ViewModel in the log messages, making it easier to track where the logs are coming from.
 */
