package com.samir.bluearchitecture.ui.helpers.widget.date_pickers

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar

/**
 * Helper class for managing date picker dialogs.
 */
class DynamicDatePickerHelperForCurrentOneWeekOrMore {

    /**
     *  here is the call (Code) for this Class
     *  --------------------------------------------------------------------------------------------------------------------
     *    private fun showDatePicker(context: Context) {
     *         val datePickerHelper = DynamicDatePickerHelperForCurrentOneWeekOrMore()
     *
     *         datePickerHelper.showDatePickerDialog(
     *             context = context, // Pass your activity or fragment context here
     *             onDateSelected = { year, month, day, cancel ->
     *                 if (!cancel) {
     *                     // Handle the selected date
     *                     // For example, you can display it or perform any desired action
     *                     val selectedDate = "$year-${month + 1}-$day"
     *                     Toast.makeText(context,"Selected date: $selectedDate", Toast.LENGTH_LONG ).show()
     *                 } else {
     *                     // Handle cancellation
     *                     Toast.makeText(context,"Dialog selection canceled", Toast.LENGTH_LONG ).show()
     *                 }
     *             },
     *             numberOfWeeks = 2 // Specify the number of weeks to consider (0 for one week, 1 for two weeks, and so on)
     *         )
     *     }
     *  --------------------------------------------------------------------------------------------------------------------
     * Shows a date picker dialog.
     *
     * @param context The context in which the dialog should be displayed.
     * @param onDateSelected Callback function to be invoked when a date is selected.
     *                       Receives the selected year, month, day, and cancellation status as parameters.
     *                       If the user cancels the dialog, `cancel` parameter will be `true`.
     *                       If the user selects a date, `cancel` parameter will be `false`.
     * @param numberOfWeeks Number of weeks to consider. 1 for current week only, 2 for current and next week, and so on.
     */
    fun showDatePickerDialog(
        context: Context,
        onDateSelected: ((year: Int, month: Int, day: Int, cancel: Boolean) -> Unit)?,
        numberOfWeeks: Int = 0 // Default to consider one week
    ) {
        // Get the current date to initialize the DatePickerDialog
        val currentDate = Calendar.getInstance()
        val yearToday = currentDate.get(Calendar.YEAR)
        val monthToday = currentDate.get(Calendar.MONTH)
        val dayToday = currentDate.get(Calendar.DAY_OF_MONTH)

        // Calculate the start date (Saturday) of the current week
        val startOfCurrentWeek = Calendar.getInstance()
        startOfCurrentWeek.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)

        // Calculate the end date (Friday) of the next week
        val endOfNextWeek = Calendar.getInstance()
        endOfNextWeek.add(Calendar.WEEK_OF_YEAR, numberOfWeeks)
        endOfNextWeek.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)

        // Create the DatePickerDialog with the restricted date range
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, day ->
                // Invoke the callback function with the selected date
                onDateSelected?.invoke(year, month, day, false)
            },
            yearToday,
            monthToday,
            dayToday
        )

        // Set the minimum and maximum dates based on the number of weeks to consider
        datePickerDialog.datePicker.minDate = startOfCurrentWeek.timeInMillis
        datePickerDialog.datePicker.maxDate = endOfNextWeek.timeInMillis

        // Set listener for cancellation event
        datePickerDialog.setOnCancelListener {
            // Invoke the callback function with cancel status
            onDateSelected?.invoke(0, 0, 0, true)
        }

        // Show the DatePickerDialog
        datePickerDialog.show()
    }
}
