package com.samir.bluearchitecture.presentation.fragment

import android.view.View

interface FragmentSetupContract {

  //region Layout and View Configuration
  /**
   * Gets the layout resource ID for the fragment.
   * Subclasses must override this method to provide the layout resource ID.
   * @return Layout resource ID.
   */
  fun getContentView(): Int

  /**
   * Method to be implemented by subclasses to provide the list of views to be managed.
   * @return List of views to be enabled or disabled.
   */
  fun getAllViews(): List<View>
  //endregion

  //region User Interaction and Event Listeners
  /**
   * Called to set up event listeners for interactive UI components in the fragment.
   * This method allows subclasses to specify which views should have event listeners attached.
   * @return List of Views that require event listeners.
   */
  fun initializeListeners(): List<View>

  /**
   * Provides a centralized click listener that handles click events for multiple views.
   * This allows better separation of concerns by defining the behavior in one place.
   * @return A View.OnClickListener instance for handling view clicks.
   */
  fun getClickListener(): View.OnClickListener
  //endregion

  //region ViewModel and LiveData Management
  /**
   * Initializes the ViewModel associated with the fragment.
   * Subclasses must override this method to initialize the ViewModel.
   */
  fun initializeViewModel()

  /**
   * Subscribes to LiveData updates.
   * Subclasses must override this method to observe LiveData objects.
   */
  fun subscribeObservers()
  //endregion
}
