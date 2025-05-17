package com.samir.bluearchitecture.offlinedata.data.dataSource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginEntity(
  @PrimaryKey val userId: String,
  val userName: String,
)
