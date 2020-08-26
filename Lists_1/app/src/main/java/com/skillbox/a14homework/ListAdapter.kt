package com.skillbox.a14homework

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var celestialBodies: List<CelestialBodies> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = celestialBodies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    abstract class BaseHolder(view: View, onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {

    }

}