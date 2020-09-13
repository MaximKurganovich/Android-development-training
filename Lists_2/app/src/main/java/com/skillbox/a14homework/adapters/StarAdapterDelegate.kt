package com.skillbox.a14homework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.a14homework.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate

class StarAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<CelestialBodies.Star, CelestialBodies, StarAdapterDelegate.StarHolder>() {

    override fun isForViewType(
        item: CelestialBodies,
        items: MutableList<CelestialBodies>,
        position: Int
    ): Boolean {
        return item is CelestialBodies.Star
    }

    override fun onCreateViewHolder(parent: ViewGroup): StarHolder {
        return StarHolder(parent.inflate(R.layout.for_list_star), onItemClick)
    }

    override fun onBindViewHolder(
        item: CelestialBodies.Star,
        holder: StarHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class StarHolder(view: View, onItemClick: (position: Int) -> Unit) :
        BaseHolder(view, onItemClick) {

        private val surfaceTemperatureTextView: TextView =
            view.findViewById(R.id.textViewSurfaceTemperature)

        fun bind(star: CelestialBodies.Star) {
            bindInfo(star.name, star.avatarLink)
            surfaceTemperatureTextView.text =
                "${itemView.resources.getString(R.string.surfaceTemperature)} = ${star.surfaceTemperature}"
        }
    }

}