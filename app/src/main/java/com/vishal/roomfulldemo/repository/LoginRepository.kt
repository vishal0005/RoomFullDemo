package com.vishal.roomfulldemo.repository

import android.content.Context
import android.util.Log
import com.vishal.roomfulldemo.database.Category
import com.vishal.roomfulldemo.database.DbHelper
import com.vishal.roomfulldemo.database.LoginUser
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginRepository {
    var TAG = LoginRepository::class.java.name

    fun getLoginUserList(applicationContext: Context, scope: CoroutineScope) {
        scope.launch {
            var allUser = DbHelper.getDatabase(applicationContext).loginDao().getAllUser()
        }
    }

    fun emptyThenAddUser(applicationContext: Context, viewModelScope: CoroutineScope) {
        viewModelScope.launch {
            var allUser = DbHelper.getDatabase(applicationContext).loginDao().getAllUser()
            if (allUser.isEmpty()) {
                DbHelper.getDatabase(applicationContext).loginDao()
                    .addUser(LoginUser(0, "vishal", "123456"))
            }
            Log.e(TAG, "total users : ${allUser.size}")
        }
    }

    fun emptyThenAddCategory(applicationContext: Context, viewModelScope: CoroutineScope) {
        viewModelScope.launch {
            var allCategory =
                DbHelper.getDatabase(applicationContext).categoryDao().getAllCategory()
            if (allCategory.isEmpty()) {
                var allNewCate = arrayListOf<Category>()
                allNewCate.add(Category(0, "Fruits"))
                allNewCate.add(Category(0, "Vegetables"))
                allNewCate.add(Category(0, "Chinese"))
                allNewCate.add(Category(0, "FastFood"))
                DbHelper.getDatabase(applicationContext).categoryDao()
                    .addCategory(allNewCate)
            }
        }
    }

    suspend fun getUserWithIdPassword(
        applicationContext: Context,
        userId: String,
        password: String
    ): LoginUser? {
        var user: LoginUser? = null
        var allUser =
            DbHelper.getDatabase(applicationContext).loginDao().getAllUser(userId, password)
        if (allUser.isNotEmpty()) {
            user = allUser[0]
        }
        return user
    }

}