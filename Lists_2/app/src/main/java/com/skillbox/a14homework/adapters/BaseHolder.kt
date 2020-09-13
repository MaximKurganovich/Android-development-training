package com.skillbox.a14homework.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.a14homework.R

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