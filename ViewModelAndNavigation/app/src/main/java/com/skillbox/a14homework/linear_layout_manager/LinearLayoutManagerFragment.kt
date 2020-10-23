package com.skillbox.a14homework.linear_layout_manager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.adapters.ListAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.list_of_celestial_bodies_fragment.*


class LinearLayoutManagerFragment :
    Fragment(R.layout.list_of_celestial_bodies_fragment), AddNewElement {

    private val dataViewModel: DataViewModel by viewModels()


    //    Создается адаптер
    private var celestialBodiesAdapter: ListAdapter? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        addFab.setOnClickListener { addDialog() }
        observeViewModelState()
    }


    //    Метод вызывает диалог для добавления элемента
    private fun addDialog() {
        DialogForAddingAnItem().show(childFragmentManager, "DialogForAddingAnItem")
    }

    //    Адаптеру присваиваются атрибуты и указывается тип LayoutManager
    private fun initList() {
        celestialBodiesAdapter =
            ListAdapter { position ->
                val action = LinearLayoutManagerFragmentDirections.actionLinearLayoutManagerFragmentToDetailsFragment()
                findNavController().navigate(action)
//                deleteCelestialBodies(position = position)
                }
        with(recycleViewCelestialBodies) {
            adapter = celestialBodiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
    }

    //    Удаляет элемент при нажатии на него
    private fun deleteCelestialBodies(position: Int) {
        dataViewModel.deleteCelestialBodies(position)
        emptyList()
    }

    override fun onDestroy() {
        super.onDestroy()
        celestialBodiesAdapter = null
    }

    //    Если список пуст, то recyclerView скрывается и появляется textView с надписью "Список пуст"
    private fun emptyList() {
        if (dataViewModel.celestialBodies.value.isNullOrEmpty()) {
            recycleViewCelestialBodies.visibility = View.GONE
            textViewEmptyList.visibility = View.VISIBLE
        } else {
            recycleViewCelestialBodies.visibility = View.VISIBLE
            textViewEmptyList.visibility = View.GONE
        }
    }

    private fun observeViewModelState() {
        dataViewModel.celestialBodies.observe(viewLifecycleOwner) { newElement ->
            celestialBodiesAdapter?.items = newElement
        }
    }

    override fun addNewElement(item: CelestialBodies) {
        dataViewModel.addNewElement(item)
        recycleViewCelestialBodies.scrollToPosition(0)
    }
}