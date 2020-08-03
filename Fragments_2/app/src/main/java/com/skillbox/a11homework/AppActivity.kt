package com.skillbox.a11homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) addMainFragment()
    }

    private fun addMainFragment () {
        supportFragmentManager.beginTransaction().add(R.id.activityContainer, MainFragment())
    }

}