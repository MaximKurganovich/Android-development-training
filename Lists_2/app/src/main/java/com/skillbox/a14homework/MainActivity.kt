package com.skillbox.a14homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skillbox.a14homework.linear_layout_manager.ListOfCelestialBodiesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, MainFragment()).addToBackStack("MainFragment").commit()
        }
    }
}