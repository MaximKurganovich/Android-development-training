package com.skillbox.a14homework.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.a14homework.linear_layout_manager.CelestialBodies

class ListAdapter(
    onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<CelestialBodies>(CelestialBodiesDiffUtilCallback()) {

    // В дополнительном конструкторе проверяется какой адаптер может обработать элемент
    init {
        delegatesManager.addDelegate(StarAdapterDelegate(onItemClick)).addDelegate(
            PlanetAdapterDelegate(onItemClick)
        )
    }

    class CelestialBodiesDiffUtilCallback : DiffUtil.ItemCallback<CelestialBodies>() {
        override fun areItemsTheSame(oldItem: CelestialBodies, newItem: CelestialBodies): Boolean {
            return when {
                oldItem is CelestialBodies.Planet && newItem is CelestialBodies.Planet -> oldItem == newItem
                oldItem is CelestialBodies.Star && newItem is CelestialBodies.Star -> oldItem == newItem
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: CelestialBodies,
            newItem: CelestialBodies
        ): Boolean {
            return oldItem == newItem
        }

    }
}