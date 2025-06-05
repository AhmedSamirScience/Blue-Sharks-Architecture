package com.samir.bluearchitecture.ui.animations

import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.samir.bluearchitecture.utils.constants.Constants

/**
 * Helper class for managing view animations.
 * @param context The application context.
 */
class AnimationHelper(private val context: Context) {

  /**
   * Starts the specified animation for the given view.
   * @param view The view to animate.
   * @param animationResource The resource ID of the animation.
   * @param strMessage Optional message to log when animation starts.
   * @param onAnimationEnd Callback to be executed when animation ends.
   */
  fun startAnimation(
    view: View,
    animationResource: Int,
    strMessage: String = "(fragment name) -> Animation started for specific view",
    onAnimationEnd: () -> Unit,
  ) {
    view.visibility = View.VISIBLE
    val animation = AnimationUtils.loadAnimation(context, animationResource)
    animation.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationStart(animation: Animation?) {
        // Animation started
      }

      override fun onAnimationEnd(animation: Animation?) {
        // Animation ended
        onAnimationEnd.invoke() // Execute the provided function when animation ends
      }

      override fun onAnimationRepeat(animation: Animation?) {
        // Animation repeated (if any)
      }
    })

    // Set animation to the view
    view.startAnimation(animation)
    Log.i(Constants.TAG_MESSAGE, strMessage)
  }

  /**
   * Clears animations from the specified views.
   * @param views The list of views to clear animations from.
   */
  fun clearAnimations(views: List<View>) {
    for (item in views) {
      item.clearAnimation()
    }
    Log.i(Constants.TAG_MESSAGE, "clear all animations")
  }

  /**
   * Sets visibility of the specified views to GONE.
   * @param views The list of views to set visibility to GONE.
   */
  fun goneViews(views: List<View>) {
    for (item in views) {
      item.visibility = View.GONE
    }
    Log.i(Constants.TAG_MESSAGE, "goneViews")
  }
}
