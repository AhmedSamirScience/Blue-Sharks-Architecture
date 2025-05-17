package com.samir.bluearchitecture.data.main.encrDecrByKeyStore.keyStoreIntegration.models

/**
 * All these parameters are employed in generating the key required for the encryption and decryption procedures.
 */
data class KeyModelRequest(
  /**
   * = KeyProperties.KEY_ALGORITHM_AES
   */
  val algorithm: String,

  /**
   * = KeyProperties.BLOCK_MODE_CBC
   */
  val blockMode: String,

  /**
   * = KeyProperties.ENCRYPTION_PADDING_PKCS7
   */
  val padding: String,

  val keyAliasName: String,
  val provider: String,
)
