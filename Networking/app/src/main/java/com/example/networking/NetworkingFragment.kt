package com.example.networking

import android.os.Bundle
import android.widget.AdapterView
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.adapter.FilmAdapter
import com.example.networking.networking.Movie
import com.example.networking.networking.ObjectToSearch
import com.example.networking.utils.autoCleared
import kotlinx.android.synthetic.main.networking_fragment.*

class NetworkingFragment : Fragment(R.layout.networking_fragment) {

    private var movieAdapter: FilmAdapter by autoCleared()


    private val viewModel: MovieViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        createADropdownList()
    }

    private fun initList() {
        movieAdapter = FilmAdapter()
        moviesList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            itemAnimator = ScaleInAnimator()
        }
    }

    private fun searchMovie(textFilledExposedDropdown: AutoCompleteTextView) {
        searchButton.setOnClickListener{
            val searchMovie = ObjectToSearch(
                title = searchNameEditText.text.toString(),
                year = searchYearEditText.text.toString(),
                type = textFilledExposedDropdown.toString()
            )
            viewModel.searchMovie(searchMovie)
        }
        viewModel.getMoviesLiveData().observe(viewLifecycleOwner) {movieAdapter.items = it}

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
        textFilledExposedDropdown.setText("Фильм", false)
        searchMovie(textFilledExposedDropdown)
    }
}
