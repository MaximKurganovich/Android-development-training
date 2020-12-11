package com.example.moshi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moshi.networking.movie.Movie
import com.example.moshi.networking.movie.MovieRating
import com.example.moshi.networking.movie.ObjectToSearch
import com.example.moshi.utils.SingleLiveEvent
import okhttp3.Call

class MovieViewModel: ViewModel() {

    private val userRepository = MovieRepository()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    //    Поле, которое иформирует 1 раз об изменении данных
    private val showErrorDialogLiveData = SingleLiveEvent<String>()
    // Переменная создана, чтобы в случае необходимости отменить уже созданный запрос
    private var currentCall: Call? = null

    //    Получение поля, которое информирует 1 раз об изменении данных
    val showErrorDialog: LiveData<String>
        get() = showErrorDialogLiveData

    //    Поле для получения списка объектов в других классах
    fun getMoviesLiveData(): LiveData<List<Movie>> {
        return moviesLiveData
    }

    // Поле, благодаря которому во фрагменте блокируются поля и кнопки во время запроса
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

    fun addRating(position: Int,rating: String) {
        moviesLiveData.postValue(userRepository.addRating(moviesLiveData.value.orEmpty(), position, rating))
    }

    fun addRating(position: Int, rate: Map<String, String>) {
        moviesLiveData.postValue(userRepository.addRating(moviesLiveData.value.orEmpty(), position, rate))
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}

// Класс, созданный для хранения ответа от сервера.
sealed class ResponseList{
    data class Success (val resultList: List<Movie>): ResponseList()
    data class Error(val errorMessage: String): ResponseList()
}