package com.samir.bluearchitecture.data.main.source

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.samir.bluearchitecture.data.main.error.getDefaultErrorResponse
import com.samir.bluearchitecture.data.main.error.getErrorResponse
import com.samir.bluearchitecture.data.main.interceptors.NoConnectivityException
import com.samir.bluearchitecture.data.main.mapper.toDomain
import com.samir.bluearchitecture.data.main.response.ErrorResponse
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.CONFLICT
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.FORBIDDEN
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.INTERNAL_SERVER_ERROR
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.NOT_FOUND
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.NO_INTERNET
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.PARSE_ERROR
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.SEE_OTHERS
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.TIMEOUT
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.TOO_MANY_REQUESTS
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.UNAUTHORIZED
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.UNKNOWN
import com.samir.bluearchitecture.data.main.source.DataSource.Companion.UNPROCESSABLE_ENTITY
import com.samir.bluearchitecture.domain.main.result.OutCome
import com.samir.bluearchitecture.ui.utils.logging.Logger
import kotlinx.coroutines.isActive
import okhttp3.Headers
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext

/**
 * Generic wrapper for executing safe API calls via Retrofit and mapping their outcomes.
 *
 * Handles success, empty, and error responses with custom logic.
 * Supports structured error handling and maps known codes to domain models.
 */
class NetworkDataSource<SERVICE>(
  private val service: SERVICE,
  private val gson: Gson,
) {

  /**
   * Executes a suspend API call and transforms the result into a standardized [OutCome].
   *
   * @param request Lambda containing the Retrofit request call.
   * @param onSuccess Handler invoked if the response is successful.
   * @param onEmpty Handler invoked if response body is empty or coroutine is inactive.
   * @param onError Handler invoked if the response is an error or an exception occurs.
   */
  suspend fun <R, T> performRequest(
    request: suspend SERVICE.() -> Response<R>,
    onSuccess: suspend (R, Headers) -> OutCome<T> = { _, _ -> OutCome.empty() },
    onEmpty: suspend () -> OutCome<T> = { OutCome.empty() },
    onError: suspend (ErrorResponse, Int) -> OutCome<T> = { errorResponse, code ->
      OutCome.error(errorResponse.toDomain(code))
    },
  ): OutCome<T> {
    try {
      val response = service.request()
      val responseCode = response.code()
      val errorBody = response.errorBody()?.string()

      if (response.isSuccessful || responseCode == SEE_OTHERS) {
        val body = response.body()
        return if (body != null && body != Unit) {
          if (coroutineContext.isActive) {
            onSuccess(body, response.headers())
          } else {
            onEmpty()
          }
        } else {
          onEmpty()
        }
      } else {
        val parsedError = if (errorBody.isNullOrBlank()) {
          getDefaultErrorResponse()
        } else {
          getErrorResponse(gson, errorBody)
        }

        // 🛡️ Handle specific known HTTP error codes with Logger
        when (responseCode) {
          UNAUTHORIZED -> {
            Logger.e(useCase = this::class.java, message = "🔐 Unauthorized – access token may be invalid or expired")
          }
          FORBIDDEN -> {
            Logger.e(useCase = this::class.java, message = "⛔ Forbidden – user is authenticated but not authorized")
          }
          NOT_FOUND -> {
            Logger.w(useCase = this::class.java, message = "❓ Not Found – endpoint or resource doesn't exist")
          }
          CONFLICT -> {
            Logger.w(useCase = this::class.java, message = "🔁 Conflict – possible duplicate data or versioning issue")
          }
          UNPROCESSABLE_ENTITY -> {
            Logger.i(useCase = this::class.java, message = "🧩 Validation Error – unprocessable entity (422)")
          }
          TOO_MANY_REQUESTS -> {
            Logger.w(useCase = this::class.java, message = "🚫 Rate Limit – too many requests (429)")
          }
          INTERNAL_SERVER_ERROR -> {
            Logger.e(useCase = this::class.java, message = "💥 Internal Server Error (500)")
          }
          else -> {
            Logger.w(useCase = this::class.java, message = "⚠️ Unhandled response code: $responseCode")
          }
        }

        return onError(parsedError, responseCode)
      }
    } catch (e: Exception) {
      val code = when (e) {
        is SocketTimeoutException -> {
          Logger.e(useCase = this::class.java, message = "⏱️ Timeout reached", throwable = e)
          TIMEOUT
        }
        is UnknownHostException, is NoConnectivityException -> {
          Logger.e(useCase = this::class.java, message = "🌐 No internet connection", throwable = e)
          NO_INTERNET
        }
        is JsonParseException -> {
          Logger.e(useCase = this::class.java, message = "🧨 JSON parsing failed", throwable = e)
          PARSE_ERROR
        }
        else -> {
          Logger.e(useCase = this::class.java, message = "❌ Unknown exception occurred", throwable = e)
          UNKNOWN
        }
      }
      return onError(getDefaultErrorResponse(), code)
    }
  }
}
