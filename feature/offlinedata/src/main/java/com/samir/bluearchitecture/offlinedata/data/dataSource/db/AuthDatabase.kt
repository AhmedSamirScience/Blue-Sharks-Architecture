package com.samir.bluearchitecture.offlinedata.data.dataSource.db

import androidx.room.Database
import com.samir.bluearchitecture.data.main.dataSource.BaseRoomDatabase
import com.samir.bluearchitecture.offlinedata.data.dataSource.dao.LoginDao
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity

/**
 * ✅ Room database for the `offlinedata` module.
 *
 * This class defines the local SQLite database schema and DAOs
 * required for persisting login-related entities.
 *
 * ---
 *
 * ### 💡 Usage:
 * - Provides a singleton instance via Hilt (see `RoomModule.kt`)
 * - Exposes `LoginDao` to interact with `LoginEntity` data
 * - Inherits base configuration from [BaseRoomDatabase]
 *
 * ---
 *
 * ### ⚠️ Versioning:
 * - `version = 2` indicates schema migration is in effect.
 * - If schema changes, update this version and provide a proper `Migration(...)`
 *   block or enable `fallbackToDestructiveMigration()` for dev-only environments.
 *
 * @see LoginDao
 * @see LoginEntity
 */
@Database(entities = [LoginEntity::class], version = 2)
abstract class AuthDatabase : BaseRoomDatabase() {

  /**
   * Provides access to `LoginDao`, which defines all login-related
   * CRUD operations for the `login` table.
   */
  abstract fun loginDao(): LoginDao
}
