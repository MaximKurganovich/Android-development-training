package com.example.coroutines.networking

import com.example.coroutines.data.AccessToken
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return run {
            val modifiedRequest = originalRequest.newBuilder()
                    .addHeader("Authorization", "token " + AccessToken.token)
                    .build()
            chain.proceed(modifiedRequest)
        }
    }
}