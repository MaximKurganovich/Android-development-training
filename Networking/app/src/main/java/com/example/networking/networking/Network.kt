package com.example.networking.networking

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object Network {

    val flipperNetworkPlugin = NetworkFlipperPlugin()

    private val client = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .addInterceptor(FlipperOkhttpInterceptor(flipperNetworkPlugin))
        .build()

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