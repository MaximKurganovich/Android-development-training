package com.example.networking.networking

/*
    Первый класс предназначен для объектов, которые получили в ответе от сервера,
    второй - для отправки запроса на сервер
*/

data class Movie(
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