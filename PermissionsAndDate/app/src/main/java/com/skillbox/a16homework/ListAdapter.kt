package com.skillbox.a16homework

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_for_list.view.*

class ListAdapter : AsyncListDifferDelegationAdapter<Data>(DataDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(LocationAdapterDelegate())
    }

    class DataDiffUtilCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return when {
                oldItem is Data.Location && newItem is Data.Location -> oldItem == newItem
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}

class LocationAdapterDelegate :
    AbsListItemAdapterDelegate<Data.Location, Data, LocationAdapterDelegate.LocationHolder>() {

    override fun isForViewType(item: Data, items: MutableList<Data>, position: Int): Boolean {
        return item is Data.Location
    }

    override fun onCreateViewHolder(parent: ViewGroup): LocationHolder {
        return LocationHolder(parent.inflate(R.layout.layout_for_list))
    }

    override fun onBindViewHolder(
        item: Data.Location,
        holder: LocationHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class LocationHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        @SuppressLint("SetTextI18n")
        fun bind(location: Data.Location) {
            Glide.with(itemView).load(location.image).placeholder(R.drawable.empty_avatar)
                .into(containerView.imageViewAvatar)
            containerView.textViewLongitude.text =
                "${itemView.resources.getText(R.string.longitude)} = ${(location.lat * 100).toInt() / 100.toDouble()}"
            containerView.textViewLatitude.text =
                "${itemView.resources.getText(R.string.latitude)} = ${(location.lnd * 100).toInt() / 100.toDouble()}"
            containerView.textViewDateAndTime.text = location.time
        }
    }
}

