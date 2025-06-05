package com.samir.bluearchitecture.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import com.samir.bluearchitecture.utils.logging.Logger

/**
 * Base class for all activities in the application.
 * Provides common setup logic for binding, navigation, and error handling.
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

  /**
   * Gets the container view ID for the NavHostFragment.
   */
  protected abstract fun getContainer(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    try {
      baseViewBinding = inflateBinding(layoutInflater)
      setContentView(baseViewBinding.root)

      val containerId = getContainer()
      if (containerId == 0) {
        throw IllegalArgumentException(
          StringBuilder("Invalid container ID: ").append(containerId).toString(),
        )
      }

      val navHostFragment = supportFragmentManager.findFragmentById(containerId)
      if (navHostFragment !is NavHostFragment) {
        throw IllegalStateException(
          StringBuilder("NavHostFragment not found in container ID: ").append(containerId).toString(),
        )
      }

      navController = navHostFragment.navController
      initializeViews()
    } catch (e: Exception) {
      Logger.e(activity = this, message = "Error in BaseActivity initialization", throwable = e)
      handleInitializationError(e)
    }
  }

  /**
   * Gracefully handles errors that occur during activity initialization.
   */
  private fun handleInitializationError(e: Exception) {
    Logger.e(activity = this, message = "Activity failed to initialize due to an error", throwable = e)

    Toast.makeText(
      this,
      StringBuilder("Something went wrong: ").append(e.localizedMessage).toString(),
      Toast.LENGTH_LONG,
    ).show()

    // finish() // Uncomment this if you want to close the activity on error
  }

  /**
   * Called to initialize the activity views. Must be implemented by subclasses.
   */
  protected abstract fun initializeViews()
}
