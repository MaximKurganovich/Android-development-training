package com.skillbox.a14homework

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var celestialBodies: List<CelestialBodies> = emptyList()

    //Метод, который позволяет определить какой тип элемента необходимо отобразить.
    //Эта информация передается в метод onCreateViewHolder
    override fun getItemViewType(position: Int): Int {
        return when (celestialBodies[position]) {
            is CelestialBodies.Planet -> TYPE_PLANET
            is CelestialBodies.Star -> TYPE_STAR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PLANET -> PlanetHolder(parent.inflate(R.layout.for_list_planet), onItemClick)
            TYPE_STAR -> StarHolder(parent.inflate(R.layout.for_list_star), onItemClick)
            else -> error("Данные не найдены")
        }
    }

    override fun getItemCount(): Int {
        return celestialBodies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlanetHolder -> {
                val planet = celestialBodies[position].let { it as CelestialBodies.Planet }
                holder.bind(planet)
            }
            is StarHolder -> {
                val star = celestialBodies[position].let { it as CelestialBodies.Star }
                holder.bind(star)
            }
        }
    }

    fun updateList(newList: List<CelestialBodies>) {
        celestialBodies = newList
        println("celestialBodies = $celestialBodies")
    }

    abstract class BaseHolder(view: View, onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val nameTextView: TextView = view.findViewById(R.id.textViewName)
        private val avatar: ImageView = view.findViewById(R.id.imageViewAvatar)

        init {
            view.setOnClickListener { onItemClick(adapterPosition) }
        }

        protected fun bindInfo(
            name: String,
            avatarLink: String
        ) {
            nameTextView.text = name

            Glide.with(itemView).load(avatarLink).placeholder(R.drawable.empty_avatar).into(avatar)
        }
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

    class PlanetHolder(view: View, onItemClick: (position: Int) -> Unit) :
        BaseHolder(view, onItemClick) {
        private val diameterTextView: TextView = view.findViewById(R.id.textViewDiameter)
        private val dayLengthTextView: TextView = view.findViewById(R.id.textViewDayLength)

        fun bind(
            planet: CelestialBodies.Planet
        ) {
            bindInfo(planet.name, planet.avatarLink)
            diameterTextView.text = "${itemView.resources.getString(R.string.planetDiameter)} = ${planet.diameter} ${itemView.resources.getString(R.string.km)}"
            dayLengthTextView.text = "${itemView.resources.getString(R.string.dayLength)} = ${planet.dayLength} ${itemView.resources.getString(R.string.days)}"
        }
    }


    companion object {
        private const val TYPE_PLANET = 1
        private const val TYPE_STAR = 2
        private const val TYPE_EMPTY = 10
    }

}