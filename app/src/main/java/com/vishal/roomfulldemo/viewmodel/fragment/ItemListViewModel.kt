package com.vishal.roomfulldemo.viewmodel.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.roomfulldemo.database.Category
import com.vishal.roomfulldemo.database.Items
import com.vishal.roomfulldemo.repository.ItemRepository
import com.vishal.roomfulldemo.ui.AddItemsPage
import com.vishal.roomfulldemo.ui.fragment.ItemListFrag
import com.vishal.roomfulldemo.utilitys.MyActions
import kotlinx.coroutines.launch

class ItemListViewModel(context: Context) : ViewModel() {
    var context = context
    var allItemLiveData = MutableLiveData<ArrayList<Items>>()
    var allItems = ArrayList<Items>()
    var commonListener: MutableLiveData<Int> = MutableLiveData()

    init {
        getAllItems()
    }

    fun getAllItems() {
        viewModelScope.launch {
            allItemLiveData.postValue(ItemRepository().getAllItemList(context) as ArrayList<Items>?)
        }
    }

    fun onAddClick(view: View) {
        commonListener.postValue(MyActions.JUMP_ADD_ITEMS)
    }

}