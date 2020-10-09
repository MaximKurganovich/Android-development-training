package com.skillbox.a16homework

sealed class LocationData {
    data class Location(
        val id: Int,
        var time: String,
        val lnd: Double,
        val lat: Double,
        val image: String
    ) : LocationData()
}




