package com.skillbox.homework4.Units

interface Warrior {
    var isKilled: Boolean // Состояние юнита
    val chanceToDodge: Int // Шанс увернуться от атаки

    fun attack(warrior: Warrior)
    fun takeDamage(damage: Int)
}
