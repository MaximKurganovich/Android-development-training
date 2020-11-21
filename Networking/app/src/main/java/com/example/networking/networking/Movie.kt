package com.example.networking.networking

import android.widget.AdapterView

data class Movie (
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