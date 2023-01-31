package com.vishal.roomfulldemo.viewmodel.fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.roomfulldemo.database.Category
import com.vishal.roomfulldemo.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryListViewModel(applicationContext: Context) : ViewModel() {
    var allCategoryLive = MutableLiveData<ArrayList<Category>>()
    var allCategory = ArrayList<Category>()

    init {
        viewModelScope.launch {
            allCategoryLive.postValue(CategoryRepository().getAllCategory(applicationContext) as ArrayList<Category>?)
        }
    }

}