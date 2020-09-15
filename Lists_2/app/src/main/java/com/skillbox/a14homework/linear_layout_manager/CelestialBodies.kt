package com.skillbox.a14homework.linear_layout_manager

sealed class CelestialBodies {

    data class Planet(
        val id: Int,
        val name: String,
        val diameter: Int,
        val avatarLink: String,
        val dayLength: Double
    ): CelestialBodies()

    data class Star (
        val id: Int,
        val name: String,
        val surfaceTemperature: Int,
        val avatarLink: String
    ): CelestialBodies()
}