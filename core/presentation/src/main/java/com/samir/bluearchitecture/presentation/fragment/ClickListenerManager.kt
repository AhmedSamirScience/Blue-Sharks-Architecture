package com.samir.bluearchitecture.presentation.fragment

import android.view.View

/**
 * 🍧 ClickListenerManager 🍧
 *
 * ▶️ **Purpose:**
 * - Handles setting click listeners dynamically for multiple views.
 * - Keeps click listener management separate from fragments, improving modularity.
 *
 * ▶️ **Usage:**
 * ```kotlin
 * ClickListenerManager.setClickListeners(listOf(view1, view2), clickListener)
 * ```
 */
object ClickListenerManager {

  /**
   * Sets click listeners for a list of views using the provided click listener.
   *
   * @param views The list of views to attach click listeners to.
   * @param clickListener The `View.OnClickListener` to handle clicks.
   */
  fun setClickListeners(views: List<View>, clickListener: View.OnClickListener) {
    for (view in views) {
      view.setOnClickListener(clickListener)
    }
  }
}
