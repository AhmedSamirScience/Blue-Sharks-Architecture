package com.samir.bluearchitecture.ui.helpers.widget.datePickers

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar

/**
 * Helper class for managing date picker dialogs.
 */
class StaticDatePickerHelperForCurrentOneWeek {

  /**
   *  here is the call (Code) for this Class
   *  --------------------------------------------------------------------------------------------------------------------
   *          StaticDatePickerHelperForCurrentOneWeek().showDatePickerDialog(requireActivity()) { year, month, day, cancel ->
   *             if (!cancel) {
   *                 // Do something with the selected date
   *                 // For example, display it in a TextView
   *                 val selectedDate = "$day/${month + 1}/$year"
   *                 // textView.text = selectedDate
   *                 Toast.makeText(requireContext(),selectedDate, Toast.LENGTH_LONG).show()
   *             } else {
   *                 // Handle cancellation
   *             }
   *         }
   *  --------------------------------------------------------------------------------------------------------------------
   * Shows a date picker dialog.
   *
   * @param context The context in which the dialog should be displayed.
   * @param onDateSelected Callback function to be invoked when a date is selected.
   *                       Receives the selected year, month, day, and cancellation status as parameters.
   *                       If the user cancels the dialog, `cancel` parameter will be `true`.
   *                       If the user selects a date, `cancel` parameter will be `false`.
   *
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

    // Calculate the start date (Saturday) of the current week
    val startOfWeek = Calendar.getInstance()
    startOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)

    // Calculate the end date (Friday) of the current week
    val endOfWeek = Calendar.getInstance()
    if (currentDate.get(Calendar.DAY_OF_WEEK) < Calendar.FRIDAY) {
      // If today is before Friday, consider this week
      endOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
    } else {
      // If today is Friday or later, consider next week
      endOfWeek.add(Calendar.WEEK_OF_YEAR, 1)
      endOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
    }

    // Create the DatePickerDialog with the restricted date range (Saturday to Friday)
    val datePickerDialog = DatePickerDialog(
      context,
      { _, year, month, day ->
        // Invoke the callback function with the selected date
        onDateSelected?.invoke(year, month, day, false)
      },
      yearToday,
      monthToday,
      dayToday,
    )

    // Set the minimum and maximum dates to restrict to Saturday to Friday of the current week
    datePickerDialog.datePicker.minDate = startOfWeek.timeInMillis
    datePickerDialog.datePicker.maxDate = endOfWeek.timeInMillis

    // Set listener for cancellation event
    datePickerDialog.setOnCancelListener {
      // Invoke the callback function with cancel status
      onDateSelected?.invoke(0, 0, 0, true)
    }

    // Show the DatePickerDialog
    datePickerDialog.show()
  }
}
