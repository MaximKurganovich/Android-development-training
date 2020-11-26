package com.example.networking

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.adapter.FilmAdapter
import com.example.networking.networking.ObjectToSearch
import com.example.networking.utils.autoCleared
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.networking_fragment.*


class NetworkingFragment : Fragment(R.layout.networking_fragment) {

    // Адаптер, который автоматически очистится при уничтожении фрагмента, благодаря свойству autoCleared()
    private var movieAdapter: FilmAdapter by autoCleared()
    private val viewModel: MovieViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        createADropdownList()
    }

    //    Адаптеру присваиваются атрибуты и указывается тип LayoutManager
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
        spinner.apply {
            setAdapter(adapter)
            prompt = resources.getString(R.string.choosing_a_movie_type)
        }
        settingParameters()
    }

    // Метод, который при нажатии на кнопку "Поиск" передает введенные данные во вьюмодель, а также
    // отслеживает изменения в списке данных
    private fun settingParameters() {
        searchButton.setOnClickListener {
            val searchMovie = ObjectToSearch(
                title = searchNameEditText.text.toString(),
                year = searchYearEditText.text.toString(),
                type = when (spinner.selectedItemPosition.toString()) {
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

        // При получении ошибки от сервера отображается тост с кодом ошибки
        viewModel.showErrorDialog.observe(viewLifecycleOwner) { errorMassage ->
            Toast.makeText(
                requireContext(),
                errorMassage,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Благодаря этому методу во время получения данных от сервера блокируются все поля и кнопки
    // и отображается progressBar
    private fun updateLoadingState(isLoading: Boolean) {
        moviesList.isVisible = isLoading.not()
        progressBar.isVisible = isLoading
        searchButton.isEnabled = isLoading.not()
        searchNameEditText.isEnabled = isLoading.not()
        searchYearEditText.isEnabled = isLoading.not()
        spinner.isEnabled = isLoading.not()
    }

    // Класс должен был помочь избавиться от бага, при котором после выбора типа фильма в
    // AutoCompleteTextView и повороте экрана в списке оставался один ранее выбранный вариант
    class ExposedDropDown(context: Context, attributeSet: AttributeSet?) :
        MaterialAutoCompleteTextView(context, attributeSet) {
        override fun getFreezesText(): Boolean {
            return false
        }
    }

    class ExposedDropdownMenu(context: Context, attributeSet: AttributeSet?) :
        AppCompatAutoCompleteTextView(context, attributeSet) {
        override fun getFreezesText(): Boolean {
            return false
        }
    }
}


