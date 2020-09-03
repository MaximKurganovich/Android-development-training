package com.skillbox.a14homework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_of_celestial_bodies_fragment.*


class ListOfCelestialBodiesFragment : Fragment(R.layout.list_of_celestial_bodies_fragment),
    AddNewElement {

    private var celestialBodies = listOf(
        CelestialBodies.Planet(
            name = "Меркурий",
            diameter = 4870,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/3/30/Mercury_in_color_-_Prockter07_centered.jpg",
            dayLength = 58.65
        ),
        CelestialBodies.Planet(
            name = "Венера",
            diameter = 12100,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/8/85/Venus_globe.jpg",
            dayLength = 243.0
        ),
        CelestialBodies.Planet(
            name = "Земля",
            diameter = 12742,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/0/0d/Africa_and_Europe_from_a_Million_Miles_Away.png",
            dayLength = 1.0
        ),
        CelestialBodies.Planet(
            name = "Марс",
            diameter = 6670,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/0/0e/Tharsis_and_Valles_Marineris_-_Mars_Orbiter_Mission_%2830055660701%29.png",
            dayLength = 1.02
        ),
        CelestialBodies.Planet(
            name = "Юпитер",
            diameter = 143760,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/5/50/Jupiter%2C_image_taken_by_NASA%27s_Hubble_Space_Telescope%2C_June_2019_-_Edited.jpg",
            dayLength = 0.41
        ),
        CelestialBodies.Planet(
            name = "Сатурн",
            diameter = 120420,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/c/c1/Saturn_-_April_25_2016_%2837612580000%29.png",
            dayLength = 0.44
        ),
        CelestialBodies.Planet(
            name = "Уран",
            diameter = 51300,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/b/bb/Uranus.jpg",
            dayLength = 0.72
        ),
        CelestialBodies.Planet(
            name = "Нептун",
            diameter = 49500,
            avatarLink = "https://vunderkind.info/wp-content/uploads/2012/09/neptun.jpg",
            dayLength = 0.74
        ),
        CelestialBodies.Star(
            name = "Солнце",
            surfaceTemperature = 5500,
            avatarLink = "https://upload.wikimedia.org/wikipedia/commons/b/b4/The_Sun_by_the_Atmospheric_Imaging_Assembly_of_NASA%27s_Solar_Dynamics_Observatory_-_20100819.jpg"
        )
    )

    private var celestialBodiesAdapter: ListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ЭТОТ АТРИБУТ ПОМОГ СОХРАНИТЬ СПИСОК ПРИ ПОВОРОТЕ ЭКРАНА
        retainInstance = true
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("onActivityCreated", "onActivityCreated ${hashCode()}")
        initList()
        addFab.setOnClickListener { addCelestialBodies() }
        celestialBodiesAdapter?.updateList(celestialBodies)
        celestialBodiesAdapter?.notifyDataSetChanged()
    }

    private fun addCelestialBodies() {
        DialogForAddingAnItem().show(childFragmentManager, "DialogForAddingAnItem")
    }

    private fun initList() {
        celestialBodiesAdapter =
            ListAdapter { position -> deleteCelestialBodies(position = position) }
        with(recycleViewCelestialBodies) {
            adapter = celestialBodiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteCelestialBodies(position: Int) {
        celestialBodies =
            celestialBodies.filterIndexed { index, _ -> index != position }
        emptyList()
        celestialBodiesAdapter?.updateList(celestialBodies)
        celestialBodiesAdapter?.notifyItemRemoved(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        celestialBodiesAdapter = null
    }

    private fun emptyList() {
        if (celestialBodies.isEmpty()) {
            recycleViewCelestialBodies.visibility = View.GONE
            textViewEmptyList.visibility = View.VISIBLE
        } else {
            recycleViewCelestialBodies.visibility = View.VISIBLE
            textViewEmptyList.visibility = View.GONE
        }
    }

    override fun addNewElement(item: CelestialBodies) {
        celestialBodies = listOf(item) + celestialBodies
        println(celestialBodies)
        emptyList()
        celestialBodiesAdapter?.updateList(celestialBodies)
        celestialBodiesAdapter?.notifyItemInserted(0)
        recycleViewCelestialBodies.scrollToPosition(0)
    }
}