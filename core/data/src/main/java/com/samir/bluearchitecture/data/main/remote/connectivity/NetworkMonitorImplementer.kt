package com.samir.bluearchitecture.data.main.remote.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * `NetworkMonitorImplementer`
 *
 * A concrete implementation of the [NetworkMonitorInterface] that checks the device's current
 * network connectivity status using Android’s `ConnectivityManager` and `NetworkCapabilities`.
 *
 * ### Responsibilities:
 * - Encapsulates logic to determine if the device is currently connected to the internet.
 * - Supports multiple transport types: Wi-Fi, Cellular, and Ethernet.
 * - Abstracts away the platform-specific implementation for easier testing and decoupling.
 *
 * ### Usage:
 * This class is designed to be injected or instantiated wherever network state checking is required.
 *
 * ```kotlin
 * val monitor = NetworkMonitorImplementer(context)
 * if (monitor.hasConnectivity()) {
 *     // Safe to make network calls
 * } else {
 *     // Notify user about connectivity issues
 * }
 * ```
 *
 * @constructor Creates a new instance of [NetworkMonitorImplementer].
 * @param context Application or Activity context used to obtain the system connectivity service.
 */
class NetworkMonitorImplementer(context: Context) : NetworkMonitorInterface {

  // Android system service used to query network state
  private val connectivityManager: ConnectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  /**
   * Checks whether the device currently has an active internet connection through
   * one of the supported transport types (Wi-Fi, Cellular, or Ethernet).
   *
   * ---
   * ✅ HOW IT WORKS:
   * - Retrieves the active network using `connectivityManager.activeNetwork`.
   * - Obtains the associated [NetworkCapabilities] for the active network.
   * - Checks whether any of the following transports are available:
   *   - [NetworkCapabilities.TRANSPORT_WIFI] → Wi-Fi connection
   *   - [NetworkCapabilities.TRANSPORT_CELLULAR] → Mobile data (4G/5G)
   *   - [NetworkCapabilities.TRANSPORT_ETHERNET] → Wired network (e.g., emulator or enterprise)
   *
   * If at least one of these transport types is active, the function returns `true`.
   * Otherwise, or if no network is active, it returns `false`.
   *
   * ---
   * ✅ WHY THIS IS IMPORTANT:
   * - It provides a modern, reliable way to detect internet access.
   * - More accurate than deprecated APIs like `NetworkInfo.isConnected()`.
   * - Essential for interceptors like `ConnectivityInterceptor` to block offline calls.
   *
   * ---
   * ✅ USAGE EXAMPLE:
   * ```kotlin
   * if (networkMonitor.hasConnectivity()) {
   *   // Proceed with online request
   * } else {
   *   // Show "No internet connection" UI
   * }
   * ```
   *
   * ---
   * @return `true` if the device has internet connectivity via Wi-Fi, mobile data, or Ethernet; `false` otherwise.
   */
  override fun hasConnectivity(): Boolean {
    return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { networkCapabilities ->
      listOf(
        NetworkCapabilities.TRANSPORT_WIFI,
        NetworkCapabilities.TRANSPORT_CELLULAR,
        NetworkCapabilities.TRANSPORT_ETHERNET,
      ).any { networkCapabilities.hasTransport(it) }
    } ?: false
  }
}
