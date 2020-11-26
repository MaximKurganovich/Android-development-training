package com.example.networking.networking

import com.example.networking.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/*Кастомный интерсептор. Необходим для модификации каждого запроса. В данном классе в каждый запрос
добавляется API key
 */
class CustomInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest =
            chain.request()                                                           // Получили запрос
        val url = originalRequest.url.newBuilder().addQueryParameter("apikey", API_KEY)
            .build()                                                                  // Добавляем в url api key
        val modifiedRequest = originalRequest.newBuilder().url(url)
            .build()                                                                  // Передаем новый url в новый запрос
        return chain.proceed(modifiedRequest)                                         // Отправляем модифицированный запрос клиенту
    }
}