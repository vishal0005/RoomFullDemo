package com.vishal.roomfulldemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {

    @Query("select * from items")
    suspend fun getAllItem(): List<Items>

    @Insert
    suspend fun addItem(item: Items) : Long

    @Delete
    suspend fun deleteItem(item: Items)

    @Update
    suspend fun updateItem(item: Items)

}