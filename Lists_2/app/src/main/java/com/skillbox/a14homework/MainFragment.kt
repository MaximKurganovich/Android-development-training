package com.skillbox.a14homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        buttonLinearLayoutManager.setOnClickListener { createLayoutManager(LINEAR_LAYOUT_MANAGER) }
//        buttonGridLayoutManager.setOnClickListener { createLayoutManager(GRID_LAYOUT_MANAGER) }
//        buttonStaggeredGridLayoutManager.setOnClickListener {
//            createLayoutManager(
//                STAGGERED_GRID_LAYOUT_MANAGER
//            )
//        }
//    }

//    private fun createLayoutManager(type: Int) {
//        childFragmentManager.beginTransaction()
//            .replace(R.id.mainContainer, ListOfCelestialBodiesFragment(type))
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
//    }

    companion object {
        private const val LINEAR_LAYOUT_MANAGER = 1
        private const val GRID_LAYOUT_MANAGER = 2
        private const val STAGGERED_GRID_LAYOUT_MANAGER = 3
    }

}