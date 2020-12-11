package com.example.moshi

import android.util.Log
import com.example.moshi.networking.movie.Movie
import com.example.moshi.networking.Network
import com.example.moshi.networking.movie.MovieRating
import com.example.moshi.networking.movie.ObjectToSearch
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MovieRepository {

    // Метод поиска фильмов
    fun searchMovie(movie: ObjectToSearch, callback: (ResponseList) -> Unit): Call {
        return Network.getSearchMovieCall(movie.title, movie.type, movie.year).apply {
            // Благодаря методу enqueue библиотека позволяет работать асинхронно. На вход
            // принимается интерфейс callBack
            enqueue(object : Callback {

                // Метод, который вызывается, когда приходит ошибка от сервера
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("Server", "execute request error = ${e.message}", e)
                    callback(ResponseList.Error(e.message.toString()))
                }

                // Метод вызывается, когда приходит ответ от сервера. Сначала проверяется является
                // ли ответ успешным
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseMovieResponse(responseString)
                        callback(movies)
                    } else {
                        callback(ResponseList.Error("Ошибка при получении данных. Код ошибки ${response.code}"))
                    }
                }
            })
        }
    }

    // Метод парсинга ответа.
    private fun parseMovieResponse(responseBody: String): ResponseList {
        val moshi = Moshi.Builder().build()
        val movieListType = Types.newParameterizedType(List::class.java, Movie::class.java)
        val adapter = moshi.adapter<List<Movie>>(movieListType).nonNull()
        return try {
            val movies =
                adapter.fromJson(JSONObject(responseBody).getJSONArray("Search").toString())
            ResponseList.Success(movies!!)
        } catch (e: Exception) {
            Log.e("Server", "parse response error = ${e.message}", e)
            ResponseList.Error(e.message.toString())
        }
    }

    fun addRating(movies: List<Movie>, position: Int, rating: String): List<Movie> {
        val list: MutableList<Movie> = movies.toMutableList()
        val range = MovieRating.values().indices
        for (i in range) {
            if(rating == MovieRating.values()[i].toString()) {
                list[position].rating = MovieRating.valueOf(rating)
                return list.toList()
            }
        }
        return list.toList()
    }

    fun addRating(movies: List<Movie>, position: Int, rate: Map<String, String>): List<Movie> {
        val list: MutableList<Movie> = movies.toMutableList()
        list[position].score = rate
        return list.toList()
    }
}