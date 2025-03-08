package com.samir.bluearchitecture.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.samir.bluearchitecture.presentation.logger.Logger

/**
 * Abstract class to handle back pressed events in fragments.
 *
 * `BackPressedStateManager` provides a customizable way for fragments to handle
 * back press events. This class uses Android's `OnBackPressedCallback` to intercept
 * the back button and allows subclasses to define specific actions upon back press.
 */
abstract class BackPressedStateManager : LifecycleStateManager() {

  //region handling onBackPressedCallback
  /**
   * An `OnBackPressedCallback` that intercepts back press events.
   * It delegates the handling of back press based on the implementation
   * of `shouldHandleBackPressed` and `handleBackPressed` methods.
   */
  private val onBackPressedCallback = object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
      // Determine whether the fragment should handle the back press
      if (shouldHandleBackPressed()) {
        Logger.i(fragment = this@BackPressedStateManager, message = "BackPressed Inside (Fragment)🔙: Handle the back press within the ((fragment))")
        // Handle the back press within the fragment
        handleBackPressed()
      } else {
        Logger.i(fragment = this@BackPressedStateManager, message = "BackPressed Inside (Fragment)🔙: Handle the back press within the ((activity))")
        // Allow the activity to handle the back press instead
        isEnabled = false
        requireActivity().onBackPressedDispatcher.onBackPressed()
      }
    }
  }

  /**
   * Called when the fragment's view is created.
   * Registers the `onBackPressedCallback` with the fragment's `viewLifecycleOwner`
   * so that the callback is only active while the fragment is visible.
   *
   * @param view The root view of the fragment's layout.
   * @param savedInstanceState A `Bundle` containing the saved state of the fragment.
   */
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    requireActivity().onBackPressedDispatcher.addCallback(
      viewLifecycleOwner,
      onBackPressedCallback,
    )
  }

  /**
   * Determines whether the fragment should handle the back press event.
   * Subclasses can override this method to provide custom logic for
   * deciding if the fragment should intercept the back press.
   *
   * @return `true` if the fragment should handle the back press, `false` otherwise.
   * Default implementation returns `false`, allowing the activity to handle it.
   */
  protected open fun shouldHandleBackPressed(): Boolean {
    return false
  }

  /**
   * Handles the back press action within the fragment.
   * Subclasses can override this method to provide custom back press handling
   * behavior when `shouldHandleBackPressed` returns `true`.
   *
   * Default implementation does nothing; subclasses should provide functionality.
   */
  protected open fun handleBackPressed() {
    // Default implementation does nothing
  }
  //endregion
}
