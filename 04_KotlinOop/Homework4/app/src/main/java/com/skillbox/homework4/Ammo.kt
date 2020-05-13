package com.skillbox.homework4

enum class Ammo (val damage: Int, val chanceOfCriticalDamage: Int, val criticalDamageRatio: Int) {
    ORDINARY(damage = 9, chanceOfCriticalDamage = 15, criticalDamageRatio = 2),
    STRENGTHENED (damage = 11, chanceOfCriticalDamage = 30, criticalDamageRatio = 2),
    CRITICAL (damage = 8, chanceOfCriticalDamage = 60, criticalDamageRatio = 3);

    fun takingCurrentDamage (ammo: Ammo): Int {
        return if (Math.random() * 100 <= ammo.chanceOfCriticalDamage)
        {damage * criticalDamageRatio}
        else damage
    }

}