package com.skillbox.github.ui.current_user.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.github.R
import com.skillbox.github.databinding.UserInformationBinding
import com.skillbox.github.ui.current_user.UserInformation
import com.skillbox.github.utils.inflate

class UserAdapterDelegate :
    AbsListItemAdapterDelegate<UserInformation, UserInformation, UserAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: UserInformation,
        items: MutableList<UserInformation>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.user_information))
    }

    override fun onBindViewHolder(
        item: UserInformation,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class Holder(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val binding = UserInformationBinding.bind(containerView)

        fun bind(user: UserInformation) {
            binding.name.text = user.name
            binding.email.text = user.url
            binding.location.text = user.type
            Glide.with(binding.avatar).load(user.avatar).diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).placeholder(R.drawable.empty_avatar).into(binding.avatar)
        }
    }


}