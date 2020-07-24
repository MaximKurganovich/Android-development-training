package com.skillbox.homework6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            addLoginFragment()
        }
    }

    override fun onBackPressed() {
        val fragmentMain = supportFragmentManager.findFragmentByTag("TAG_MAIN_FRAGMENT")
        if (fragmentMain != null && fragmentMain.childFragmentManager.backStackEntryCount > 0) {
            fragmentMain.childFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    //    Метод, который загружает фрагмент логина
    private fun addLoginFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, LoginFragment(), TAG_LOGIN_FRAGMENT).commit()
    }

    companion object {
        private const val TAG_LOGIN_FRAGMENT = "TAG_LOGIN_FRAGMENT"
    }

}
