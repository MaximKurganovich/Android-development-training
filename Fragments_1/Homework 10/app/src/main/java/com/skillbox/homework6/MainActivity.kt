package com.skillbox.homework6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addLoginFragment()
    }

    override fun onBackPressed() {
        val fragmentMain = supportFragmentManager.findFragmentByTag("TAG_MAIN_FRAGMENT")

        if (fragmentMain != null && fragmentMain.childFragmentManager.backStackEntryCount > 0) {
            fragmentMain.childFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


    private fun addLoginFragment() {
        supportFragmentManager.beginTransaction().add(R.id.mainContainer, LoginFragment()).commit()
    }

}
