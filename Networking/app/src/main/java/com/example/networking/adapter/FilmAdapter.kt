package com.example.networking.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.networking.networking.Movie
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FilmAdapter: AsyncListDifferDelegationAdapter<Movie>(MovieDiffUtilCallback()) {

    init{
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}

