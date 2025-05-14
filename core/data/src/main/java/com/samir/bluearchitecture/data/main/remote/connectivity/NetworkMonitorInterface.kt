package com.samir.bluearchitecture.data.main.remote.connectivity

/**
 * `NetworkMonitorInterface`
 *
 * An abstraction for monitoring network connectivity status.
 *
 * ### Purpose:
 * - Provides a contract for checking whether the device has an active internet connection.
 * - Enables separation of concerns by decoupling the implementation from the usage.
 * - Facilitates easier testing and mocking in unit tests or different environments (e.g., emulator, real device).
 *
 * ### Common Implementations:
 * - [NetworkMonitorImplementer]: Uses Android’s `ConnectivityManager` to check connectivity.
 * - Mock implementations can be used in tests to simulate online/offline conditions.
 *
 * ### Example:
 * ```kotlin
 * class SomeViewModel(private val networkMonitor: NetworkMonitorInterface) {
 *     fun fetchDataIfConnected() {
 *         if (networkMonitor.hasConnectivity()) {
 *             // Proceed with network operation
 *         } else {
 *             // Show no connection error
 *         }
 *     }
 * }
 * ```
 */
interface NetworkMonitorInterface {

  /**
   * Determines whether the device currently has internet connectivity.
   *
   * @return `true` if the device has network access, otherwise `false`.
   */
  fun hasConnectivity(): Boolean
}
