package com.samir.bluearchitecture.data.main.remote.error

import android.content.Context
import com.samir.bluearchitecture.data.R
import com.samir.bluearchitecture.data.main.remote.source.DataSource.Companion.NO_INTERNET
import com.samir.bluearchitecture.data.main.remote.source.DataSource.Companion.PARSE_ERROR
import com.samir.bluearchitecture.data.main.remote.source.DataSource.Companion.TIMEOUT
import com.samir.bluearchitecture.data.main.remote.source.DataSource.Companion.UNKNOWN

class ErrorMessageProvider(private val context: Context) {

  fun getMessageForCode(code: Int): String {
    return when (code) {
      TIMEOUT -> context.getString(R.string.app_error_timeout_exception)
      NO_INTERNET -> context.getString(R.string.app_error_io_exception)
      PARSE_ERROR -> context.getString(R.string.app_error_invalid_data_received)
      UNKNOWN -> context.getString(R.string.app_error_unexpected_exception)
      else -> context.getString(R.string.app_error_http_exception) // fallback for HTTP 500, 403, etc.
    }
  }

  /**
   * Message shown when no data is returned from a successful API response.
   *
   * Use this when calling [OutCome. error] due to a valid HTTP 200 response
   * with a `null` or empty body.
   */
  fun getEmptyResponseMessage(): String {
    return context.getString(R.string.app_error_empty_response)
  }
}
