package com.samir.bluearchitecture.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import com.samir.bluearchitecture.ui.utils.logging.Logger

/**
 * Base class for all activities in the application.
 * This class provides common functionality such as setting up views, etc.
 */
abstract class BaseActivity : BackPressedHandlerActivity() {

  /** ViewDataBinding instance for the layout of the activity */
  protected lateinit var baseViewBinding: ViewDataBinding

  /**
   * Inflates the binding for the activity layout.
   * @param inflater LayoutInflater instance to inflate the binding.
   * @return ViewDataBinding instance for the inflated layout.
   */
  protected abstract fun inflateBinding(inflater: LayoutInflater): ViewDataBinding
  protected abstract fun getContainer(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    try {
      // Safely inflate the binding
      baseViewBinding = inflateBinding(layoutInflater)
      setContentView(baseViewBinding.root)

      // Ensure container ID is valid before accessing NavHostFragment
      val containerId = getContainer()
      if (containerId == 0) {
        throw IllegalArgumentException("Invalid container ID: $containerId")
      }

      // Safely get NavHostFragment and NavController
      val navHostFragment = supportFragmentManager.findFragmentById(containerId)
      if (navHostFragment !is NavHostFragment) {
        throw IllegalStateException("NavHostFragment not found in container ID: $containerId")
      }

      navController = navHostFragment.navController

      // Safely initialize views
      initializeViews()
    } catch (e: Exception) {
      Logger.e(activity = this, message = "Error in BaseActivity initialization", throwable = e)
      handleInitializationError(e) // Handle the error gracefully
    }

    val navHostFragment = supportFragmentManager.findFragmentById(getContainer()) as NavHostFragment
    navController = navHostFragment.navController
    initializeViews()
  }

  /**
   * To gracefully handle initialization failures, implement this method:
   *
   */
  private fun handleInitializationError(e: Exception) {
    Logger.e(activity = this, message = "Activity failed to initialize due to an error", throwable = e)

    // Show a fallback UI or message
    Toast.makeText(this, "Something went wrong: ${e.localizedMessage}", Toast.LENGTH_LONG).show()

    // Optionally finish the activity to prevent a broken state
    // finish()
  }

  /**
   * Initializes the views of the activity.
   * Subclasses must override this method to set up views.
   */
  protected abstract fun initializeViews()
}
