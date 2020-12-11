package com.example.moshi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moshi.R
import com.example.moshi.databinding.ForListMovieBinding
import com.example.moshi.networking.movie.Movie
import com.example.moshi.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate


/*Класс принимает 3 параметра:
    1. Тип элемента, которым управляет данный delegate adapter. Должен быть подтипов второго параметра
    2. Общий тип списка
    3. Тип ViewHolder
 */
class MovieAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.MovieHolder>() {


    //    Метод возвращает true, если поступивший элемент может быть обработан текущим адаптером
    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    //    Создает VIewHolder
    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.for_list_movie) as ViewGroup, onItemClick)
    }

    //    Связывает ViewHolder и элементы
    override fun onBindViewHolder(item: Movie, holder: MovieHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    /*    Класс Holder, который наследуется от интерфейса LayoutContainer
      Благодаря интерфейсу холдер знает какая вью корневая и можно получать вью из корневой из кэша, что повышает скорость работы
 */
    class MovieHolder(private val containerView: ViewGroup, onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(containerView) {
        init {
            containerView.setOnClickListener { onItemClick(adapterPosition) }
        }

        private var binding = ForListMovieBinding.bind(containerView)

        //    Метод утснавливает поля значения
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.yearOfIssue.text = movie.year.toString()
            binding.typeMovie.text = movie.type
        }
    }
}

