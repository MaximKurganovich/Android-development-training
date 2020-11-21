package com.example.networking.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networking.R
import com.example.networking.utils.inflate
import com.example.networking.networking.Movie
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.for_list_movie.*

class MovieAdapterDelegate : AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.MovieHolder>() {

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.for_list_movie))
    }

    override fun onBindViewHolder(item: Movie, holder: MovieHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class MovieHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Movie) {
            movie_title.text = movie.title
            year_of_issue.text = movie.year
            type_movie.text = movie.type
        }
    }
}

