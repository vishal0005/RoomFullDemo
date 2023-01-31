package com.vishal.roomfulldemo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginUser::class, Category::class, Items::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao
}