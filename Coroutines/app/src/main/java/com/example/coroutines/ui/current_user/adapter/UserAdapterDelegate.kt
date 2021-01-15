package com.example.coroutines.ui.current_user.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.coroutines.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.example.coroutines.databinding.RepositoryInformationBinding
import com.example.coroutines.ui.current_user.FollowingInformation
import com.example.coroutines.ui.current_user.UserInformation
import com.example.coroutines.ui.repository_list.RepositoryInformation
import com.skillbox.github.utils.inflate

class UserAdapterDelegate :
    AbsListItemAdapterDelegate<FollowingInformation, FollowingInformation, UserAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: FollowingInformation,
        items: MutableList<FollowingInformation>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: FollowingInformation,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.repository_information))
    }

    class Holder(
        containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val binding = RepositoryInformationBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        fun bind(following: FollowingInformation) {
            binding.name.text = following.name
            Glide.with(itemView).load(following.avatar).transform(CircleCrop())
                .placeholder(R.drawable.ic_emoji)
                .into(binding.avatar)
        }
    }
}