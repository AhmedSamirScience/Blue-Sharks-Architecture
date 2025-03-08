package com.samir.bluearchitecture.presentation.viewModel

import androidx.lifecycle.ViewModel

/**
 * Base class for all ViewModels in the application.
 * This class provides common functionality such as start and stop methods.
 */
abstract class BaseViewModel : ViewModel() {

  /**
   * Stops any ongoing operations or processes.
   * Subclasses must override this method to perform cleanup tasks.
   */
  abstract fun stop()

  /**
   * Starts any necessary operations or processes.
   * Subclasses must override this method to perform startup tasks.
   */
  // abstract fun start()
  // Basic start method with no parameters
  open fun start() {
    // Default startup behavior
  }

  // Overloaded start method with parameters
  open fun start(parameters: Map<String, Any>) {
    // Handle startup with parameters
  }
}
