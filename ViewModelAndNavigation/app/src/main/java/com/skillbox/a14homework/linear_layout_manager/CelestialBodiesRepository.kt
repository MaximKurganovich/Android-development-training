package com.skillbox.a14homework.linear_layout_manager

import com.skillbox.a14homework.CelestialBodies

class CelestialBodiesRepository {

    fun newElement(
        celestialBodies: List<CelestialBodies>,
        newElement: CelestialBodies
    ): List<CelestialBodies> {
        return listOf(newElement) + celestialBodies
    }

    fun deleteElement(
        celestialBodies: List<CelestialBodies>,
        position: Int
    ): List<CelestialBodies> {
        return celestialBodies.filterIndexed { index, _ -> index != position }
    }

}