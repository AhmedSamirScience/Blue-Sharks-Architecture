package com.samir.bluearchitecture.ui.helpers.widget.datePickers

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.NumberPicker
import com.samir.bluearchitecture.ui.R
import java.util.Calendar

/**
 * Helper class for managing month and year picker dialogs manually.
 */
class MonthYearPickerHelperDialogManually {

  /**
   * Shows a custom dialog for selecting month and year.
   *
   * @param context The context in which the dialog should be displayed.
   * @param onMonthYearSelected Callback function to be invoked when a month and year are selected.
   *                            Receives the selected year and month as parameters.
   */
  fun showMonthYearPickerDialog(
    context: Context,
    onMonthYearSelected: (year: Int, month: Int) -> Unit,
  ) {
    // Get the current date
    val currentDate = Calendar.getInstance()
    val currentYear = currentDate.get(Calendar.YEAR)
    val currentMonth = currentDate.get(Calendar.MONTH)

    // Inflate custom layout
    val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_month_year_picker, null)

    // Find the NumberPickers in the layout
    val monthPicker = dialogView.findViewById<NumberPicker>(R.id.monthPicker)
    val yearPicker = dialogView.findViewById<NumberPicker>(R.id.yearPicker)

    // Set up the month picker
    monthPicker.minValue = 0 // January
    monthPicker.maxValue = 11 // December
    monthPicker.displayedValues = arrayOf(
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December",
    )
    monthPicker.value = currentMonth

    // Set up the year picker
    yearPicker.minValue = currentYear - 50 // 50 years ago
    yearPicker.maxValue = currentYear + 50 // 50 years in the future
    yearPicker.value = currentYear

    // Build the dialog
    AlertDialog.Builder(context)
      .setTitle("Select Month and Year")
      .setView(dialogView)
      .setPositiveButton("OK") { _, _ ->
        val selectedYear = yearPicker.value
        val selectedMonth = monthPicker.value
        onMonthYearSelected(selectedYear, selectedMonth)
      }
      .setNegativeButton("Cancel", null)
      .show()
  }
}

/**
 * client code
 * --------------------------------------------------------------------------------------------------------------------
 * MonthYearPickerHelperDialogManually().showMonthYearPickerDialog(context = requireContext()) { year, month ->
 *     // Handle selected month and year
 *     val formattedDate = "${month + 1}/$year" // Month is zero-based
 *     baseViewBinding.btnChooseDate.text = formattedDate
 * }
 *
 */
