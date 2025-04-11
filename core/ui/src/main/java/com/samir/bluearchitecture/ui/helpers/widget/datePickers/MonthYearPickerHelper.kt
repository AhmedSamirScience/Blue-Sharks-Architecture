package com.samir.bluearchitecture.ui.helpers.widget.datePickers

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import java.util.Calendar

/**
 * Helper class for managing month and year picker dialogs.
 */
class MonthYearPickerHelper {

  /**
   * Shows a month and year picker dialog.
   *
   * @param context The context in which the dialog should be displayed.
   * @param onMonthYearSelected Callback function to be invoked when a month and year are selected.
   *                            Receives the selected year, month, and cancellation status as parameters.
   *                            If the user cancels the dialog, `cancel` parameter will be `true`.
   */
  fun showMonthYearPickerDialog(
    context: Context,
    onMonthYearSelected: ((year: Int, month: Int, cancel: Boolean) -> Unit)?,
  ) {
    // Get the current date to initialize the DatePickerDialog
    val currentDate = Calendar.getInstance()
    val yearToday = currentDate.get(Calendar.YEAR)
    val monthToday = currentDate.get(Calendar.MONTH)

    // Create the DatePickerDialog
    val datePickerDialog = DatePickerDialog(
      context,
      { _, year, month, _ ->
        // Invoke the callback function with the selected month and year
        onMonthYearSelected?.invoke(year, month, false)
      },
      yearToday,
      monthToday,
      1, // Default day set to 1
    )

    // Access the DatePicker widget inside the dialog
    val datePicker = datePickerDialog.datePicker

    // Hide the day spinner (or number picker)
    try {
      val daySpinnerId = context.resources.getIdentifier("day", "id", "android")
      val daySpinner = datePicker.findViewById<View>(daySpinnerId)
      if (daySpinner != null) {
        daySpinner.visibility = View.GONE
      }
    } catch (e: Exception) {
      e.printStackTrace() // Log the exception for debugging
    }

    // Set listener for cancellation event
    datePickerDialog.setOnCancelListener {
      // Invoke the callback function with cancel status
      onMonthYearSelected?.invoke(0, 0, true)
    }

    // Show the DatePickerDialog
    datePickerDialog.show()
  }

  /**
   * Adds a leading zero to single-digit numbers.
   *
   * @param digit The digit to format.
   * @return The formatted string with a leading zero if needed.
   */
  fun addZero(digit: Int): String {
    return if (digit / 10 > 0) digit.toString() else "0$digit"
  }
}

/**
 * client Code calling
 *----------------------------------------------------------------------------------------------------------------------
 * MonthYearPickerHelper().showMonthYearPickerDialog(context = requireContext()) { year, month, cancel ->
 *                     if (cancel) {
 *                         // Handle cancellation
 *                         //baseViewBinding.btnChooseDate.text = getString(R.string.reporting_chooseReportDate)
 *                     } else {
 *                         // Handle selected month and year
 *                         val selectedDate = "${MonthYearPickerHelper().addZero(month + 1)}/$year"
 *                         //baseViewBinding.btnChooseDate.text = selectedDate
 *                         Toast.makeText(context, selectedDate, Toast.LENGTH_SHORT).show()
 *                     }
 *                 }
 */
