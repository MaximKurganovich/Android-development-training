package com.skillbox.a14homework.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.a14homework.linear_layout_manager.CelestialBodies
import com.skillbox.a14homework.R
import com.skillbox.a14homework.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.for_list_star.*

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

    class StarHolder(override val containerView: View, onItemClick: (position: Int) -> Unit) :
        BaseHolder(containerView, onItemClick), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(star: CelestialBodies.Star) {
            bindInfo(star.name, star.avatarLink)
            textViewSurfaceTemperature.text =
                "${itemView.resources.getString(R.string.surfaceTemperature)} = ${star.surfaceTemperature}"
        }
    }

}