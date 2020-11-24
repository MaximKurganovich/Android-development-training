package com.example.networking

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Xml
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.adapter.FilmAdapter
import com.example.networking.networking.ObjectToSearch
import com.example.networking.utils.autoCleared
import com.facebook.flipper.core.FlipperPlugin
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.networking_fragment.*
import java.util.*


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


    //    Создается адаптер для AutoCompleteTextView. В адаптер устанавливается context, разметка для
//    листа и массив со значениями. Далее находится AutoCompleteTextView и ему устанавливается адаптер.
//    Чтобы не редактировать вариант меню, необходимо отключить ввод пользователя, установив
//    android: inputType = "none" на AutoCompleteTextView.
    @SuppressLint("SetTextI18n", "ResourceType")
    private fun createADropdownList() {
        val adapter: ArrayAdapter<String>? = context?.let {
            ArrayAdapter(
                it,
                R.layout.dropdown_menu_popup_item,
                resources.getStringArray(R.array.list_of_movie_types)
            )
        }
        filled_exposed_dropdown.apply {
            freezesText.not()
            setAdapter(adapter)
            setText("Фильм", false)
            val parser = resources.getXml(R.layout.dropdown_menu_popup_item)
            val attributes = Xml.asAttributeSet(parser)
            ExposedDropDown(requireContext(), attributes).freezesText
        }
//        getAttributeBooleanValue
        settingParameters()
    }

    private fun settingParameters() {
        searchButton.setOnClickListener {
            val searchMovie = ObjectToSearch(
                title = searchNameEditText.text.toString(),
                year = searchYearEditText.text.toString(),
                type = when (filled_exposed_dropdown.text.toString()) {
                    "Фильм" -> "movie"
                    "Серия" -> "series"
                    "Эпизод" -> "episode"
                    else -> ""
                }
            )
            viewModel.searchMovie(searchMovie)
        }
        viewModel.getMoviesLiveData().observe(viewLifecycleOwner) { movieAdapter.items = it }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.showErrorDialog.observe(viewLifecycleOwner) { errorMassage ->
            Toast.makeText(
                requireContext(),
                errorMassage,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        moviesList.isVisible = isLoading.not()
        progressBar.isVisible = isLoading
        searchButton.isEnabled = isLoading.not()
        searchNameEditText.isEnabled = isLoading.not()
        searchYearEditText.isEnabled = isLoading.not()
        filled_exposed_dropdown.isEnabled = isLoading.not()
    }

    class ExposedDropDown(context: Context, attributeSet: AttributeSet?) :
        MaterialAutoCompleteTextView(context, attributeSet) {
        override fun getFreezesText(): Boolean {
            return false
        }
    }
}


