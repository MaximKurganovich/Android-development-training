package com.skillbox.homework6

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.list_fragment.*


class ListFragment : Fragment(R.layout.list_fragment) {

    val catNames = arrayOf(
        "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
        "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
        "Китти", "Масяня", "Симба"
    )


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1, catNames
        )
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            viewDetailedInformationOfTheSelectedItem(catNames[position])
        }

    }

    private fun viewDetailedInformationOfTheSelectedItem(text: String) {
        val trans = parentFragment?.childFragmentManager?.beginTransaction()
        trans?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        trans?.replace(R.id.mainFragmentContainer, DetailFragment.newInstance(text))
        trans?.addToBackStack(null)
        trans?.commit()
    }

}