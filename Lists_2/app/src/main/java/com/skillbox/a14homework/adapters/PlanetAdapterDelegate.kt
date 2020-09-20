package com.skillbox.a14homework.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.for_list_planet.*

/*Класс принимает 3 параметра:
    1. Тип элемента, которым управляет данный delegate adapter. Должен быть подтипов второго параметра
    2. Общий тип списка
    3. Тип ViewHolder
 */
class PlanetAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<CelestialBodies.Planet, CelestialBodies, PlanetAdapterDelegate.PlanetHolder>() {

//    Метод возвращает true, если поступивший элемент может быть обработан текущим адаптером
    override fun isForViewType(
        item: CelestialBodies,
        items: MutableList<CelestialBodies>,
        position: Int
    ): Boolean {
        return item is CelestialBodies.Planet
    }

//    Создает VIewHolder
    override fun onCreateViewHolder(parent: ViewGroup): PlanetHolder {
        return PlanetHolder(parent.inflate(R.layout.for_list_planet), onItemClick)
    }

//    Связывает ViewHolder и элементы
    override fun onBindViewHolder(
        item: CelestialBodies.Planet,
        holder: PlanetHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

/*    Класс Holder, который наследуется от интерфейса LayoutContainer
      Благодаря интерфейсу холдер знает как вью корневая и можно получать вью из корневой из кэша, что повышает скорость работы
 */
    class PlanetHolder(override val containerView: View, onItemClick: (position: Int) -> Unit) :
        BaseHolder(containerView, onItemClick), LayoutContainer {

//    Метод утснавливает в оставшиеся поля значения
        @SuppressLint("SetTextI18n")
        fun bind(
            planet: CelestialBodies.Planet
        ) {
            bindInfo(planet.name, planet.avatarLink)
            textViewDiameter.text =
                "${itemView.resources.getString(R.string.planetDiameter)} = ${planet.diameter} ${
                    itemView.resources.getString(
                        R.string.km
                    )
                }"
            textViewDayLength.text =
                "${itemView.resources.getString(R.string.dayLength)} = ${planet.dayLength} ${
                    itemView.resources.getString(
                        R.string.days
                    )
                }"
        }
    }

}