package com.samir.bluearchitecture.presentation.fragment

import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel

/**
 * 🍧 Abstract ViewModelStateHandler 🍧
 *
 * ▶️ **Purpose:**
 * - Provides an abstract class to manage `start()` and `stop()` lifecycle functions of `BaseViewModel`.
 * - Prevents redundant `start()` calls by tracking whether data is already loaded.
 * - Allows subclasses to customize how ViewModel lifecycle methods behave.
 *
 * ▶️ **Usage in `BaseFragment`:**
 * ```kotlin
 * class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding, MyFragmentArgs>() {
 *     override fun provideViewModelStateHandler() = MyCustomViewModelStateHandler()
 * }
 * ```
 */
abstract class ViewModelStateHandler<V : BaseViewModel>() : BackPressedStateManager() {
  protected lateinit var baseViewModel: V // ✅ No constructor needed!

  /**
   * Tracks whether data has already been loaded.
   * This prevents multiple unnecessary API calls when the fragment is recreated.
   */
  private var isDataLoaded = false

  /**
   * 🍧 startViewModelIfNeeded() 🍧
   *
   * ▶️ **Purpose:**
   * - Calls `start()` on the ViewModel **only if data hasn't been loaded yet**.
   * - Prevents unnecessary reloading when navigating back and forth.
   */
  open fun startViewModel() {
    if (!isDataLoaded) {
      isDataLoaded = true
      baseViewModel.start()
    }
  }

  /**
   * 🍧 stopViewModel() 🍧
   *
   * ▶️ **Purpose:**
   * - Calls `stop()` on the ViewModel when the fragment is destroyed.
   */
  open fun stopViewModel() {
    baseViewModel.stop()
  }
}
