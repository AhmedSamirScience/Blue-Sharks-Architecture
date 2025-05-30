package com.samir.bluearchitecture.data.main.remote.uiState

/**
 * Event Wrapper
 * references:
 * https://stackoverflow.com/questions/58342060/why-is-onchanged-being-called-when-i-navigate-back-to-a-fragment
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 * https://medium.com/google-developer-experts/avoid-backing-properties-for-livedata-and-stateflow-706006c9867e
 */
open class Event<out T>(private val content: T) {

  var hasBeenHandled = false
    private set // Allow external read but not write

  /**
   * Returns the content and prevents its use again.
   */
  fun getContentIfNotHandled(): T? {
    return if (hasBeenHandled) {
      null
    } else {
      hasBeenHandled = true
      content
    }
  }

  /**
   * Returns the content, even if it's already been handled.
   */
  fun peekContent(): T = content
}
