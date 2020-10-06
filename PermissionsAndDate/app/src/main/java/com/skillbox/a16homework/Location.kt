package com.skillbox.a16homework

import org.threeten.bp.Instant

sealed class Data {
    data class Location(
        val id: Int,
        var time: String,
        val lnd: Double,
        val lat: Double,
        val image: String
    ) : Data()
}




