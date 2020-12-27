package com.skillbox.github.ui.repository_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.github.ui.repository_list.RepositoryInformation

class RepositoryAdapter:  AsyncListDifferDelegationAdapter<RepositoryInformation>(MovieDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RepositoryAdapterDelegate())
    }

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<RepositoryInformation>() {
        //    Сравнивает два элемента на основании какого-либо индентификатора
        override fun areItemsTheSame(oldItem: RepositoryInformation, newItem: RepositoryInformation): Boolean {
            return oldItem == newItem
        }

        //    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(oldItem: RepositoryInformation, newItem: RepositoryInformation): Boolean {
            return oldItem == newItem
        }

    }
}