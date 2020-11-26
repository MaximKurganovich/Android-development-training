package com.example.networking.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.networking.networking.Movie
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FilmAdapter: AsyncListDifferDelegationAdapter<Movie>(MovieDiffUtilCallback()) {

    // В дополнительном конструкторе проверяется какой адаптер может обработать элемент
    init{
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    //    Класс, который определяет правила сравнения элементов. Необходим для работы DiffUtil
    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {
        //    Сравнивает два элемента на основании какого-либо индентификатора
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        //    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}

