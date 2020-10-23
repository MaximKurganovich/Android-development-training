package com.skillbox.a14homework.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.a14homework.CelestialBodies

class ListAdapter(
    onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<CelestialBodies>(CelestialBodiesDiffUtilCallback()) {

    // В дополнительном конструкторе проверяется какой адаптер может обработать элемент
    init {
        delegatesManager.addDelegate(StarAdapterDelegate(onItemClick)).addDelegate(
            PlanetAdapterDelegate(onItemClick)).addDelegate(ImageGridAdapterDelegate()
        )
    }

//    Класс, который определяет правила сравнения элементов. Необходим для работы DiffUtil
    class CelestialBodiesDiffUtilCallback : DiffUtil.ItemCallback<CelestialBodies>() {

//    Сравнивает два элемента на основании какого-либо индентификатора. В данном случае сравнение идет на основе id
        override fun areItemsTheSame(oldItem: CelestialBodies, newItem: CelestialBodies): Boolean {
            return when {
                oldItem is CelestialBodies.Planet && newItem is CelestialBodies.Planet -> oldItem.id == newItem.id
                oldItem is CelestialBodies.Star && newItem is CelestialBodies.Star -> oldItem.id == newItem.id
                oldItem is CelestialBodies.ImagePlanet && newItem is CelestialBodies.ImagePlanet -> oldItem == newItem
                else -> false
            }
        }

//    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(
            oldItem: CelestialBodies,
            newItem: CelestialBodies
        ): Boolean {
            return oldItem == newItem
        }
    }
}