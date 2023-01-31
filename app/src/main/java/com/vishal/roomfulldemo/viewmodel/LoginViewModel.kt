package com.vishal.roomfulldemo.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.roomfulldemo.database.LoginUser
import com.vishal.roomfulldemo.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(applicationContext: Context) : ViewModel() {
    var applicationContext = applicationContext
    var userId = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")
    var showToast = MutableLiveData<String>("")
    var loginUpdate = MutableLiveData<LoginUser>()

    init {
        LoginRepository().emptyThenAddUser(applicationContext, viewModelScope)
    }

    fun onLoginClick(view: View) {
        if (userId.value!!.isNotEmpty() && password.value!!.isNotEmpty()) {
            viewModelScope.launch {
                var loginUSer: LoginUser? = LoginRepository().getUserWithIdPassword(
                    applicationContext,
                    userId.value!!,
                    password.value!!
                )
                if (loginUSer == null) {
                    showToast.postValue("Enter valid userid and password")
                } else {
                    LoginRepository().emptyThenAddCategory(applicationContext, viewModelScope)
                    loginUpdate.postValue(loginUSer)
                }
            }
        } else {
            showToast.postValue("enter both details")
        }
    }

}