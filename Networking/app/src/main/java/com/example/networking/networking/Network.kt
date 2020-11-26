package com.example.networking.networking

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

// Класс, который собирает http запрос
object Network {

    // Плагин для Flipper
    val flipperNetworkPlugin = NetworkFlipperPlugin()

    // Клиент для всего приложения. Клиент модифицирован интерсепторами.
    // Первый интерсептор модифицирует запрос, второй - логирует запрос, третий - позволяет
    // просматривать логи в Flipper
    private val client = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .addInterceptor(FlipperOkhttpInterceptor(flipperNetworkPlugin))
        .build()

    // Функция поиска фильмов. Запросу присваиваются параметры. Потом запрос будет модифиццирован
    // в клиенте
    fun getSearchMovieCall(text: String, type: String, year: String): Call {
        //http://www.omdbapi.com/?apikey=[yourkey]&s=

        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("s", text)
            .addQueryParameter("type", type)
            .addQueryParameter("y", year)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }
}