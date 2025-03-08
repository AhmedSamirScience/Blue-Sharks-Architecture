package com.samir.bluearchitecture.presentation.fragment

import androidx.navigation.NavArgs
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel

/**
 * 🍧 SafeArgsFragmentManager 🍧
 *
 * ▶️ **Purpose:**
 * - A base class for fragments that may or may not need argument handling via Safe Args (`navArgs()`).
 * - Automatically retrieves arguments dynamically if available.
 * - Ensures type safety while making Safe Args optional.
 *
 * ▶️ **How It Works:**
 * - Subclasses **can** override `provideNavArgs()` to retrieve Safe Args.
 * - If Safe Args are not needed, `provideNavArgs()` can be left unimplemented.
 * - The `args` property will return `null` if no arguments are provided.
 *
 * ▶️ **Example Usage in a Fragment (Safe Args Present):**
 * ```kotlin
 * override fun provideNavArgs(): BlankFragmentArgs = navArgs<BlankFragmentArgs>().value
 * ```
 */
abstract class SafeArgsFragmentManager<VM : BaseViewModel>() : ViewModelStateHandler<VM>() {

  /**
   * 🍧 `args` Property 🍧
   *
   * ▶️ **Purpose:**
   * - Lazily retrieves Safe Args if available.
   * - If no arguments exist, it returns `null`.
   *
   * ▶️ **Usage Example:**
   * ```kotlin
   * val argumentValue = (args as? BlankFragmentArgs)?.someArgument ?: "Default Value"
   * ```
   */
  protected val args: NavArgs? by lazy {
    try {
      provideNavArgs()
    } catch (e: IllegalArgumentException) {
      null // No arguments provided, return null
    }
  }

  /**
   * 🍧 provideNavArgs() 🍧
   *
   * ▶️ **Purpose:**
   * - Subclasses can override this method to return Safe Args (`navArgs()`).
   * - Returns `null` by default, making Safe Args **optional**.
   *
   * ▶️ **Example Override in a Fragment:**
   * ```kotlin
   * override fun provideNavArgs(): BlankFragmentArgs = navArgs<BlankFragmentArgs>().value
   * ```
   *
   * @return The Safe Args instance if available, otherwise `null`.
   */
  protected open fun provideNavArgs(): NavArgs? = null
}

/**
 * 🍧 Example of How to Use `SafeArgsFragmentManager` 🍧
 *
 * ▶️ **Scenario 1: Fragment That Uses Safe Args**
 * ```kotlin
 * class BlankFragment : BaseFragment<BlankViewModel, FragmentBlankBinding>() {
 *
 *     override fun initializeViewModel() {
 *         val viewModel: BlankViewModel by viewModels()
 *         baseViewModel = viewModel
 *     }
 *
 *     override fun provideNavArgs(): BlankFragmentArgs = navArgs<BlankFragmentArgs>().value
 *
 *     override fun getContentView(): Int = R.layout.fragment_blank
 *
 *     override fun initializeViews() {
 *         // ✅ Safely access Safe Args
 *         val argumentValue = (args as? BlankFragmentArgs)?.someArgument ?: "Default Value"
 *         Toast.makeText(requireContext(), "Argument: $argumentValue", Toast.LENGTH_SHORT).show()
 *     }
 * }
 * ```
 *
 * ▶️ **Scenario 2: Fragment That Does NOT Use Safe Args**
 * ```kotlin
 * class SimpleFragment : BaseFragment<SimpleViewModel, FragmentSimpleBinding>() {
 *
 *     override fun initializeViewModel() {
 *         val viewModel: SimpleViewModel by viewModels()
 *         baseViewModel = viewModel
 *     }
 *
 *     override fun getContentView(): Int = R.layout.fragment_simple
 *
 *     override fun initializeViews() {
 *         Toast.makeText(requireContext(), "No Safe Args in this fragment", Toast.LENGTH_SHORT).show()
 *     }
 * }
 * ```
 *
 * ▶️ **Navigating with Safe Args**
 * ```kotlin
 * val action = BlankFragmentDirections.actionBlankFragmentToSimpleFragment("Hello World!")
 * findNavController().navigate(action)
 * ```
 *
 * ▶️ **Navigating Without Arguments**
 * ```kotlin
 * findNavController().navigate(R.id.action_blankFragment_to_simpleFragment)
 * ```
 *
 * ---
 * **✨ Key Benefits of This Approach:**
 * ✅ **Safe Args Are Now Optional** (No need to enforce arguments in all fragments)
 * ✅ **Prevents Crashes** (Handles missing arguments safely)
 * ✅ **Clean and Reusable Base Class** (No more generic `<A : NavArgs>`)
 * ✅ **Allows Fragments to Decide If They Need Safe Args**
 *
 * 🚀 **Now your fragments can use Safe Args only when necessary!**
 */
