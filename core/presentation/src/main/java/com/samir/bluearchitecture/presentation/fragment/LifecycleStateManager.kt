package com.samir.bluearchitecture.presentation.fragment

import com.samir.bluearchitecture.presentation.logger.Logger

/**
 * Abstract class to handle fragment lifecycle methods.
 *
 * `LifecycleStateManager` extends `ViewStateManager` and provides
 * additional logging and control over lifecycle events.
 * This class can be extended by other fragments to standardize logging
 * and manage fragment-specific operations within the lifecycle.
 */
abstract class LifecycleStateManager : ViewStateManager() {

  //region onStart
  /**
   * 🍧onStart (Lifecycle)🍧
   *
   * ▶️ Description:
   *                 It is called when the fragment becomes visible to the user but is not yet in the foreground (it’s still not interactive).
   *
   * ▶️ Usages:
   *                ➊ ✅ Start Light Processes: Start lightweight tasks that prepare the UI, like loading data for a list (Not Firing APIs) or updating non-interactive elements.
   *                ➋ ✅ Starting animations that should run while the fragment is visible
   *                ➌ ✅ Registering broadcast receivers (if needed)
   *                ➌ ✅ Connecting to location services or sensors  (if needed)
   *                ➌ ✅ Fetching non-real-time data that only needs to load when the fragment appears (if needed)
   *
   * ▶️ Example (Kotlin Code):
   *                   override fun onStart() {
   *                       super.onStart()
   *                       // ✅ ➊ Start Light Processes
   *                       loadDashboardData()
   *
   *                       // ✅ ➋ Starting an animation when the fragment becomes visible
   *                       val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
   *                       binding.imageView.startAnimation(fadeInAnimation)
   *
   *                       // ✅ ➌ Register a broadcast receiver for battery status
   *                       requireContext().registerReceiver(
   *                       batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED)
   *                       )
   *
   *                       // ✅ ➍ Connect to location services
   *                       locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
   *                       locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10f, locationListener)
   *                   }
   *
   *                  //✅ Function to load non-real-time data in onStart()
   *                  private fun loadDashboardData() {
   *                      binding.textViewWelcome.text = "Welcome back, ${getUserName()}!"
   *                  }
   *                  private fun getUserName(): String {
   *                      return "John Doe" // Simulated user data
   *                  }
   **/
  override fun onStart() {
    Logger.i(fragment = this, message = "♻♻♻ onStart ♻♻♻")
    super.onStart()
  }
  //endregion

  //region onResume
  /**
   * 🍧onResume (Lifecycle)🍧
   *
   * ▶️ Description:
   * Called when the fragment is **fully interactive** with the user.
   * This is the best place to start UI updates, real-time observers, and resume paused tasks.
   *
   * ▶️ Usages:
   * ➊ ✅ Resume real-time (API updates). 🛑(If Needed and it is recommended to be in on view created)🛑
   * ➋ ✅ Refresh user interface elements that need updating every time the user returns. 🛑(If Needed)🛑
   * ➌ ✅ Reconnect to services like live chat, WebSockets, or media players. 🛑(If Needed)🛑
   * ➍ ✅ Start any foreground tasks that require direct user interaction. 🛑(If Needed)🛑
   *
   * ▶️ Example:
   * override fun onResume() {
   *         super.onResume()
   *
   *         // ✅ 1️⃣ Fetch latest API data when user resumes fragment 🛑(If Needed and it is recommended to be in on view created)🛑
   *         viewModel.fetchLatestNews()
   *
   *         // ✅ 2️⃣ Refresh time-sensitive UI elements 🛑(If Needed)🛑
   *         binding.textViewTimestamp.text = "Last updated: ${System.currentTimeMillis()}"
   *
   *         // ✅ 3️⃣ Reconnect to live chat service 🛑(If Needed)🛑
   *         chatService.connect()
   *
   *         // ✅ 4️⃣ Resume media playback if necessary 🛑(If Needed)🛑
   *         mediaPlayer?.start()
   *     }
   */
  override fun onResume() {
    Logger.i(fragment = this, message = "✅✅✅ onResume ✅✅✅")
    super.onResume()
    start()
  }

  /**
   * Starts any necessary operations or processes when the fragment
   * is in the foreground. Subclasses must override this method to
   * perform tasks that should begin with `onResume`.
   *
   * Default implementation is empty; subclasses provide the functionality.
   */
  protected open fun start() {
    // Default implementation is empty; subclasses override if needed
  }
  //endregion

  //region onPause
  /**
   * Called when the fragment is no longer interacting with the user.
   * Logs the event and calls the superclass's `onPause` method.
   * This can be overridden by subclasses to handle tasks that should
   * pause when the fragment is no longer active.
   */
  override fun onPause() {
    Logger.i(fragment = this, message = "⚠⚠⚠ onPause ⚠⚠⚠")
    super.onPause()
  }
  //endregion

  //region onStop
  /**
   * 🍧onStop (Lifecycle)🍧
   *
   * ▶️ Description:
   *                      onStop() is called when the fragment is no longer visible to the user but has not yet been destroyed.
   *                      At this stage, the fragment is in the background, and it is a good place to release resources, stop ongoing processes,
   *                      and unregister listeners to avoid memory leaks or unnecessary background processing.
   *
   * ▶️ Usages:
   *                      ✔ 1️⃣ Stop animations to prevent memory leaks
   *                      ✔ 2️⃣ Unregister broadcast receivers (e.g., battery status, network changes)
   *                      ✔ 3️⃣ Remove location updates to save battery
   *                      ✔ 4️⃣ Pause background tasks that are not needed when the fragment is invisible
   *                      ✔ 5️⃣ Release or disconnect external services (e.g., Bluetooth, media player, etc.)
   *
   * ▶️ Example (Kotlin Code):
   *                      override fun onStop() {
   *                          super.onStop()
   *
   *                          //✅ 1️⃣ Stop animations to prevent memory leaks
   *                          binding.imageView.clearAnimation()
   *
   *                          //✅ 2️⃣ Unregister the broadcast receiver
   *                          requireContext().unregisterReceiver(batteryReceiver)
   *
   *                          //✅ 3️⃣ Remove location updates to save battery
   *                          locationManager.removeUpdates(locationListener)
   *                      }
   */
  override fun onStop() {
    Logger.i(fragment = this, message = "🚩🚩🚩onStop🚩🚩🚩")
    super.onStop()
    stopAndReset()
  }

  /**
   * Stops and resets any ongoing operations or processes when the fragment
   * is no longer visible. This method should be overridden by subclasses to
   * handle any cleanup necessary when the fragment transitions out of the foreground.
   *
   * Default implementation is empty; subclasses provide the functionality.
   */
  protected open fun stopAndReset() {
    // Default implementation is empty; subclasses override if needed
  }
  //endregion
}

/**
 * In Android, the onStart() and onResume() methods are part of the Activity and Fragment lifecycle and serve different purposes.
 * Let’s go over when to use each of them, along with practical examples of what you might typically do in these methods.
 *
 * 1. onStart()
 * When it’s Called: onStart() is called when the activity or fragment becomes visible to the user but is not yet in the foreground (it’s still not interactive).
 *
 * Typical Use Cases:
 *
 * Initialize UI Components: Set up UI elements, animations, or resources that don’t need immediate interactivity.
 * Register Broadcast Receivers or Sensors: Start observing or listening to things that don’t require user interaction, like a location update.
 * Example:
 *
 * kotlin
 * Copy code
 * override fun onStart() {
 *     super.onStart()
 *     // Example: Register a listener for battery status
 *     registerReceiver(batteryStatusReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
 *     Log.i("Activity", "onStart called, battery status listener registered.")
 * }
 * Summary: Use onStart() to handle setup tasks for elements that only need visibility but not interactivity. This is where you can prepare the app’s UI without engaging the user directly.
 *
 * 2. onResume()
 * When it’s Called: onResume() is called when the activity or fragment becomes fully interactive with the user.
 * At this point, it’s at the top of the activity stack and ready to handle user input.
 *
 * Typical Use Cases:
 *
 * Start Interactivity: Resume user-interactive tasks, like starting animations, refreshing live data, or showing a dialog.
 * Register Real-Time Observers: Set up listeners that need to be refreshed every time the user sees the screen, such as state observers, real-time data listeners, or lifecycle-based callbacks.
 * Resume Background Tasks: Restart any processes paused in onPause(), like music playback, live data streams, or network calls.
 * Example:
 *
 * kotlin
 * Copy code
 * override fun onResume() {
 *     super.onResume()
 *     // Example: Refresh the latest data when the user returns to the screen
 *     viewModel.fetchLatestData()
 *     Log.i("Activity", "onResume called, latest data fetched.")
 * }
 * Summary: onResume() is ideal for restoring interactivity, updating real-time data, and resuming tasks that were paused.
 * This method is critical for refreshing information and engaging directly with the user.
 *
 * Differences Between onStart() and onResume()
 * Aspect	onStart()	onResume()
 * Purpose	Prepare UI elements and lightweight tasks	Resume full interactivity and handle user actions
 * Visibility State	Visible but not yet interactive	Fully visible and interactive
 * Typical Tasks	Register non-interactive listeners, initialize UI	Resume animations, start interactive observers
 * Real-Time Updates	Generally not necessary	Refresh and update real-time data
 * Practical Scenario
 * In a chat application, for example:
 *
 * onStart(): You might register listeners for incoming notifications, so you’re aware if a message is received in the background.
 * onResume(): You would refresh the chat messages and mark any unread messages as read, making sure the user sees the latest state immediately on returning to the app.
 * Each lifecycle method is essential in its own way, with onStart() serving as a precursor to onResume() for setting up background visibility and onResume() enabling the full user experience with live data and interactivity.
 */
