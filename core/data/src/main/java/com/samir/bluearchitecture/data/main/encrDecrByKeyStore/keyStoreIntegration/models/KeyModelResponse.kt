package com.samir.bluearchitecture.data.main.encrDecrByKeyStore.keyStoreIntegration.models

import javax.crypto.SecretKey

data class KeyModelResponse(
  val transformation: String,
  val secretKey: SecretKey,
)
