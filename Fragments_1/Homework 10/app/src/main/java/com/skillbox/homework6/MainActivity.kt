package com.skillbox.homework6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addLoginFragment()
    }


    private fun addLoginFragment() {
        supportFragmentManager.beginTransaction().add(R.id.mainContainer, LoginFragment()).commit()
    }

}
