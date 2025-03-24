package com.samir.bluearchitecture.ui.helpers.input

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText

/**
 * Utility object for managing TextInputEditText views.
 */
object TextInputEditTextUtils {

    /**
     * Checks if the TextInputEditText is empty.
     *
     * @param view The TextInputEditText to check.
     * @param errorMessage The error message to display if the view is empty.
     * @return True if the view is empty, false otherwise.
     */
    fun isEmptyTextInputEditText(view: TextInputEditText, errorMessage: String, context: Context): Boolean {
        return if (view.text?.isEmpty() == true) {
            view.requestFocus()
            showSoftKeyboard(context, view)
            view.error = errorMessage
            true
        } else {
            false
        }
    }

    /**
     * Checks if the TextInputEditText's text matches a given validation string.
     *
     * @param view The TextInputEditText to check.
     * @param errorMessage The error message to display if the validation fails.
     * @param textValidation The string to compare against the TextInputEditText's text.
     * @return True if the TextInputEditText's text matches the validation string, false otherwise.
     */
    fun isEqualTextInputEditText(view: TextInputEditText, errorMessage: String, textValidation: String): Boolean {
        return if (view.text?.toString() == textValidation) {
            true
        } else {
            view.requestFocus()
            view.error = errorMessage
            false
        }
    }

    /**
     * Clears the text for all TextInputEditText views in the provided list.
     *
     * @param views The list of TextInputEditText views to clear.
     */
    fun clearTextForAllInputEditText(views: List<TextInputEditText>) {
        for (item in views) {
            item.text?.clear()
        }
    }

    fun showSoftKeyboard(context: Context, view: TextInputEditText) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(
           view, InputMethodManager.SHOW_IMPLICIT
        )
    }
}
