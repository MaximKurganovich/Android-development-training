package com.example.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networking.networking.Movie
import com.example.networking.networking.ObjectToSearch
import okhttp3.Call

class MovieViewModel: ViewModel() {

    private val userRepository = MovieRepository()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private var currentCall: Call? = null

    fun getMoviesLiveData(): LiveData<List<Movie>> {
        return moviesLiveData
    }

    fun searchMovie(movie: ObjectToSearch) {
        currentCall = userRepository.searchMovie(movie) {
            listMovies -> moviesLiveData.postValue(listMovies)
        }

    }
}