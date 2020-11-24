package com.example.networking.networking

import com.example.networking.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder().addQueryParameter("apikey", API_KEY).build()
        val modifiedRequest = originalRequest.newBuilder().url(url).build()
        return chain.proceed(modifiedRequest)
    }
}