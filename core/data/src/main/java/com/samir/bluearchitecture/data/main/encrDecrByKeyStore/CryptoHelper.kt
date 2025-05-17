package com.samir.bluearchitecture.data.main.encrDecrByKeyStore

/**
 * Helper class that provides simplified access to encryption and decryption functionality
 * using the Android Keystore system via EncryptionDecryptionManager.
 *
 * This class wraps around the lower-level encryption API and exposes
 * high-level `encrypt` and `decrypt` functions.
 */
class CryptoHelper {

  // Instance of the encryption manager responsible for key generation and data processing
  private val encryptionManager = EncryptionDecryptionManager()

  // Cryptographic key used for both encryption and decryption operations
  // Note: Assumes `createKey()` either retrieves an existing key or generates a new one once
  private val keyModel = encryptionManager.createKey()

  /**
   * Encrypts the given plain text string using the internally managed key.
   *
   * @param value The plain text to encrypt.
   * @return The encrypted result as a Base64 or encoded string.
   */
  fun encrypt(value: String): String {
    return encryptionManager.encryptData(value, keyModel)
  }

  /**
   * Decrypts the given encrypted string using the internally managed key.
   *
   * @param value The encrypted text to decrypt.
   * @return The original plain text after decryption.
   */
  fun decrypt(value: String): String {
    return encryptionManager.decryptData(value, keyModel)
  }
}
