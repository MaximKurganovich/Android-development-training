package com.skillbox.multithreading.threading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.skillbox.multithreading.networking.Movie

class MovieViewModel : ViewModel() {

    private val userRepository = MovieRepository()

    private val movieIds = listOf(
        "tt0167261",
        "tt0133093",
        "tt0165832",
        "tt0088763",
        "tt0096874",
        "tt0120201",
        "tt0101272",
        "tt0481499"
    )

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    val movieList: LiveData<List<Movie>>
        get() = moviesLiveData

    fun requestMovies(setAdapter: (movies: MutableList<Movie>) -> Unit) {
        moviesLiveData.postValue(listOf())
        userRepository.fetchMovies(movieIds) { allMovies -> setAdapter(allMovies)
        moviesLiveData.postValue(allMovies)}
    }
}