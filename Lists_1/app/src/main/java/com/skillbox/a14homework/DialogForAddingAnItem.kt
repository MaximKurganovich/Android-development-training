package com.skillbox.a14homework

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.layout_for_dialogue_planet.*
import kotlinx.android.synthetic.main.layout_for_dialogue_planet.editLinkToAvatar
import kotlinx.android.synthetic.main.layout_for_dialogue_star.*

interface AddNewElement {
    fun addNewElement(item: CelestialBodies)
}

class DialogForAddingAnItem : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).setTitle("Добавляем:")
            .setItems(R.array.listOfCelestialBodies) { dialog, which -> addDialog(which) }.create()
    }

    private fun addDialog(which: Int) {
        when (which) {
            0 -> DialogForAddingAStar().show(childFragmentManager, TYPE_STAR)
            1 -> DialogForAddingAPlanet().show(childFragmentManager, TYPE_PLANET)
        }
    }

    companion object {
        private const val TYPE_PLANET = "AddPlanetDialog"
        private const val TYPE_STAR = "AddStarDialog"
    }
}

class DialogForAddingAStar : DialogFragment() {

    private var mListener: AddNewElement? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        Log.d("DialogForAddingAStar", "DialogForAddingAStar ${hashCode()}")
        return AlertDialog.Builder(requireContext()).setView(R.layout.layout_for_dialogue_star)
            .setPositiveButton(
                "Добавить"
            ) { _, _ ->
                mListener?.addNewElement(addNewStar())
            }.setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }.create()
    }

    private fun addNewStar(): CelestialBodies.Star {
//        val inflater = activity!!.layoutInflater
//
//        val view = inflater.inflate(R.layout.layout_for_dialogue_star, null)
        val nameStar = editNameStar.toString()
        val linkToAvatar = editLinkToAvatar.toString()
        val surfaceTemperature =
            editSurfacTemperature.toString().toInt()

        return CelestialBodies.Star(
            name = nameStar,
            surfaceTemperature = surfaceTemperature,
            avatarLink = linkToAvatar
        )
    }
}

class DialogForAddingAPlanet : DialogFragment() {
    private var mListener: AddNewElement? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).setView(R.layout.layout_for_dialogue_planet)
            .setPositiveButton(
                "Добавить"
            ) { _, _ ->
                mListener?.addNewElement(addNewPlanet())
            }.setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }.create()
    }

    private fun addNewPlanet(): CelestialBodies.Planet {
        val inflater = activity!!.layoutInflater

        val view = inflater.inflate(R.layout.layout_for_dialogue_planet, null)
        val nameStar = editNamePlanet.toString()
        val linkToAvatar = editLinkToAvatar.toString()
        val planetDiameter =
            editPlanetDiameter.toString().toInt()
        val dayLength = editDayLength.toString().toDouble()

        return CelestialBodies.Planet(
            name = nameStar,
            avatarLink = linkToAvatar,
            diameter = planetDiameter,
            dayLength = dayLength
        )
    }
}