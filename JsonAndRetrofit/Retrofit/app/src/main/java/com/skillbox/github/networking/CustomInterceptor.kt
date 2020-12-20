package com.skillbox.github.networking

import com.skillbox.github.data.AccessToken
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return run {
            val modifiedRequest = originalRequest.newBuilder()
                    .addHeader("Authorization", AccessToken.token)
                    .build()
            chain.proceed(modifiedRequest)
        }
    }
}