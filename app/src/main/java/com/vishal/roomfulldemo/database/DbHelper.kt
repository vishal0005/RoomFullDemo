package com.vishal.roomfulldemo.database

import android.content.Context
import androidx.room.Room

object DbHelper {
    fun getDatabase(cn: Context): AppDatabase {
        var db = Room.databaseBuilder(
            cn, AppDatabase::class.java, "RoomFullDatabase"
        ).fallbackToDestructiveMigration().build()
        return db
    }
}