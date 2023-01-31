package com.vishal.roomfulldemo.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.vishal.roomfulldemo.database.Category
import com.vishal.roomfulldemo.database.DbHelper

class CategoryRepository {

    suspend fun getAllCategory(
        applicationContext: Context,
    ): List<Category> {
        var db = DbHelper.getDatabase(applicationContext)
        var allCate = db.categoryDao().getAllCategory()
        return allCate
    }

}