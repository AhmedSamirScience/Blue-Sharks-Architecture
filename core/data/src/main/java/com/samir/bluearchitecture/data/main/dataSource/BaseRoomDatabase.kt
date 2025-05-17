package com.samir.bluearchitecture.data.main.dataSource

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * ✅ Abstract base class for Room databases.
 *
 * Provides a shared foundation for all Room database implementations across modules.
 * It defines a reusable `defaultCallback` that can be attached during database
 * creation to handle events like `onCreate()` (e.g., for logging, seeding data).
 *
 * ---
 *
 * ### 💡 Usage:
 * Extend this base class when creating your feature-specific database:
 *
 * ```kotlin
 * @Database(entities = [...], version = X)
 * abstract class AuthDatabase : BaseRoomDatabase() {
 *     abstract fun loginDao(): LoginDao
 * }
 * ```
 *
 * Then in your Hilt module:
 * ```kotlin
 * Room.databaseBuilder(context, AuthDatabase::class.java, "auth_db")
 *     .addCallback(BaseRoomDatabase.defaultCallback)
 *     .build()
 * ```
 */
abstract class BaseRoomDatabase : RoomDatabase() {

  /**
   * Default Room `Callback` used during database initialization.
   *
   * Useful for:
   * - Logging database creation
   * - Seeding initial data
   * - Performing setup queries
   */
  companion object {
    val defaultCallback = object : Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Optional: Insert initial data, setup schema, or log

      }
    }
  }
}
