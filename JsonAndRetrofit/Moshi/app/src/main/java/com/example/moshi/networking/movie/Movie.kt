package com.example.moshi.networking.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.net.URL
import android.os.Parcelable

/*
    Первый класс предназначен для объектов, которые получили в ответе от сервера,
    второй - для отправки запроса на сервер
*/

@Parcelize
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "imdbID")
    val id: String,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: Int,
    var rating: MovieRating = MovieRating.GeneralAudiences,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Poster")
    val poster: String,
    var score: Map<String, String> = mapOf("Internet Movie Database" to "10/10")
): Parcelable

data class ObjectToSearch(
    val title: String,
    val year: String,
    val type: String
)