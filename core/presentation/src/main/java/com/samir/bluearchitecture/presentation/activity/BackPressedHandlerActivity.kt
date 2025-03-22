package com.samir.bluearchitecture.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.samir.bluearchitecture.ui.utils.logging.Logger

/**
 * Abstract class for handling back press events.
 * Activities that need custom back press handling should extend this class.
 */
abstract class BackPressedHandlerActivity : AppCompatActivity() {

  protected lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setUpBackPressedHandling()
  }

  /**
   * Gets the animation for onBackPressed handling.
   * Subclasses must override this method to provide animation resource IDs.
   * @return Pair of animation resource IDs (Enter Animation, Exit Animation).
   */
  protected abstract fun getAnimationPopUpView(): Pair<Int, Int>

  /**
   * Sets up handling of onBackPressed event.
   * Finishes the activity and executes custom onBackPressed action.
   */
  private fun setUpBackPressedHandling() {
    val callback = object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        Log.e("handleOnBackPressed", "out")
        if (!navController.popBackStack()) {
          Logger.i(activity = this@BackPressedHandlerActivity, message = "BackPressed Inside (Activity)🔙: If there's no previous fragment in the back stack, finish the activity  (navController.popBackStack())")
          // If there's no previous fragment in the back stack, finish the activity
          finish()
        }
        onBackPressedAction()
      }
    }
    onBackPressedDispatcher.addCallback(this, callback)
  }

  /**
   * Executes onBackPressed action.
   * Subclasses can override this method to provide custom behavior on back press.
   */
  private fun onBackPressedAction() {
    overridePendingTransition(getAnimationPopUpView().first, getAnimationPopUpView().second)
    onBackPressedHandle()
  }

  /**
   * Hook method for subclasses to execute additional actions on back press.
   * Subclasses can override this method to provide custom behavior.
   */
  protected open fun onBackPressedHandle() {}
}
