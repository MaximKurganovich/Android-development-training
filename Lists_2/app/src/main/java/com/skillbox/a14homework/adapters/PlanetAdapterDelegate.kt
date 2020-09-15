package com.skillbox.a14homework.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.a14homework.linear_layout_manager.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.for_list_planet.*

class PlanetAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<CelestialBodies.Planet, CelestialBodies, PlanetAdapterDelegate.PlanetHolder>() {

    override fun isForViewType(
        item: CelestialBodies,
        items: MutableList<CelestialBodies>,
        position: Int
    ): Boolean {
        return item is CelestialBodies.Planet
    }

    override fun onCreateViewHolder(parent: ViewGroup): PlanetHolder {
        return PlanetHolder(parent.inflate(R.layout.for_list_planet), onItemClick)
    }

    override fun onBindViewHolder(
        item: CelestialBodies.Planet,
        holder: PlanetHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class PlanetHolder(override val containerView: View, onItemClick: (position: Int) -> Unit) :
        BaseHolder(containerView, onItemClick), LayoutContainer {

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