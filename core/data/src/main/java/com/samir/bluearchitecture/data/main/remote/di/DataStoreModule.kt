package com.samir.bluearchitecture.data.main.remote.di

import android.content.Context
import com.samir.bluearchitecture.data.main.dataSource.EncryptedPreferencesDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

  @Provides
  @Singleton
  fun provideEncryptedPreferencesDataStoreManager(
    @ApplicationContext context: Context,
  ): EncryptedPreferencesDataStoreManager {
    return EncryptedPreferencesDataStoreManager(context)
  }
}
