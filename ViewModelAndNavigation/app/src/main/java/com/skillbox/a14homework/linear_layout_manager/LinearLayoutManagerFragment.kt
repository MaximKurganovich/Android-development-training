package com.skillbox.a14homework.linear_layout_manager

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.adapters.ListAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_of_celestial_bodies_fragment.*


class LinearLayoutManagerFragment :
    Fragment(R.layout.list_of_celestial_bodies_fragment), AddNewElement {

    //    Поле хранит класс, в котором хранятся данные для списка
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
            ListAdapter({ position ->
                openDetailsFragment(position)
            }, { position -> deleteCelestialBodies(position = position) })
        with(recycleViewCelestialBodies) {
            adapter = celestialBodiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
    }

    //    Метод открывает детальный фрагмент при нажатии на элемент
    private fun openDetailsFragment(position: Int) {
        val element = dataViewModel.celestialBodies.value?.get(position)
        var name: String = fragment.resources.getString(R.string.no_name)
        var id: Int = 0
        if (element is CelestialBodies.Planet) {
            id = element.id
            if (element.name != "") {
                name = element.name
            }
        }
        if (element is CelestialBodies.Star) {
            id = element.id
            if (element.name != "") {
                name = element.name
            }
        }
        val action =
            LinearLayoutManagerFragmentDirections.actionLinearLayoutManagerFragmentToDetailsFragment(
                name,
                id
            )
        findNavController().navigate(action)
    }

    //    Удаляет элемент при нажатии на него
    private fun deleteCelestialBodies(position: Int) {
        dataViewModel.deleteCelestialBodies(position)
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

    //    Метод следит за изменениями в списке (DataViewModel) и информирует о них адаптер
    //    Во второй части метода при удалении элемента 1 раз отображается тост. Об этом информирует DataViewModel
    private fun observeViewModelState() {
        dataViewModel.celestialBodies.observe(viewLifecycleOwner) { newElement ->
            celestialBodiesAdapter?.items = newElement
            emptyList()
        }

        dataViewModel.showToast.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                fragment.resources.getString(R.string.delete_element),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //    Метод принимает данные от диалогов и передает их в DataViewModel
    override fun addNewElement(item: CelestialBodies) {
        dataViewModel.addNewElement(item)
        recycleViewCelestialBodies.scrollToPosition(0)
    }
}