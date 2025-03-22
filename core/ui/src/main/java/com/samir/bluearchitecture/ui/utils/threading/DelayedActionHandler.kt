package com.samir.bluearchitecture.ui.utils.threading

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.samir.bluearchitecture.ui.constants.Constants

/**
 * Helper class for performing delayed actions on the main thread.
 * @param handlerName A name to identify the handler instance.
 */
class DelayedActionHandler(private var handlerName: String) {
  private var handler: Handler = Handler(Looper.getMainLooper())
  private lateinit var runner: Runnable

  /**
   * Creates a Runnable with the specified action.
   * @param action The action to be executed.
   */
  fun createActionRunnableWithYourCode(action: (() -> Unit)?) {
    runner = Runnable {
      action?.invoke()
    }
    Log.i(Constants.TAG_MESSAGE, "($handlerName) -> Created action runnable with code")
  }

  /**
   * Starts a delayed action with the specified delay.
   * @param delayMillis The delay in milliseconds before the action is executed.
   */
  fun startDelayedAction(delayMillis: Long) {
    handler.postDelayed(runner, delayMillis)
    Log.i(
      Constants.TAG_MESSAGE,
      "($handlerName) -> Started delayed action with delay $delayMillis milliseconds",
    )
  }

  /**
   * Removes the previously scheduled delayed action.
   */
  fun removeDelayedAction() {
    handler.removeCallbacks(runner)
    Log.i(Constants.TAG_MESSAGE, "($handlerName) -> Removed delayed action")
  }
}
