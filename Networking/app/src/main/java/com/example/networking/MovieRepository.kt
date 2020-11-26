package com.example.networking

import android.util.Log
import com.example.networking.networking.Movie
import com.example.networking.networking.Network
import com.example.networking.networking.ObjectToSearch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
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

    // Метод парсинга ответа. Принимается JSON, затем из него достается массив,
    // а уже из массива достаются нужные данные.
    private fun parseMovieResponse(responseBody: String): ResponseList {
        return try {
            val jsonObject = JSONObject(responseBody)
            val movieArray = jsonObject.getJSONArray("Search")

            ResponseList.Success((0 until movieArray.length()).map { index ->
                movieArray.getJSONObject(
                    index
                )
            }
                .map { movieJSONObject ->
                    val title = movieJSONObject.getString("Title")
                    val year = movieJSONObject.getString("Year")
                    val id = movieJSONObject.getString("imdbID")
                    val type = movieJSONObject.getString("Type")
                    Movie(title = title, year = year, id = id, type = type)
                })
        } catch (e: JSONException) {
            Log.e("Server", "parse response error = ${e.message}", e)
            ResponseList.Error(e.message.toString())
        }
    }
}