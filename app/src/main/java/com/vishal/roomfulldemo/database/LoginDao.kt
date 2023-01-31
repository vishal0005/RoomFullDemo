package com.vishal.roomfulldemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDao {

    @Query("select * from LoginUser")
    suspend fun getAllUser(): List<LoginUser>

    @Query("select * from LoginUser where userId= :useid And password= :pass")
    suspend fun getAllUser(useid: String, pass: String): List<LoginUser>

    @Insert()
    suspend fun addUser(user: LoginUser)

    @Delete()
    suspend fun deleteUser(user: LoginUser)

}