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

/*Класс принимает 3 параметра:
    1. Тип элемента, которым управляет данный delegate adapter. Должен быть подтипов второго параметра
    2. Общий тип списка
    3. Тип ViewHolder
 */
class MovieAdapterDelegate : AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.MovieHolder>() {

    //    Метод возвращает true, если поступивший элемент может быть обработан текущим адаптером
    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    //    Создает VIewHolder
    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.for_list_movie))
    }

    //    Связывает ViewHolder и элементы
    override fun onBindViewHolder(item: Movie, holder: MovieHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    /*    Класс Holder, который наследуется от интерфейса LayoutContainer
      Благодаря интерфейсу холдер знает какая вью корневая и можно получать вью из корневой из кэша, что повышает скорость работы
 */
    class MovieHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        //    Метод утснавливает поля значения
        fun bind(movie: Movie) {
            movie_title.text = movie.title
            year_of_issue.text = movie.year
            type_movie.text = movie.type
        }
    }
}

