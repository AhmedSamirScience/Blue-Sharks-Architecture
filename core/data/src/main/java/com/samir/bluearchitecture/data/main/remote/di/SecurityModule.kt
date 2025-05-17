package com.samir.bluearchitecture.data.main.remote.di

import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.CryptoHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

  @Provides
  @Singleton
  fun provideCryptoHelper(): CryptoHelper = CryptoHelper()
}
