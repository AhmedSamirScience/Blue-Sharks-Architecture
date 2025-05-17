package com.samir.bluearchitecture.offlinedata.di

import android.content.Context
import androidx.room.Room
import com.samir.bluearchitecture.data.main.dataSource.BaseRoomDatabase
import com.samir.bluearchitecture.offlinedata.data.dataSource.dao.LoginDao
import com.samir.bluearchitecture.offlinedata.data.dataSource.db.AuthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ✅ Hilt DI module for Room database components in the `offlinedata` feature.
 *
 * Provides singleton-scoped dependencies for:
 * - Room database instance ([AuthDatabase])
 * - Feature-specific DAO ([LoginDao])
 *
 * ---
 *
 * ### 💡 Best Practice:
 * - Scoped to `SingletonComponent` to ensure one instance of DB and DAO
 * - Uses `.fallbackToDestructiveMigration()` for schema version changes (dev-safe)
 *   > Replace with `.addMigrations(...)` before release to preserve user data.
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  /**
   * Provides the singleton instance of [AuthDatabase], the Room database
   * that stores login-related entities locally for offline access.
   *
   * @param context Application context required by Room.
   * @return Singleton instance of the Room database.
   */
  @Provides
  @Singleton
  fun provideAuthDatabase(@ApplicationContext context: Context): AuthDatabase {
    return Room.databaseBuilder(context, AuthDatabase::class.java, "auth_db")
      .addCallback(BaseRoomDatabase.defaultCallback)
      .fallbackToDestructiveMigration()
      .build()
  }

  /**
   * Provides the DAO for accessing login-related data from the local database.
   *
   * @param db The injected [AuthDatabase] instance.
   * @return The [LoginDao] interface used for DB operations.
   */
  @Provides
  fun provideLoginDao(db: AuthDatabase): LoginDao = db.loginDao()
}
