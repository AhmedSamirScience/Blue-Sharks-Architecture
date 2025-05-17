package com.samir.bluearchitecture.offlinedata.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Query
import com.samir.bluearchitecture.data.main.dataSource.BaseDao
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity

/**
 * ✅ DAO interface for accessing and manipulating `LoginEntity` records in the local Room database.
 *
 * Inherits standard CRUD operations from [BaseDao], and adds custom queries for login-specific use cases.
 *
 * ---
 *
 * ### 💡 Responsibilities:
 * - Insert/update/delete login entries via [BaseDao]
 * - Fetch the most recent login entry (used for offline login or auto-fill)
 * - Fetch all cached login entries (useful for admin/testing/debugging)
 */
@Dao
interface LoginDao : BaseDao<LoginEntity> {

  /**
   * Returns the most recently inserted login record from the local database.
   *
   * ### Use case:
   * - Offline login or session restoration
   * - UI auto-population with last known user
   *
   * @return The last inserted [LoginEntity], or null if no records exist.
   */
  @Query("SELECT * FROM login LIMIT 1")
  suspend fun getLastLogin(): LoginEntity?

  /**
   * Returns all login records stored in the database.
   *
   * ### Use case:
   * - Used for debugging, testing, or displaying a login history list.
   *
   * @return List of all [LoginEntity] rows in the table.
   */
  @Query("SELECT * FROM login")
  suspend fun getAllLogins(): List<LoginEntity>
}
