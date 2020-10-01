package com.skillbox.a16homework

import org.threeten.bp.Instant

data class Location(
    val id: Int,
    var time: Instant,
    val lat: Double,
    val lnd: Double,
    val image: String
)


