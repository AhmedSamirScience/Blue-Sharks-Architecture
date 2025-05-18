package com.samir.bluearchitecture.data.main.dataSource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.EncryptionDecryptionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ✅ A secure wrapper around Jetpack DataStore using encryption and decryption.
 *
 * This class provides encrypted storage and retrieval for primitive and complex types
 * using Google's AndroidX DataStore, backed by Android KeyStore encryption.
 */
@Singleton // Ensures a single instance is used app-wide via Hilt
class EncryptedPreferencesDataStoreManager @Inject constructor(
  @ApplicationContext private val context: Context, // Inject the application context
) {

  //region 🔐 Dependencies
  // Encryption manager instance responsible for encrypting/decrypting data
  private val encryptionManager = EncryptionDecryptionManager()

  // Generated encryption key using Android KeyStore
  private val keyModel = encryptionManager.createKey()
  //endregion

  //region ⚙️ Internal DataStore
  // Declare DataStore instance using Kotlin extension property
  // Preferences-based (key-value pairs)
  private val Context.dataStore by preferencesDataStore(name = "secure_pref")
  //endregion

  //region 🔐 Core Encryption
  /**
   * Encrypts and stores a plain text string value securely in DataStore.
   */
  suspend fun saveEncryptedString(key: String, value: String) {
    val encrypted = encryptionManager.encryptData(value, keyModel) // Encrypt input string
    val prefKey = stringPreferencesKey(key) // Define DataStore key
    context.dataStore.edit { it[prefKey] = encrypted } // Save encrypted string
  }

  /**
   * Reads an encrypted string from DataStore and decrypts it into plain text.
   */
  suspend fun getDecryptedString(key: String): String {
    val prefKey = stringPreferencesKey(key) // Define DataStore key
    val encrypted = context.dataStore.data // Observe preferences flow
      .map { it[prefKey] ?: "" } // Read encrypted value
      .first() // Suspend and get latest
    return encryptionManager.decryptData(encrypted, keyModel) // Decrypt and return
  }
  //endregion

  //region 🔢 Int/Double Support

  /**
   * Encrypts and stores an integer value as a string.
   */
  suspend fun saveEncryptedInt(key: String, value: Int) {
    saveEncryptedString(key, value.toString()) // Convert to string → encrypt → store
  }

  /**
   * Retrieves and decrypts an integer stored as a string.
   */
  suspend fun getDecryptedInt(key: String): Int {
    return getDecryptedString(key).toIntOrNull() ?: 0 // Convert decrypted string to Int
  }

  /**
   * Encrypts and stores a double value as a string.
   */
  suspend fun saveEncryptedDouble(key: String, value: Double) {
    saveEncryptedString(key, value.toString()) // Convert to string → encrypt → store
  }

  /**
   * Retrieves and decrypts a double stored as a string.
   */
  suspend fun getDecryptedDouble(key: String): Double {
    return getDecryptedString(key).toDoubleOrNull() ?: 0.0 // Convert to Double or fallback
  }
  //endregion

  //region 🧩 Object Support

  /**
   * Serializes an object into JSON, encrypts it, and stores it in DataStore.
   */
  suspend fun saveEncryptedObject(key: String, model: Any) {
    val json = Gson().toJson(model) // Convert object to JSON string
    saveEncryptedString(key, json) // Encrypt and store JSON
  }

  /**
   * Retrieves, decrypts, and deserializes a JSON string into an object of type T.
   */
  suspend inline fun <reified T> getDecryptedObject(key: String): T? {
    return try {
      val json = getDecryptedString(key) // Decrypt stored JSON string
      Gson().fromJson(json, T::class.java) // Deserialize into object
    } catch (e: Exception) {
      null // Return null if parsing fails
    }
  }
  //endregion

  //region 🧼 Clear
  /**
   * Clears all entries in the secure DataStore.
   */
  suspend fun clearAll() {
    context.dataStore.edit { it.clear() } // Wipe all key-value pairs
  }
  //endregion
}

/**
 * ////// ------- Examples for calling ------- //////
 * // Save
 * prefs.saveEncryptedInt("login_count", 5)
 * prefs.saveEncryptedString("email", "test@example.com")
 * prefs.saveEncryptedObject("server", ServerTime(...))
 *
 * // Get
 * val count = prefs.getDecryptedInt("login_count")
 * val email = prefs.getDecryptedString("email")
 * val serverTime = prefs.getDecryptedObject<ServerTime>("server")
 */
