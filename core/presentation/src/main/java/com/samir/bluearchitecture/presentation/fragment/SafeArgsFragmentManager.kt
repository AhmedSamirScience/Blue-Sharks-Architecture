package com.samir.bluearchitecture.presentation.fragment

import androidx.navigation.NavArgs
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel

/**
 * 🍧 SafeArgsFragmentManager 🍧
 *
 * ▶️ **Purpose:**
 * - A base class for fragments that need argument handling via Safe Args (`navArgs()`).
 * - Automatically retrieves arguments dynamically.
 * - Ensures type safety and cleaner argument handling.
 *
 * ▶️ **How It Works:**
 * - Subclasses define their `NavArgs` type in `provideNavArgs()`.
 * - Arguments are accessed via `args.propertyName`.
 *
 * ▶️ **Example Usage in a Fragment:**
 * ```kotlin
 * override fun provideNavArgs(): ScanBarCodeFragmentArgs = navArgs<ScanBarCodeFragmentArgs>().value
 * ```
 */
abstract class SafeArgsFragmentManager<A : NavArgs, VM : BaseViewModel>() : ViewModelStateHandler<VM>() {

  // Automatically retrieves Safe Args for the fragment
  protected val args: A by lazy { provideNavArgs() }

  /**
   * 🍧 provideNavArgs() 🍧
   *
   * ▶️ **Purpose:**
   * - Subclasses must implement this to return their Safe Args (`navArgs()`).
   *
   * ▶️ **Example Usage:**
   * ```kotlin
   * override fun provideNavArgs(): ScanBarCodeFragmentArgs = navArgs<ScanBarCodeFragmentArgs>().value
   * ```
   *
   * @return The Safe Args instance of type `A`.
   */
  protected abstract fun provideNavArgs(): A
}

/**
 * 🍧 Example of How to Use `SafeArgsFragmentManager` 🍧
 *
 * ▶️ **Step 1: Define Arguments in `navigation.xml`**
 * ```xml
 * <fragment
 *     android:id="@+id/scanBarCodeFragment"
 *     android:name="com.samir.bluearchitecture.presentation.scanner.ScanBarCodeFragment"
 *     android:label="Scan Barcode">
 *     <argument
 *         android:name="scanResult"
 *         app:argType="string"
 *         android:defaultValue="" />
 * </fragment>
 * ```
 *
 * ▶️ **Step 2: Extend `SafeArgsFragmentManager` in Your Fragment**
 * ```kotlin
 * class ScanBarCodeFragment : SafeArgsFragmentManager<ScanBarCodeFragmentArgs>() {
 *
 *     override fun provideNavArgs(): ScanBarCodeFragmentArgs = navArgs<ScanBarCodeFragmentArgs>().value
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *
 *         // Access argument dynamically
 *         val scanResult = args.scanResult
 *         Logger.d("Scanned Result: $scanResult")
 *     }
 * }
 * ```
 *
 * ▶️ **Step 3: Pass Arguments and Navigate**
 * ```kotlin
 * val action = HomeFragmentDirections.actionHomeToScanBarCode(scanResult = "QR-12345")
 * findNavController().navigate(action)
 * ```
 */
