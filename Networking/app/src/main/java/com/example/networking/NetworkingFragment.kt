package com.example.networking

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment

class NetworkingFragment : Fragment(R.layout.networking_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createADropdownList()
    }

//    Создается адаптер для AutoCompleteTextView. В адаптер устанавливается context, разметка для
//    листа и массив со значениями. Далее находится AutoCompleteTextView и ему устанавливается адаптер.
//    Чтобы не редактировать вариант меню, необходимо отключить ввод пользователя, установив
//    android: inputType = "none" на AutoCompleteTextView.
    private fun createADropdownList() {
        val adapter: ArrayAdapter<String>? = context?.let {
            ArrayAdapter(
                it,
                R.layout.dropdown_menu_popup_item,
                resources.getStringArray(R.array.list_of_movie_types)
            )
        }
        val textFilledExposedDropdown: AutoCompleteTextView =
            requireView().findViewById(R.id.filled_exposed_dropdown)
        textFilledExposedDropdown.setAdapter(adapter)
    }
}
