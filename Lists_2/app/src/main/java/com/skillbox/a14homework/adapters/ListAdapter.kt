package com.skillbox.a14homework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate

class ListAdapter(
    onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<CelestialBodies>(CelestialBodiesDiffUtilCallback()) {

    // В дополнительном конструкторе проверяется какой адаптер может обработать элемент
    init {
        delegatesManager.addDelegate(StarAdapterDelegate(onItemClick)).addDelegate(PlanetAdapterDelegate(onItemClick))
    }

    class CelestialBodiesDiffUtilCallback: DiffUtil.ItemCallback<CelestialBodies>() {
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
    companion object {
        private const val TYPE_PLANET = 1
        private const val TYPE_STAR = 2
        private const val TYPE_EMPTY = 10
    }

}