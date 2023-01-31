package com.vishal.roomfulldemo.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.vishal.roomfulldemo.database.DbHelper
import com.vishal.roomfulldemo.database.Items

class ItemRepository {

    suspend fun getAllItemList(context: Context): List<Items> {
        var db = DbHelper.getDatabase(context)
        return db.itemDao().getAllItem()
    }

    suspend fun addItem(context: Context, items: Items): Boolean {
        var db = DbHelper.getDatabase(context)
        return db.itemDao().addItem(items) >= 0
    }

}