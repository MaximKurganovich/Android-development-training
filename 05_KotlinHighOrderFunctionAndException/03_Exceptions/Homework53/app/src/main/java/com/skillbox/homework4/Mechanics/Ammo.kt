package com.skillbox.homework4.Mechanics

import kotlin.random.Random
import kotlin.random.nextInt

enum class Ammo (val damage: Int, val chanceOfCriticalDamage: Int, val criticalDamageRatio: Int) {
    ORDINARY(damage = 9, chanceOfCriticalDamage = 15, criticalDamageRatio = 2),
    STRENGTHENED (damage = 10, chanceOfCriticalDamage = 30, criticalDamageRatio = 2),
    CRITICAL (damage = 7, chanceOfCriticalDamage = 60, criticalDamageRatio = 3);

    fun takingCurrentDamage (ammo: Ammo): Int {
        return if (Random.nextInt(1..100) <= ammo.chanceOfCriticalDamage)
        {damage * criticalDamageRatio}
        else damage
    }

}