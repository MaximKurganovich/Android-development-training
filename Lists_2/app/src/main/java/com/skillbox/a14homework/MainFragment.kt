package com.skillbox.a14homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.skillbox.a14homework.grid_layout_manager.GridLayoutManagerFragment
import com.skillbox.a14homework.linear_layout_manager.LinearLayoutManagerFragment
import com.skillbox.a14homework.staggered_grid_layout_manager_fragment.StaggeredGridLayoutManagerFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonLinearLayoutManager.setOnClickListener { createLayoutManager(LINEAR_LAYOUT_MANAGER) }
        buttonGridLayoutManager.setOnClickListener { createLayoutManager(GRID_LAYOUT_MANAGER) }
        buttonStaggeredGridLayoutManager.setOnClickListener {
            createLayoutManager(
                STAGGERED_GRID_LAYOUT_MANAGER
            )
        }
    }

    //    Создает необходимый фрагмент
    private fun createLayoutManager(type: Int) {
        val typeFragment = when (type) {
            1 -> LinearLayoutManagerFragment()
            2 -> GridLayoutManagerFragment()
            3 -> StaggeredGridLayoutManagerFragment()
            else -> error("")
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, typeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("MainFragment")
            .commit()
    }

    companion object {
        private const val LINEAR_LAYOUT_MANAGER = 1
        private const val GRID_LAYOUT_MANAGER = 2
        private const val STAGGERED_GRID_LAYOUT_MANAGER = 3
    }

}