package com.skillbox.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.main_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

//    Если фрагмент запуще впервые, то в нем запускается первый вложенный фрагмент. Иначе восстанавливается
//    автоматически сохраненное состояние
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            addListFragment()
        }
    }

    private fun addListFragment() {
        val trans =
            childFragmentManager.beginTransaction().add(R.id.mainFragmentContainer, ListFragment())
        trans.commit()
    }
}