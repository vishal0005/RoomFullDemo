package com.vishal.roomfulldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.vishal.roomfulldemo.ui.LoginPage

class SplashPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this,LoginPage::class.java))
            finish()
        },3000)
    }
}