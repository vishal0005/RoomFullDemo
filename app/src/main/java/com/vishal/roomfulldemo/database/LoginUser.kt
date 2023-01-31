package com.vishal.roomfulldemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginUser(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo var userId: String,
    @ColumnInfo var password: String
)
