package com.samir.bluearchitecture.ui.keyboard

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText

object SoftKeyBoardUtils {

  /**
   * Shows the soft keyboard for the given view.
   */
  fun showSoftKeyboard(context: Context, view: TextInputEditText) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
  }

  /**
   * Hides the soft keyboard from the currently focused view.
   */
  fun hideSoftKeyboard(activity: Activity) {
    val view = activity.currentFocus
    if (view != null) {
      val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }
}

/**
 * val keyboardUtils = SoftKeyBoardUtils()
 *
 * // To show the keyboard
 * keyboardUtils.showSoftKeyboard(requireContext(), baseViewBinding.edtEnterEmployeeCode)
 *
 * // To hide the keyboard
 * keyboardUtils.hideSoftKeyboard(requireActivity())
 *
 */
