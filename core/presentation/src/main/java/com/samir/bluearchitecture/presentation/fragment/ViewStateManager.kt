package com.samir.bluearchitecture.presentation.fragment

import android.view.View
import androidx.fragment.app.Fragment

/**
 * 🍧 ViewStateManager 🍧
 *
 * ▶️ **Purpose:**
 * - Provides a structured way to enable/disable views in a fragment.
 * - Allows showing or hiding a progress indicator while views are disabled.
 *
 * ▶️ **How It Works:**
 * - Subclasses implement `getAllViews()` to specify which views should be enabled/disabled.
 * - Optionally, they can override `getViewIndicatorProgress()` to set a progress indicator.
 * - `disableAllViews()` and `enableAllViews()` manage UI state dynamically.
 *
 * ▶️ **Example Usage in a Fragment:** (See example at the bottom)
 */
abstract class ViewStateManager : FragmentSetupContract, Fragment() {

  //region Disable and Enable All Views

  /**
   * List of all views in the fragment that should be enabled or disabled together.
   * This list is initialized in `onViewCreated` using the `getAllViews` method provided by subclasses.
   */
  private lateinit var allViews: List<View>

  /**
   * View that serves as an indicator of a loading or processing state.
   * This is optional and may be null if not needed. The view can be shown or hidden
   * during enable/disable operations to provide visual feedback to the user.
   */
  private var viewIndicatorProgress: View? = null

  /**
   * Method to be overridden by subclasses to provide an optional loading indicator view.
   * The default implementation returns null, meaning no loading indicator is used.
   * @return The view to be used as a loading indicator, or null if not needed.
   */
  protected open fun getViewIndicatorProgress(): View? = null

  /**
   * Called when the fragment's view has been created.
   * Initializes the `allViews` list and the optional `viewIndicatorProgress`.
   */
  protected fun initializeViewState() {
    allViews = getAllViews()
    viewIndicatorProgress = getViewIndicatorProgress()
  }

  /**
   * Disables all views provided in `allViews` by setting `isEnabled` to false.
   * If `viewIndicatorProgress` is not null, its visibility is set to `View.VISIBLE`
   * to indicate a loading or processing state.
   */
  fun disableAllViews() {
    for (item in allViews) {
      item.isEnabled = false
    }

    /**
     * - If viewIndicatorProgress is null, the statement is skipped. ✅
     * - No NullPointerException occurs because ?. prevents accessing a null object.
     */
    viewIndicatorProgress?.visibility = View.VISIBLE
  }

  /**
   * Enables all views provided in `allViews` by setting `isEnabled` to true.
   * If `viewIndicatorProgress` is not null, its visibility is set to `View.GONE`
   * to hide the loading indicator.
   */
  fun enableAllViews() {
    for (item in allViews) {
      item.isEnabled = true
    }

    /**
     * - If viewIndicatorProgress is null, the statement is skipped. ✅
     * - No NullPointerException occurs because ?. prevents accessing a null object.
     */
    viewIndicatorProgress?.visibility = View.GONE
  }
  //endregion
}

/**
 * 🍧 Example of How to Use `ViewStateManager` 🍧
 *
 * ▶️ **Step 1: XML Layout with Loading Indicator**
 * ```xml
 * <androidx.constraintlayout.widget.ConstraintLayout
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent">
 *
 *     <androidx.constraintlayout.widget.ConstraintLayout
 *         android:id="@+id/imgLoading"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"
 *         android:background="@color/black_light"
 *         android:gravity="center"
 *         android:visibility="gone">
 *
 *         <pl.droidsonroids.gif.GifImageView
 *             android:id="@+id/gifImageView"
 *             android:layout_width="@dimen/_140sdp"
 *             android:layout_height="@dimen/_140sdp"
 *             android:src="@drawable/all_loading_gif" />
 *
 *         <TextView
 *             android:id="@+id/txvReleaseVersion"
 *             android:layout_width="wrap_content"
 *             android:layout_height="wrap_content"
 *             android:text="@string/app_loading"
 *             android:textColor="@color/white"
 *             android:textSize="@dimen/_11sdp"
 *             app:layout_constraintTop_toBottomOf="@+id/gifImageView"/>
 *     </androidx.constraintlayout.widget.ConstraintLayout>
 * </androidx.constraintlayout.widget.ConstraintLayout>
 * ```
 *
 * ▶️ **Step 2: Extend `ViewStateManager` in Your Fragment**
 * ```kotlin
 * @AndroidEntryPoint
 * class CreateVisitPatientFragment: ViewStateManager() {
 *
 *     override fun getContentView(): Int = R.layout.fragment_visit_patient
 *
 *     override fun getAllViews(): List<View> {
 *         return listOf(
 *             baseViewBinding.recyclerView,
 *             baseViewBinding.edtSearch,
 *             baseViewBinding.btnSearch,
 *             baseViewBinding.tilSearch,
 *         )
 *     }
 *
 *     override fun getViewIndicatorProgress(): View {
 *         return baseViewBinding.baseLoading.imgLoading
 *     }
 *
 * }
 * ```
 */
