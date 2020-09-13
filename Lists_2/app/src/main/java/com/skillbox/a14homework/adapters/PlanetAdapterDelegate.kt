package com.skillbox.a14homework.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate

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

    class PlanetHolder(view: View, onItemClick: (position: Int) -> Unit) : BaseHolder(view, onItemClick) {
        private val diameterTextView: TextView = view.findViewById(R.id.textViewDiameter)
        private val dayLengthTextView: TextView = view.findViewById(R.id.textViewDayLength)

        @SuppressLint("SetTextI18n")
        fun bind(
            planet: CelestialBodies.Planet
        ) {
            bindInfo(planet.name, planet.avatarLink)
            diameterTextView.text = "${itemView.resources.getString(R.string.planetDiameter)} = ${planet.diameter} ${itemView.resources.getString(
                R.string.km
            )}"
            dayLengthTextView.text = "${itemView.resources.getString(R.string.dayLength)} = ${planet.dayLength} ${itemView.resources.getString(
                R.string.days
            )}"
        }
    }

}