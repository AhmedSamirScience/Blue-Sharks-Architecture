-keep class com.samir.bluearchitecture.data.main.remote.di.** { *; }
-keep class com.samir.bluearchitecture.data.main.remote.error.** { *; }
-keep class com.samir.bluearchitecture.data.main.remote.mapper.MapperKt { *; }
-keep class com.samir.bluearchitecture.data.main.remote.source.NetworkDataSource { *; }
-keep class com.samir.bluearchitecture.data.main.remote.factory.ServiceFactory { *; }
-keep class com.samir.bluearchitecture.data.main.remote.uiState.** { *; }
-keep class com.samir.bluearchitecture.data.main.dataSource.** { *; }
-keep class com.samir.bluearchitecture.data.main.encrDecrByKeyStore.CryptoHelper { *; }
-keepnames class com.samir.bluearchitecture.data.main.**

-keep class android.util.Log.** { *; }



-keep class com.samir.bluearchitecture.data.** { @dagger.* *; }
-keep @dagger.hilt.InstallIn class * { *; }

# Keep Hilt Modules
-keep class com.samir.bluearchitecture.data.**Module { *; }

# Keep classes used in @Provides
-keep class com.samir.bluearchitecture.data.** { @dagger.Provides *; }

# Keep @Inject constructors
-keep class com.samir.bluearchitecture.data.** { <init>(...); }