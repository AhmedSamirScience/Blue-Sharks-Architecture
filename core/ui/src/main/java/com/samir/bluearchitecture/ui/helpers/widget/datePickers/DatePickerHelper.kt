package com.samir.bluearchitecture.ui.helpers.widget.datePickers

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar

/**
 * Helper class for managing date picker dialogs.
 */
class DatePickerHelper {

  /**
   *  here is the call (Code) for this Class
   *  --------------------------------------------------------------------------------------------------------------------
   * DatePickerHelper().showDatePickerDialog(context = requireContext()) { year, month, day, cancel ->
   *                     // Handle the selected date here
   *                     baseViewBinding.btnChooseDate.isEnabled = true
   *
   *                     if (cancel) {
   *                         baseViewBinding.btnChooseDate.text =
   *                             getString(R.string.reporting_chooseReportDate)
   *                     } else {
   *                         pickedDate = "$day/${month + 1}/$year"
   *                     }
   *
   *                 }
   *  --------------------------------------------------------------------------------------------------------------------
   * Shows a date picker dialog.
   *
   * @param context The context in which the dialog should be displayed.
   * @param onDateSelected Callback function to be invoked when a date is selected.
   *                       Receives the selected year, month, day, and cancellation status as parameters.
   *                       If the user cancels the dialog, `cancel` parameter will be `true`.
   *                       If the user selects a date, `cancel` parameter will be `false`.
   */
  fun showDatePickerDialog(
    context: Context,
    onDateSelected: ((year: Int, month: Int, day: Int, cancel: Boolean) -> Unit)?,
  ) {
    // Get the current date to initialize the DatePickerDialog
    val currentDate = Calendar.getInstance()
    val yearToday = currentDate.get(Calendar.YEAR)
    val monthToday = currentDate.get(Calendar.MONTH)
    val dayToday = currentDate.get(Calendar.DAY_OF_MONTH)

    // Create the DatePickerDialog
    val datePickerDialog = DatePickerDialog(context, { _, year, month, day ->
      // Invoke the callback function with the selected date
      onDateSelected?.invoke(year, month, day, false)
    }, yearToday, monthToday, dayToday)

    // Set listener for cancellation event
    datePickerDialog.setOnCancelListener {
      // Invoke the callback function with cancel status
      onDateSelected?.invoke(0, 0, 0, true)
    }

    // Show the DatePickerDialog
    datePickerDialog.show()
  }
  fun addZero(digit: Int): String {
    if (digit / 10 > 0) {
      return ""
    }
    return "0"
  }

  fun getMonthName(month: Int): String {
    val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    return months[month] // `month` starts from 0 (January)}
  }
}
