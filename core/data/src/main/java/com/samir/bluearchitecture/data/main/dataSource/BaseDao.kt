package com.samir.bluearchitecture.data.main.dataSource

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * ✅ Generic base interface for all Room DAOs.
 *
 * Provides a set of common CRUD operations that can be reused across
 * different Room DAO implementations via inheritance.
 *
 * ---
 *
 * ### 💡 Usage:
 * Extend this interface in feature-specific DAOs:
 * ```
 * @Dao
 * interface LoginDao : BaseDao<LoginEntity> {
 *     // additional @Query methods here
 * }
 * ```
 *
 * @param T The type of the entity being persisted.
 */
interface BaseDao<T> {

  /**
   * Inserts a single item into the database.
   * If an item with the same primary key exists, it will be replaced.
   *
   * @param item The entity to insert or replace.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(item: T)

  /**
   * Inserts a list of items into the database.
   * If any item has a conflict on the primary key, it will be replaced.
   *
   * @param items The list of entities to insert or replace.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(items: List<T>)

  /**
   * Updates an existing item in the database.
   * The item must already exist and have a matching primary key.
   *
   * @param item The entity to update.
   */
  @Update
  suspend fun update(item: T)

  /**
   * Deletes an item from the database.
   *
   * @param item The entity to delete.
   */
  @Delete
  suspend fun delete(item: T)
}
