package com.samir.bluearchitecture.ui.helpers.widget.time_picker

import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar

/**
 * Helper class for managing time picker dialogs.
 */
class TimePickerHelper {

    /**
     * Shows a time picker dialog.
     *
     * @param context The context in which the dialog should be displayed.
     * @param is24HourView Whether to display the time in 24-hour format.
     * @param onTimeSelected Callback function to be invoked when a time is selected.
     *                       Receives the selected hour, minute, and cancellation status as parameters.
     *                       If the user cancels the dialog, `cancel` parameter will be `true`.
     *                       If the user selects a time, `cancel` parameter will be `false`.
     */
    fun showTimePickerDialog(
        context: Context,
        is24HourView: Boolean = true,
        onTimeSelected: ((hour: Int, minute: Int, cancel: Boolean) -> Unit)?
    ) {
        // Get the current time to initialize the TimePickerDialog
        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentTime.get(Calendar.MINUTE)

        // Create the TimePickerDialog
        val timePickerDialog = TimePickerDialog(context, { _, hourOfDay, minute ->
            // Invoke the callback function with the selected time
            onTimeSelected?.invoke(hourOfDay, minute, false)
        }, currentHour, currentMinute, is24HourView)

        // Set listener for cancellation event
        timePickerDialog.setOnCancelListener {
            // Invoke the callback function with cancel status
            onTimeSelected?.invoke(0, 0, true)
        }

        // Show the TimePickerDialog
        timePickerDialog.show()
    }

    /**
     * Helper method to format a digit to a two-digit string (e.g., 01, 09, 10).
     *
     * @param digit The digit to format.
     * @return The formatted two-digit string.
     */
    fun addZero(digit: Int): String {
        return if (digit < 10) "0$digit" else digit.toString()
    }
}

/**
 * How can You Call the Class: (Call Example)
 *
 * TimePickerHelper().showTimePickerDialog(context = requireContext()) { hour, minute, cancel ->
 *     if (cancel) {
 *         // Handle cancellation
 *     } else {
 *         val formattedTime = "${TimePickerHelper().addZero(hour)}:${TimePickerHelper().addZero(minute)}"
 *         // Use the formatted time here
 *     }
 * }
 */