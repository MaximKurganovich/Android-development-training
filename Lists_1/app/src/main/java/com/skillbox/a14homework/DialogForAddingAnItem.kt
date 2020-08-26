package com.skillbox.a14homework

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.layout_for_dialogue_planet.*
import kotlinx.android.synthetic.main.layout_for_dialogue_planet.editLinkToAvatar
import kotlinx.android.synthetic.main.layout_for_dialogue_star.*

interface AddNewElement {
    fun addNewElement(item: CelestialBodies)
}

open class DialogForAddingAnItem : DialogFragment() {

    //Создаем диалог, в котором пользователю будет дан выбран какой элемент он хочет добавить
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).setTitle("Добавляем:")
            .setItems(R.array.listOfCelestialBodies) { dialog, which -> addDialog(which) }.create()
    }

    //В зависимости от выбора пользователя в первом диалоге создается второй диалог с кастомной разметкой,
    // в который вносятся параметры нового элемента
    private fun addDialog(which: Int) {
        when (which) {
            0 -> fragmentManager?.let { DialogForAddingAStar().show(it, TYPE_STAR) }
            1 -> fragmentManager?.let { DialogForAddingAPlanet().show(it, TYPE_PLANET) }
        }
    }

    companion object {
        private const val TYPE_PLANET = "AddPlanetDialog"
        private const val TYPE_STAR = "AddStarDialog"
    }
}

// Ниже представлены два класса, которые нужны, чтобы вторые диалоги сохраняли свое состояние при повороте экрана
class DialogForAddingAStar : DialogForAddingAnItem() {

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

    //Метод создает новый элемент "Звезда"
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

    //Метод создает элемент "Планета"
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