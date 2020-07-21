package com.skillbox.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.main_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addListFragment()
    }

    private fun addListFragment() {
        val trans =
            childFragmentManager.beginTransaction().add(R.id.mainFragmentContainer, ListFragment())
        trans.addToBackStack(null)
        trans.commit()
    }
}