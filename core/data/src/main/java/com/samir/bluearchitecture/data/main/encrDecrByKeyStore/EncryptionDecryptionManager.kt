package com.samir.bluearchitecture.data.main.encrDecrByKeyStore

import android.security.keystore.KeyProperties
import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.keyStoreIntegration.KeyStoreHelper
import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.keyStoreIntegration.models.KeyModelRequest
import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.keyStoreIntegration.models.KeyModelResponse
import com.samir.bluearchitecture.data.main.remote.constants.SECRET_KEY_2024

/**
 * * This class was constructed following the principles of Procedural Module Cohesion.
 */
class EncryptionDecryptionManager {

  fun createKey(): KeyModelResponse {
    return KeyStoreHelper(
      KeyModelRequest(
        algorithm = KeyProperties.KEY_ALGORITHM_AES,
        blockMode = KeyProperties.BLOCK_MODE_CBC,
        padding = KeyProperties.ENCRYPTION_PADDING_PKCS7,
        keyAliasName = SECRET_KEY_2024,
        provider = "AndroidKeyStore",
      ),
    ).getKey()
  }

  fun encryptData(data: String, keyModelResponse: KeyModelResponse): String {
    return EncryptionModule().apply(data, keyModelResponse)
  }

  fun decryptData(data: String, keyModelResponse: KeyModelResponse): String {
    return DecryptionModule().apply(data, keyModelResponse)
  }
}
