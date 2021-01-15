package com.example.coroutines.ui.repository_list.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.coroutines.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.example.coroutines.databinding.RepositoryInformationBinding
import com.example.coroutines.ui.repository_list.RepositoryInformation
import com.skillbox.github.utils.inflate

class RepositoryAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<RepositoryInformation, RepositoryInformation, RepositoryAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: RepositoryInformation,
        items: MutableList<RepositoryInformation>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: RepositoryInformation,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.repository_information), onItemClick)
    }

    class Holder(
        containerView: View, onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView) {
        init {
            containerView.setOnClickListener { onItemClick(adapterPosition) }
        }

        private val binding = RepositoryInformationBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        fun bind(repository: RepositoryInformation) {
            binding.name.text = repository.name
            Glide.with(itemView).load(repository.owner.avatar).transform(CircleCrop())
                .placeholder(R.drawable.ic_emoji)
                .into(binding.avatar)
        }
    }
}