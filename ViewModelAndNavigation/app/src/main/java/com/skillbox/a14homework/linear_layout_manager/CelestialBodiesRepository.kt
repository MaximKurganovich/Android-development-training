package com.skillbox.a14homework.linear_layout_manager

import com.skillbox.a14homework.CelestialBodies

// Репозиторий. Отвечает за изменение данных
class CelestialBodiesRepository {

    //    Добавляет элемент в список и возвращает новый список
    fun newElement(
        celestialBodies: List<CelestialBodies>,
        newElement: CelestialBodies
    ): List<CelestialBodies> {
        return listOf(newElement) + celestialBodies
    }

    //    Удаляет элмент из списка и возвращает новый список
    fun deleteElement(
        celestialBodies: List<CelestialBodies>,
        position: Int
    ): List<CelestialBodies> {
        return celestialBodies.filterIndexed { index, _ -> index != position }
    }
}