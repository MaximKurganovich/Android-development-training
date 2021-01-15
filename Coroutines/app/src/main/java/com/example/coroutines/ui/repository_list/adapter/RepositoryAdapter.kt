package com.example.coroutines.ui.repository_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coroutines.ui.repository_list.RepositoryInformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class RepositoryAdapter(
    onItemClick: (position: Int) -> Unit):  AsyncListDifferDelegationAdapter<RepositoryInformation>(
    RepositoryDiffUtilCallback()
) {

    init {
        delegatesManager.addDelegate(RepositoryAdapterDelegate(onItemClick))
    }

    class RepositoryDiffUtilCallback : DiffUtil.ItemCallback<RepositoryInformation>() {
        //    Сравнивает два элемента на основании какого-либо индентификатора
        override fun areItemsTheSame(
            oldItem: RepositoryInformation,
            newItem: RepositoryInformation
        ): Boolean {
            return oldItem == newItem
        }

        //    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(
            oldItem: RepositoryInformation,
            newItem: RepositoryInformation
        ): Boolean {
            return oldItem == newItem
        }

    }
}
