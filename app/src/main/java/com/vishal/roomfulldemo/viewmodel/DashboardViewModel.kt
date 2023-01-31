package com.vishal.roomfulldemo.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishal.roomfulldemo.utilitys.MyActions

class DashboardViewModel : ViewModel() {

    var commonListener: MutableLiveData<Int> = MutableLiveData()

    fun onCategoryClick(view: View) {
        commonListener.postValue(MyActions.CATEGORY_CLICK)
    }

    fun onItemClick(view: View) {
        commonListener.postValue(MyActions.ITEMS_CLICK)
    }
}