package com.samir.bluearchitecture.remotedata.main.di

import android.content.Context
import com.google.gson.Gson
import com.samir.bluearchitecture.data.main.remote.error.ErrorMessageProvider
import com.samir.bluearchitecture.data.main.remote.factory.ServiceFactory
import com.samir.bluearchitecture.data.main.remote.source.NetworkDataSource
import com.samir.bluearchitecture.remotedata.main.data.remote.apiService.AuthApi
import com.samir.bluearchitecture.remotedata.main.data.remote.repository.AuthRepositoryImpl
import com.samir.bluearchitecture.remotedata.main.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  /**
   * Provides an instance of [ErrorMessageProvider] which maps internal error codes
   * to localized user-facing strings.
   *
   * @param context The application context used to access string resources.
   */
  @Provides
  fun provideErrorMessageProvider(@ApplicationContext context: Context): ErrorMessageProvider {
    return ErrorMessageProvider(context)
  }

  @Provides
  @Singleton
  fun provideLoginServiceFactory(serviceFactory: ServiceFactory): AuthApi {
    return serviceFactory.create(AuthApi::class.java)
  }

  @Provides
  @Singleton
  fun provideNetworkDataSource(
    loginService: AuthApi,
    gson: Gson,
    errorMessageProvider: ErrorMessageProvider,
  ): NetworkDataSource<AuthApi> {
    return NetworkDataSource(loginService, gson, errorMessageProvider)
  }

/*  @Provides
  @Singleton
  fun provideAuthRepository(api: AuthApi): AuthRepository {
    return AuthRepositoryImpl(api = api)
  }*/
  @Provides
  @Singleton
  fun provideAuthRepository(
    networkDataSource: NetworkDataSource<AuthApi>,
  ): AuthRepository {
    return AuthRepositoryImpl(networkDataSource)
  }
}
