package com.samir.bluearchitecture.offlinedata.di

/**
 * 🧩 DependencyBridgeModule
 *
 * ---
 * ### ✅ Purpose:
 * This module acts as a **bridge** to include and expose core DI modules from the `data` layer
 * (such as networking and locale support) into the `remotedata` feature's Hilt dependency graph.
 *
 * ---
 * ### 💡 Why It's Needed:
 * - In **release builds**, R8 may strip modules that are not explicitly installed into a component.
 * - If `NetworkModuleOfflineData`, `InterceptorsModule`, or `LocaleModule` are only defined in the `data` layer,
 *   and not referenced explicitly in this module, their bindings (e.g., `ErrorMessageProvider`, `Gson`, etc.)
 *   may be missing at runtime → causing crashes.
 *
 * ---
 * ### 🔗 How It Works:
 * - By using `@Module(includes = [...])`, this object **re-exports core modules** into the SingletonComponent.
 * - It ensures that all the required bindings are **retained during compilation and R8 optimization**.
 *
 * ---
 * ### 📦 Included Modules:
 * - `NetworkModuleOfflineData` – Provides Retrofit services, `NetworkDataSource`, etc.
 * - `InterceptorsModule` – Provides OkHttp interceptors like logging or headers
 * - `LocaleModule` – Provides localization-aware components like `ErrorMessageProvider`
 *
 * ---
 * ### 🧪 When to Use:
 * - Use this pattern whenever a feature module **depends on bindings from another module**,
 *   and you want to **explicitly expose them in the dependency graph**.
 */
/*@Module(
  includes = [
    com.samir.bluearchitecture.data.main.remote.di.NetworkModule::class,
    com.samir.bluearchitecture.data.main.remote.di.InterceptorsModule::class,
    com.samir.bluearchitecture.data.main.remote.di.LocaleModule::class,
    com.samir.bluearchitecture.data.main.remote.di.SecurityModule::class,
  ],
)
@InstallIn(SingletonComponent::class)
object DependencyBridgeModule*/
