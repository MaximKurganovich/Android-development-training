package com.example.networking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networking.networking.Movie

class MovieViewModel: ViewModel() {

    private val userRepository = MovieRepository()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
}