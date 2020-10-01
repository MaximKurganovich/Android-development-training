package com.skillbox.a16homework

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class Initialization: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}