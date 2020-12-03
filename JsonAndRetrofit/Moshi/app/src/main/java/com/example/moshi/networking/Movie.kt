package com.example.moshi.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*
    Первый класс предназначен для объектов, которые получили в ответе от сервера,
    второй - для отправки запроса на сервер
*/
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "imdbID")
    val id: String,
    val title: String,
    val year: String,
    val type: String
)

data class ObjectToSearch(
    val title: String,
    val year: String,
    val type: String
)