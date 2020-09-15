package com.skillbox.a14homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.skillbox.a14homework.linear_layout_manager.ListOfCelestialBodiesFragment
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


class SharedViewModel : ViewModel() {
    private val selected = MutableLiveData<Int>()
    fun select(item: Int) {
        selected.value = item
    }

    fun getSelected(): LiveData<Int> {
        return selected
    }
}

class MainFragment : Fragment(R.layout.fragment_main) {

//    ViewModel
    private var model: SharedViewModel = ViewModelProviders.of(Objects.requireNonNull(activity)!!).get(SharedViewModel::class.java)


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

    private fun createLayoutManager(type: Int) {
        model.select(type)
        childFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, ListOfCelestialBodiesFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }

    companion object {
        private const val KEY = "key"
        private const val LINEAR_LAYOUT_MANAGER = 1
        private const val GRID_LAYOUT_MANAGER = 2
        private const val STAGGERED_GRID_LAYOUT_MANAGER = 3
    }

}