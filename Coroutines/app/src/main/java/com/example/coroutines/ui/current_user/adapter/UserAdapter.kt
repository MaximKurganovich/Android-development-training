package com.example.coroutines.ui.current_user.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coroutines.ui.current_user.FollowingInformation
import com.example.coroutines.ui.repository_list.RepositoryInformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class UserAdapter(
):  AsyncListDifferDelegationAdapter<FollowingInformation>(
    RepositoryDiffUtilCallback()
) {

    init {
        delegatesManager.addDelegate(UserAdapterDelegate())
    }

    class RepositoryDiffUtilCallback : DiffUtil.ItemCallback<FollowingInformation>() {
        //    Сравнивает два элемента на основании какого-либо индентификатора
        override fun areItemsTheSame(
            oldItem: FollowingInformation,
            newItem: FollowingInformation
        ): Boolean {
            return oldItem == newItem
        }

        //    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(
            oldItem: FollowingInformation,
            newItem: FollowingInformation
        ): Boolean {
            return oldItem == newItem
        }

    }
}
