package com.skillbox.a16homework

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_for_list.view.*

class ListAdapter(onItemClick: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<LocationData>(DataDiffUtilCallback()) {

    // В дополнительном конструкторе проверяется какой адаптер может обработать элемент
    init {
        delegatesManager.addDelegate(LocationAdapterDelegate(onItemClick))
    }

    //    Класс, который определяет правила сравнения элементов. Необходим для работы DiffUtil
    class DataDiffUtilCallback : DiffUtil.ItemCallback<LocationData>() {

        //    Сравнивает два элемента на основании какого-либо индентификатора. В данном случае сравнение идет на основе id
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return when {
                oldItem is LocationData.Location && newItem is LocationData.Location -> oldItem.id == newItem.id
                else -> false
            }
        }

        //    Проверяет совпадает ли содержание двух элементов. Метод будет вызван после того как метод выше вернет true
        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem == newItem
        }
    }
}

/*Класс принимает 3 параметра:
    1. Тип элемента, которым управляет данный delegate adapter. Должен быть подтипов второго параметра
    2. Общий тип списка
    3. Тип ViewHolder
 */
class LocationAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<LocationData.Location, LocationData, LocationAdapterDelegate.LocationHolder>() {

    //    Метод возвращает true, если поступивший элемент может быть обработан текущим адаптером
    override fun isForViewType(
        item: LocationData,
        items: MutableList<LocationData>,
        position: Int
    ): Boolean {
        return item is LocationData.Location
    }

    //    Создает VIewHolder
    override fun onCreateViewHolder(parent: ViewGroup): LocationHolder {
        return LocationHolder(parent.inflate(R.layout.layout_for_list), onItemClick)
    }

    //    Связывает ViewHolder и элементы
    override fun onBindViewHolder(
        item: LocationData.Location,
        holder: LocationHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    /*    Класс Holder, который наследуется от интерфейса LayoutContainer
      Благодаря интерфейсу холдер знает какая вью корневая и можно получать вью из корневой из кэша, что повышает скорость работы
 */
    class LocationHolder(override val containerView: View, onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        //   С помощью дополнительного конструктора элементы в адаптере становятся кликабельными
        init {
            containerView.setOnClickListener { onItemClick(adapterPosition) }
        }

        //    Присваивает вью отдельные атрибуты
        @SuppressLint("SetTextI18n")
        fun bind(location: LocationData.Location) {
            Glide.with(itemView).load(location.image).placeholder(R.drawable.empty_avatar)
                .into(containerView.imageViewAvatar)
            containerView.textViewLongitude.text =
                "${itemView.resources.getText(R.string.longitude)} = ${(location.lat * 100).toInt() / 100.toDouble()}"
            containerView.textViewLatitude.text =
                "${itemView.resources.getText(R.string.latitude)} = ${(location.lnd * 100).toInt() / 100.toDouble()}"
            containerView.textViewDateAndTime.text = location.time
        }
    }
}

