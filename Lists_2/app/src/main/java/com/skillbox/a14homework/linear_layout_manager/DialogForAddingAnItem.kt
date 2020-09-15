package com.skillbox.a14homework.linear_layout_manager

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.skillbox.a14homework.R
import kotlinx.android.synthetic.main.layout_for_dialogue_planet.view.*
import kotlinx.android.synthetic.main.layout_for_dialogue_star.view.*
import kotlinx.android.synthetic.main.layout_for_dialogue_star.view.editLinkToAvatar

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

    private val mListener: AddNewElement?
        get() = parentFragment as? AddNewElement ?: activity as? AddNewElement

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        Log.d("DialogForAddingAStar", "DialogForAddingAStar ${hashCode()}")
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.layout_for_dialogue_star, null)

        return AlertDialog.Builder(requireContext()).setView(view)
            .setPositiveButton(
                "Добавить"
            ) { _, _ ->
                mListener?.addNewElement(addNewStar(view))
            }.setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }.create()
    }

    //Метод создает новый элемент "Звезда"
    private fun addNewStar(view: View): CelestialBodies.Star {
        val nameStar = view.editNameStar.text.toString()
        val linkToAvatar = view.editLinkToAvatar.text.toString()
        val surfaceTemperature =
            view.editSurfacTemperature.text.toString().toInt()

        return CelestialBodies.Star(
            name = nameStar,
            surfaceTemperature = surfaceTemperature,
            avatarLink = linkToAvatar,
            id = ListOfCelestialBodiesFragment().celestialBodies.size + 1
        )
    }
}

class DialogForAddingAPlanet : DialogFragment() {

    private val mListener: AddNewElement?
        get() = parentFragment as? AddNewElement ?: activity as? AddNewElement

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.layout_for_dialogue_planet, null)

        return AlertDialog.Builder(requireContext()).setView(view)
            .setPositiveButton(
                "Добавить"
            ) { _, _ ->
                mListener?.addNewElement(addNewPlanet(view))
            }.setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }.create()
    }

    //Метод создает элемент "Планета"
    private fun addNewPlanet(view: View): CelestialBodies.Planet {

        val nameStar = view.editNamePlanet.text.toString()
        val linkToAvatar = view.editLinkToAvatar.text.toString()
        val planetDiameter =
            view.editPlanetDiameter.text.toString().toInt()
        val dayLength = view.editDayLength.text.toString().toDouble()

        return CelestialBodies.Planet(
            name = nameStar,
            avatarLink = linkToAvatar,
            diameter = planetDiameter,
            dayLength = dayLength,
            id = ListOfCelestialBodiesFragment().celestialBodies.size + 1
        )
    }
}