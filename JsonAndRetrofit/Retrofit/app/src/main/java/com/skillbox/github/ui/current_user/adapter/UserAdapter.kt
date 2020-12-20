package com.skillbox.github.ui.current_user.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.github.ui.current_user.UserInformation

class UserAdapter : AsyncListDifferDelegationAdapter<UserInformation>(MovieDiffUtilCallback()) {

    // В дополнительном конструкторе проверяется какой адаптер может обработать элемент
    init {
        delegatesManager.addDelegate(UserAdapterDelegate())
    }

    //    Класс, который определяет правила сравнения элементов. Необходим для работы DiffUtil
    class MovieDiffUtilCallback : DiffUtil.ItemCallback<UserInformation>() {
        //    Сравнивает два элемента на основании какого-либо индентификатора
        override fun areItemsTheSame(oldItem: UserInformation, newItem: UserInformation): Boolean {
            return oldItem == newItem
        }

        //    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(oldItem: UserInformation, newItem: UserInformation): Boolean {
            return oldItem == newItem
        }

    }
}