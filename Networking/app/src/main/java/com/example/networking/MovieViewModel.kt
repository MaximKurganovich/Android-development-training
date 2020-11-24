package com.example.networking

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networking.networking.Movie
import com.example.networking.networking.ObjectToSearch
import okhttp3.Call

class MovieViewModel: ViewModel() {

    private val userRepository = MovieRepository()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private var currentCall: Call? = null
    private val showErrorDialogLiveData = SingleLiveEvent<String>()

    val showErrorDialog: LiveData<String>
        get() = showErrorDialogLiveData

    fun getMoviesLiveData(): LiveData<List<Movie>> {
        return moviesLiveData
    }

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData


    fun searchMovie(movie: ObjectToSearch) {
        isLoadingLiveData.postValue(true)
        currentCall = userRepository.searchMovie(movie) {
            response -> isLoadingLiveData.postValue(false)
            when(response) {
                is ResponseList.Success -> {moviesLiveData.postValue(response.resultList)}
                is ResponseList.Error -> {
                    moviesLiveData.postValue(emptyList())
                    showErrorDialogLiveData.postValue(response.errorMessage)
                }
            }
            currentCall = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}

sealed class ResponseList{
    data class Success (val resultList: List<Movie>): ResponseList()
    data class Error(val errorMessage: String): ResponseList()
}