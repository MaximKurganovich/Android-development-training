package com.example.moshi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moshi.adapter.FilmAdapter
import com.example.moshi.databinding.NetworkingFragmentBinding
import com.example.moshi.networking.movie.ObjectToSearch
import com.example.moshi.utils.autoCleared
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import androidx.navigation.fragment.findNavController


class NetworkingFragment : Fragment(R.layout.networking_fragment) {

    // Адаптер, который автоматически очистится при уничтожении фрагмента, благодаря свойству autoCleared()
    private var movieAdapter: FilmAdapter by autoCleared()
    private val viewModel: MovieViewModel by activityViewModels()

    private var binding: NetworkingFragmentBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NetworkingFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        createADropdownList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //    Адаптеру присваиваются атрибуты и указывается тип LayoutManager
    private fun initList() {
        movieAdapter = FilmAdapter { position -> openDetailsFragment(position) }
        binding!!.moviesList.apply {
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

    private fun openDetailsFragment(position: Int) {
        val element = viewModel.getMoviesLiveData().value?.get(position)
        val action = NetworkingFragmentDirections.actionNetworkingFragmentToDetailFragment(
            element!!,
            position
        )
        findNavController().navigate(action)
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
        binding!!.spinner.apply {
            setAdapter(adapter)
            prompt = resources.getString(R.string.choosing_a_movie_type)
        }
        settingParameters()
    }

    // Метод, который при нажатии на кнопку "Поиск" передает введенные данные во вьюмодель, а также
    // отслеживает изменения в списке данных
    private fun settingParameters() {
        binding!!.searchButton.setOnClickListener {
            val searchMovie = ObjectToSearch(
                title = binding!!.searchNameEditText.text.toString(),
                year = binding!!.searchYearEditText.text.toString(),
                type = when (binding!!.spinner.selectedItemPosition.toString()) {
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
        binding!!.moviesList.isVisible = isLoading.not()
        binding!!.progressBar.isVisible = isLoading
        binding!!.searchButton.isEnabled = isLoading.not()
        binding!!.searchNameEditText.isEnabled = isLoading.not()
        binding!!.searchYearEditText.isEnabled = isLoading.not()
        binding!!.spinner.isEnabled = isLoading.not()
    }
}


