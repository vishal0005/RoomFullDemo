package com.vishal.roomfulldemo.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.roomfulldemo.database.Items
import com.vishal.roomfulldemo.repository.ItemRepository
import com.vishal.roomfulldemo.utilitys.MyActions
import kotlinx.coroutines.launch
import java.io.File

class AddItemsViewModel(context: Context) : ViewModel() {
    var context = context
    var items: MutableLiveData<Items> = MutableLiveData(Items(0, "", -1, "", ""))
    var itemAddListener: MutableLiveData<Boolean> = MutableLiveData()
    var commonListener: MutableLiveData<Int> = MutableLiveData()

    fun addItemsClick(view: View) {
        var myItem = items.value
        viewModelScope.launch {
            myItem?.let {
                itemAddListener.postValue(ItemRepository().addItem(context, it))
            }
        }
    }

    fun onImageClick(view: View) {
        commonListener.postValue(MyActions.PICK_IMAGE)
    }

    fun updateImageFile(saveFile: File?) {
        if (saveFile != null) {
            items.value!!.image = saveFile.path
        } else {

        }

    }

}