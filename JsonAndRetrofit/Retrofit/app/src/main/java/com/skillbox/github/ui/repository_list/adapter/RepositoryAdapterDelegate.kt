package com.skillbox.github.ui.repository_list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.github.R
import com.skillbox.github.databinding.RepositoryInformationBinding
import com.skillbox.github.databinding.RepositoryInformationTwoBinding
import com.skillbox.github.ui.repository_list.RepositoryInformation
import com.skillbox.github.utils.inflate

class RepositoryAdapterDelegate :
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
        return Holder(parent.inflate(R.layout.repository_information_two))
    }

    class Holder(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val binding = RepositoryInformationTwoBinding.bind(containerView)

        fun bind(repository: RepositoryInformation) {
            binding.name.text = repository.name
            binding.fullName.text = repository.fullName
        }
    }
}