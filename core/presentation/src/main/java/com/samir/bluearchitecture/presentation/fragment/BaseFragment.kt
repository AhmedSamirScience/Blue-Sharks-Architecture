package com.samir.bluearchitecture.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.samir.bluearchitecture.presentation.viewModel.BaseViewModel
import com.samir.bluearchitecture.ui.utils.logging.Logger

/**
 * Base class for all fragments in the application.
 * This class provides common functionality such as view initialization, lifecycle management, etc.
 * @param VM The type of ViewModel associated with the fragment.
 * @param VB The type of ViewDataBinding associated with the fragment's layout.
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding>() : SafeArgsFragmentManager<VM>(), View.OnClickListener {

  /*** ViewDataBinding instance for the fragment's layout **/
  protected lateinit var baseViewBinding: VB

  /**
   * 🍧onCreate (Lifecycle)🍧
   *
   * ▶️ Description:
   *                 Use this method for non-UI-related initializations like setting up ViewModels, retrieving arguments, or initializing other objects that don’t require the view to be created.
   *                 Ideal for dependency injection and setting up instance variables. (Avoid interacting with views here because the UI has not yet been inflated.)
   *
   * ▶️ Usages:
   *                ➊ ✅ Initialize ViewModel
   *                ➋ ✅ Retrieve arguments
   *
   * ▶️ Example (Kotlin Code):
   *                override fun onCreate(savedInstanceState: Bundle?) {
   *                   super.onCreate(savedInstanceState)
   *
   *                   // 👉 Initialize ViewModel 👈
   *                   viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
   *
   *                   // 👉 Retrieve arguments 👈
   *                   val myArgument = arguments?.getString("key")
   *               }
   **/
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Logger.i(fragment = this, message = "*************************** onCreate ***************************")

    /**
     * 🍧 initializeViewModel() 🍧
     *
     * ▶️ **Purpose:**
     * - This method is responsible for **initializing the ViewModel** associated with the fragment.
     * - Ensures that the fragment has access to the correct `ViewModel` instance before interacting with UI or data.
     * - Encourages **dependency injection** and best practices in the **MVVM (Model-View-ViewModel)** architecture.
     *
     * ▶️ **Why It's Important?**
     * - 🏗 Ensures that the `ViewModel` instance is ready **before making API calls or observing data**.
     * - 🔄 Allows fragments to use the **same ViewModel instance across different lifecycle states**.
     * - ⚡ Keeps UI logic separate from business logic by delegating tasks to the `ViewModel`.
     *
     * ▶️ **Where It Should Be Called?**
     * - Inside `onCreate()`, so the ViewModel is initialized **before** UI elements are set up.
     * - Must be called **before `subscribeObservers()`**, so that observers have a valid `ViewModel` instance.
     *
     * ▶️ **Example Usage in a Fragment:**
     * ```kotlin
     * override fun initializeViewModel() {
     *     val viewModel: UserViewModel by viewModels()
     *     baseViewModel = viewModel
     * }
     * ```
     *
     * ▶️ **Best Practices:**
     * ✅ Always use `by viewModels()` (or `by activityViewModels()` if sharing ViewModel between fragments).
     * ✅ Call **before `subscribeObservers()`** to ensure observers have a valid ViewModel.
     * ✅ Use **Hilt/Dagger** for dependency injection if applicable.
     *
     * ▶️ **Example Using Hilt for Dependency Injection:**
     * ```kotlin
     * @AndroidEntryPoint
     * class UserFragment : BaseFragment<UserViewModel, FragmentUserBinding>() {
     *
     *     override fun initializeViewModel() {
     *         val viewModel: UserViewModel by viewModels()
     *         baseViewModel = viewModel
     *     }
     * }
     * ```
     *
     * ▶️ **Common Mistakes to Avoid:**
     * ❌ Initializing the ViewModel **in `onViewCreated()` instead of `onCreate()`** → Leads to **unnecessary reinitialization**.
     * ❌ Using `ViewModelProvider(this)` manually → `by viewModels()` is the recommended way.
     * ❌ Forgetting to assign the ViewModel to `baseViewModel` in a generic base fragment.
     */
    initializeViewModel()
  }

  /**
   * 🍧onCreateView (Lifecycle)🍧
   *
   * ▶️ Description:
   *                 This is where the UI is inflated. Use this method to return the root view of the fragment by inflating it with DataBindingUtil or LayoutInflater.
   *                 Avoid extensive initializations here; keep it focused on inflating the layout and setting up view binding.
   *
   * ▶️ Usages:
   *                ➊ ✅ Nothing, no usages here (Use onCreateView for inflating the view and binding only)
   *
   * ▶️ Example (Kotlin Code):
   *                 override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
   *                      👉 binding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout, container, false) 👈
   *                      👉 return binding.root 👈
   *                 }
   **/
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    baseViewBinding = DataBindingUtil.inflate(inflater, getContentView(), container, false)
    return baseViewBinding.root
  }

  /**
   * 🍧onViewCreated (Lifecycle)🍧
   *
   * ▶️ Description:
   *                 Best for view-related initializations. This is called right after onCreateView, so views are ready for use.
   *                 Set up UI components, attach listeners, and start observing LiveData here.
   *                 Any ViewModel observers and UI logic that requires view initialization should go here.
   *
   *                 Called: Immediately after the fragment’s view is created but before it’s attached to the fragment’s lifecycle.
   *                 Purpose: Used to set up the fragment’s view elements and initialize objects (tied to the view), such as setting up RecyclerView adapters, attaching listeners, and binding view elements.
   *                 When to Use: This is generally where you set up views, bind data, and prepare the UI elements that are specifically tied to the fragment’s layout. You don’t yet have access to lifecycle-aware components here, as the fragment has not fully entered the STARTED or RESUMED states.
   *
   * ▶️ Usages:
   *                ➊ ✅ Set up observers (🔥Golden Rule) (Always attach LiveData observers in onViewCreated() using viewLifecycleOwner.) (🚀 This ensures they are bound to the view's lifecycle and automatically removed when the view is destroyed.)
   *                ➋ ✅ Initialize UI components or listeners (Click listeners, UI interactions)
   *                ➌ ✅ setting up RecyclerView adapters (Set up UI elements)
   *                ➍ ✅ Bind UI views (Set up UI elements)
   *                ➎ ✅ Toggle UI elements (Set up UI elements)
   *
   * ▶️ What Happens Behind the Scenes When a New Observer is Added?
   *              ⚠ When you add a LiveData observer, it registers a callback function that listens for changes in data inside the ViewModel.
   *                Problem: If you add the observer in onStart() or onResume(), a new callback function is added every time the fragment restarts!
   *                This can cause multiple function calls for the same data update.
   *
   * ▶️ Example (Kotlin Code):
   *                 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
   *                        super.onViewCreated(view, savedInstanceState)
   *
   *                        // 👉 Set up observers 👈
   *                        viewModel.someLiveData.observe(viewLifecycleOwner) { data ->
   *                            updateUI(data)
   *                        }
   *
   *                        // 👉 Initialize UI components or listeners 👈
   *                        binding.button.setOnClickListener {
   *                            // Handle button click
   *                        }
   *
   *                        // 👉 Bind UI views 👈
   *                        binding.textView.text = "Welcome"
   *
   *                        // 👉 Toggle UI elements 👈
   *                        binding.view.visibility = View.GONE
   *
   *                        // 👉 setting up RecyclerView adapters 👈
   *                        binding.recyclerView.adapter = MyAdapter(myDataList)
   *                        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
   *                 }
   **/
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Logger.i(fragment = this, message = "📺📺📺onViewCreated called📺📺📺")

    /**
     * Initializes the view state for the fragment by:
     *
     * 1. **Loading the views that can be enabled or disabled** dynamically using [getAllViews].
     * 2. **Optionally referencing a loading indicator view** via [getViewIndicatorProgress],
     *    which is shown/hidden during state changes (e.g., API call in progress).
     *
     * ---
     * ### ✅ Purpose:
     * This function prepares the fragment's interactive views for state control
     * via [disableAllViews] and [enableAllViews], promoting consistent UI feedback during loading or error states.
     *
     * ---
     * ### 📦 Typical Use Case:
     * Called in `onViewCreated()` before observing or reacting to data from the ViewModel.
     *
     * ---
     * ### ⚠️ Requirements:
     * - You must override [getAllViews] to specify the views to manage.
     * - Optionally override [getViewIndicatorProgress] if a loading indicator is used.
     *
     * ---
     * ### 🧩 Example:
     * ```kotlin
     * override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     *     super.onViewCreated(view, savedInstanceState)
     *     initializeViewState()
     *     subscribeObservers()
     * }
     * ```
     *
     * @see getAllViews
     * @see getViewIndicatorProgress
     * @see disableAllViews
     * @see enableAllViews
     */
    initializeViewState()

    /**
     * 🍧 subscribeObservers() 🍧
     *
     * ▶️ **Purpose:**
     * - This method is responsible for setting up **LiveData** or **StateFlow** observers inside the fragment.
     * - Observers listen for data changes in the `ViewModel` and trigger UI updates automatically.
     * - Ensures that the UI remains **reactive** to changes in **network calls, database updates, or any state changes**.
     *
     * ▶️ **Why It's Important?**
     * - 🔄 Keeps UI **updated in real-time** when data changes.
     * - 🚀 Follows **MVVM** pattern by keeping logic inside `ViewModel` and observing it in the `Fragment`.
     * - 🔥 Helps avoid **memory leaks** by observing LiveData with `viewLifecycleOwner`.
     *
     * ▶️ **Where It Should Be Called?**
     * - Inside `onViewCreated()`, so the observers are active **only when the view exists**.
     * - Should be used **before interacting with UI elements** that depend on LiveData.
     *
     * ▶️ **Example Usage in a Fragment:**
     * ```kotlin
     * override fun subscribeObservers() {
     *     viewModel.userData.observe(viewLifecycleOwner) { user ->
     *         binding.textViewUserName.text = user.name
     *     }
     * }
     * ```
     *
     * ▶️ **Best Practices:**
     * ✅ Always use `viewLifecycleOwner` to avoid **memory leaks**.
     * ✅ Observe **only in `onViewCreated()`**, not in `onCreate()`.
     * ✅ Handle **error states** inside the observer.
     *
     * ▶️ **Example Handling Different States in `StateFlow`:**
     * ```kotlin
     * override fun subscribeObservers() {
     *     viewLifecycleOwner.lifecycleScope.launch {
     *         viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
     *             viewModel.userStateFlow.collect { state ->
     *                 when (state) {
     *                     is UIState.Success -> updateUI(state.data)
     *                     is UIState.Error -> showError(state.message)
     *                     is UIState.Loading -> showLoading()
     *                 }
     *             }
     *         }
     *     }
     * }
     * ```
     *
     * ▶️ **Common Mistakes to Avoid:**
     * ❌ Observing LiveData in `onCreate()` instead of `onViewCreated()` → Can cause **memory leaks**.
     * ❌ Not using `viewLifecycleOwner` → Can lead to **UI updates on a destroyed view**.
     * ❌ Not handling error/loading states properly.
     */
    subscribeObservers()

    /**
     * 🍧 setClickListeners() 🍧
     *
     * ▶️ **Purpose:**
     * - Uses `ClickListenerManager` to attach click listeners dynamically.
     */
    ClickListenerManager.setClickListeners(initializeListeners(), getClickListener())

    /**
     * 🍧 startViewModelIfNeeded() 🍧
     *
     * ▶️ **Purpose:**
     * - Ensures that the `ViewModel`'s `start()` function is only executed **once per fragment lifecycle**.
     * - Prevents unnecessary API calls or re-execution of `start()` when navigating back to the fragment.
     * - This method is designed to improve **performance** and **avoid redundant data fetching**.
     *
     * ▶️ **Why It's Important?**
     * - 🔄 **Avoids redundant API calls**: Ensures `start()` is only triggered **once per fragment instance**.
     * - ⚡ **Optimized performance**: Saves network requests and expensive operations from being unnecessarily repeated.
     * - 🚀 **Improves UX**: Prevents reloading data every time the user navigates back to the same fragment.
     *
     * ▶️ **Where It Should Be Called?**
     * - Inside `onViewCreated()`, **after observers have been set up** (`subscribeObservers()`).
     * - Before performing any logic that depends on ViewModel data.
     *
     * ▶️ **Example Usage in a Fragment:**
     * ```kotlin
     * override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     *     super.onViewCreated(view, savedInstanceState)
     *
     *     // ✅ Set up observers first
     *     subscribeObservers()
     *
     *     // ✅ Attach click listeners dynamically
     *     ClickListenerManager.setClickListeners(initializeListeners(), getClickListener())
     *
     *     // ✅ Ensures ViewModel.start() is only called once per fragment instance
     *     startViewModelIfNeeded()
     * }
     * ```
     *
     * ▶️ **Implementation in `ViewModelStateHandler`:**
     * ```kotlin
     * open fun startViewModelIfNeeded() {
     *     if (!isDataLoaded) {
     *         isDataLoaded = true
     *         viewModel.start() // ✅ Calls the ViewModel's start() function
     *     }
     * }
     * ```
     *
     * ▶️ **Common Mistakes to Avoid:**
     * ❌ Calling `viewModel.start()` directly in `onViewCreated()` → Leads to **unnecessary re-fetching** of data.
     * ❌ Not tracking `isDataLoaded` → Can cause **multiple API calls** when navigating back to the fragment.
     * ❌ Calling it before `subscribeObservers()` → Can cause UI updates before the observer is set.
     */
    startViewModel()
  }

  /**
   * 🍧 `onDestroy()` - Fragment Lifecycle 🍧
   *
   * ▶️ **Description:**
   * - This method is called when the fragment is being **destroyed**.
   * - It is the last opportunity to **clean up resources** before the fragment is removed.
   * - Used to **free memory, unregister listeners, stop running processes**, and handle other cleanup tasks.
   *
   * ▶️ **Why It's Important?**
   * - Prevents **memory leaks** by releasing resources.
   * - Ensures that ViewModel and other dependencies are **properly stopped**.
   * - Helps in debugging by logging fragment destruction.
   *
   * ▶️ **Execution Order in Lifecycle:**
   * - `onDestroyView()` is called first (if applicable) to clean up the fragment’s view.
   * - Then `onDestroy()` is called to finalize fragment destruction.
   * - After that, the fragment is completely removed from memory.
   *
   * ▶️ **Breakdown of Functionality:**
   * 1. **Logs fragment destruction** for debugging purposes.
   * 2. **Calls `stopViewModel()`** to ensure that ViewModel-related resources are cleaned up.
   * 3. **Calls `super.onDestroy()`** to allow the parent class to execute its destruction logic.
   *
   * ▶️ **Example Usage in Fragment Lifecycle:**
   * ```kotlin
   * override fun onDestroy() {
   *     Logger.i(fragment = this, message = "⛔⛔⛔ onDestroy ⛔⛔⛔") // Log fragment destruction
   *     super.onDestroy()
   *     stopViewModel() // Clean up ViewModel-related resources
   * }
   * ```
   *
   * ▶️ **Best Practices:**
   * ✅ Always call `super.onDestroy()` to ensure proper cleanup.
   * ✅ Use `Logger.i()` for debugging and tracking lifecycle state.
   * ✅ Clean up resources like **ViewModel, LiveData observers, background tasks, and listeners**.
   *
   * ▶️ **Common Mistakes to Avoid:**
   * ❌ Forgetting to stop ViewModel, which may cause background processes to persist.
   * ❌ Not calling `super.onDestroy()`, leading to improper cleanup in the parent class.
   * ❌ Performing heavy operations in `onDestroy()`, as it should be lightweight.
   *
   * 🔗 **Related Lifecycle Methods:**
   * - `onDestroyView()` → Called when the fragment’s **view** is destroyed, but the fragment instance still exists.
   * - `onStop()` → Called when the fragment is no longer visible but is still in memory.
   * - `onDetach()` → Called when the fragment is completely detached from its activity.
   */
  override fun onDestroy() {
    Logger.i(fragment = this, message = "⛔⛔⛔ onDestroy ⛔⛔⛔")
    super.onDestroy()
    stopViewModel()
  }
}
