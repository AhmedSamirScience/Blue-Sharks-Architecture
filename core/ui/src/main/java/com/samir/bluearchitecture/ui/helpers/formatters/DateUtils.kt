package com.samir.bluearchitecture.ui.helpers.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
            return format.format(date)
        }
    }
}