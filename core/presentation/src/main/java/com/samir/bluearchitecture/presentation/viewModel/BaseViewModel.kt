package com.samir.bluearchitecture.presentation.viewModel

import androidx.lifecycle.ViewModel

/**
 * 🍧 BaseViewModel 🍧
 *
 * ▶️ **Purpose:**
 * - This is a base class for all ViewModels in the application.
 * - Provides a **common structure** for handling ViewModel lifecycle operations.
 * - Implements **start and stop methods** to standardize ViewModel initialization and cleanup.
 *
 * ▶️ **Why Use This Base Class?**
 * ✅ Encourages a **consistent** lifecycle management approach across ViewModels.
 * ✅ Supports **overloaded `start()` functions** for flexibility.
 * ✅ Provides a **standard `stop()` method** for resource cleanup.
 * ✅ Ensures **better maintainability and reusability** of ViewModel logic.
 */
abstract class BaseViewModel : ViewModel() {

  /**
   * 🍧 stop() - Cleanup Method 🍧
   *
   * ▶️ **Purpose:**
   * - Called when the ViewModel needs to release resources.
   * - Subclasses **must** override this method to define cleanup logic.
   * - This helps prevent **memory leaks** and ensures proper ViewModel shutdown.
   *
   * ▶️ **Example Implementation in a ViewModel:**
   * ```kotlin
   * override fun stop() {
   *     cancelJobs() // Cancel coroutines or network calls
   *     clearCache() // Remove temporary data
   * }
   * ```
   */
  abstract fun stop()

  /**
   * 🍧 start() - Default Method 🍧
   *
   * ▶️ **Purpose:**
   * - Called to **initialize ViewModel operations**.
   * - This method is **optional** and provides a default implementation.
   * - Subclasses **can override** this to define custom startup behavior.
   *
   * ▶️ **Example Override in a ViewModel:**
   * ```kotlin
   * override fun start() {
   *     fetchDataFromApi() // Start fetching initial data
   * }
   * ```
   */
  open fun start() {
    // Default startup behavior (can be overridden)
  }

  /**
   * 🍧 start(parameters: Map<String, Any>) - Overloaded Method 🍧
   *
   * ▶️ **Purpose:**
   * - Allows ViewModel to start with **dynamic parameters**.
   * - This is useful when a ViewModel needs **external inputs** (e.g., `userId`, `visitId`).
   *
   * ▶️ **Example Override in a ViewModel:**
   * ```kotlin
   * override fun start(parameters: Map<String, Any>) {
   *     val visitId = parameters["visitId"] as? String ?: return
   *     fetchVisitData(visitId) // Start fetching visit-specific data
   * }
   * ```
   *
   * ▶️ **Example Usage in a Fragment:**
   * ```kotlin
   * val parameters = mapOf("visitId" to "12345")
   * baseViewModel?.start(parameters)
   * ```
   */
  open fun start(parameters: Map<String, Any>) {
    // Default behavior for starting with parameters (can be overridden)
  }
}
