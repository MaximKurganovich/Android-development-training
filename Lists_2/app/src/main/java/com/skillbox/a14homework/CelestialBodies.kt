package com.skillbox.a14homework

sealed class CelestialBodies {

    data class Planet(
        val name: String,
        val diameter: Int,
        val avatarLink: String,
        val dayLength: Double
    ): CelestialBodies()

    data class Star (
        val name: String,
        val surfaceTemperature: Int,
        val avatarLink: String
    ): CelestialBodies()
}