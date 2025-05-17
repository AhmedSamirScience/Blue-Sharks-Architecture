package com.samir.bluearchitecture.offlinedata.di

import com.google.gson.Gson
import com.samir.bluearchitecture.data.main.encrDecrByKeyStore.CryptoHelper
import com.samir.bluearchitecture.data.main.remote.error.ErrorMessageProvider
import com.samir.bluearchitecture.data.main.remote.factory.ServiceFactory
import com.samir.bluearchitecture.data.main.remote.source.NetworkDataSource
import com.samir.bluearchitecture.offlinedata.data.dataSource.dao.LoginDao
import com.samir.bluearchitecture.offlinedata.data.remote.apiService.AuthApi
import com.samir.bluearchitecture.offlinedata.data.remote.repository.AuthRepositoryImpl
import com.samir.bluearchitecture.offlinedata.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ✅ Hilt DI module for the `offlinedata` feature's network and repository dependencies.
 *
 * This module provides:
 * - API interface instance (`AuthApi`)
 * - Retrofit-based `NetworkDataSource`
 * - Feature-specific `AuthRepositoryImpl` that integrates Room DAO + network fallback
 *
 * ---
 *
 * ### 💡 Why it's separated:
 * This module is specific to the `offlinedata` feature and helps avoid polluting
 * the core DI graph with feature-scoped implementations.
 *
 * ---
 *
 * ### 🔐 Singleton Scope:
 * All provided dependencies are `@Singleton`-scoped to optimize reuse across the app lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModuleOfflineData {

  /**
   * Provides the implementation of the [AuthApi] Retrofit service
   * using the shared [ServiceFactory].
   *
   * @param serviceFactory Factory to create typed Retrofit services.
   */
  @Provides
  @Singleton
  fun provideLoginServiceFactoryOffline(serviceFactory: ServiceFactory): AuthApi {
    return serviceFactory.create(AuthApi::class.java)
  }

  /**
   * Provides a generic [NetworkDataSource] wrapper around the [AuthApi] interface.
   *
   * It handles:
   * - Response parsing
   * - Error mapping
   * - Logging/interception (if configured in core)
   *
   * @param loginService The API interface instance.
   * @param gson Shared Gson instance for JSON parsing.
   * @param errorMessageProvider Maps technical errors to user-facing messages.
   */
  @Provides
  @Singleton
  fun provideNetworkDataSourceOffline(
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

  /**
   * Provides the offline-enabled implementation of [AuthRepository].
   *
   * Combines Room-based [LoginDao] and network-based [NetworkDataSource]
   * to support both offline and online login flows.
   *
   * @param networkDataSource Network handler for API calls.
   * @param loginDao Room DAO for local login caching and retrieval.
   */
  @Provides
  @Singleton
  fun provideAuthRepositoryOffline(
    networkDataSource: NetworkDataSource<AuthApi>,
    loginDao: LoginDao,
    cryptoHelper: CryptoHelper,
  ): AuthRepository {
    return AuthRepositoryImpl(networkDataSource, loginDao, cryptoHelper)
  }
}
