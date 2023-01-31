package com.vishal.roomfulldemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("select * from category")
    suspend fun getAllCategory(): List<Category>

    @Insert
    suspend fun addCategory(category: Category)

    @Insert
    suspend fun addCategory(category: ArrayList<Category>)

    @Delete
    suspend fun deleteCategory(category: Category)

}