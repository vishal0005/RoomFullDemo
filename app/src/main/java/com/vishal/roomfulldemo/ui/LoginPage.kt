package com.vishal.roomfulldemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vishal.roomfulldemo.Helper
import com.vishal.roomfulldemo.databinding.LoginPageBinding
import com.vishal.roomfulldemo.viewmodel.LoginViewModel

class LoginPage : AppCompatActivity() {
    lateinit var binding: LoginPageBinding
    lateinit var viewModel: LoginViewModel
    var cn = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = LoginViewModel(applicationContext)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.showToast.observe(this) { it ->
            Helper.toastMsg(cn, it)
        }

        viewModel.loginUpdate.observe(this) { it ->
            Helper.toastMsg(cn, "Login successfully")
            startActivity(Intent(cn, DashboardPage::class.java))
        }
    }
}