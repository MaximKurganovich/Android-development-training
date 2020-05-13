package com.skillbox.homework4

abstract class AbstractWeapon (val maxCountOfBullets: Int, val fireType: FireType) {
    private var listAmmo = mutableListOf<Ammo>()
    private var emptyClip = listAmmo.isEmpty()

    private fun bulletMaking(): Ammo {
        return when ((Math.random()*100).toInt()) {
            in 0..15 -> Ammo.CRITICAL
            in 16..45 -> Ammo.STRENGTHENED
            else -> Ammo.ORDINARY
        }
    }

    fun recharge() {
        val range: IntRange = 1 .. maxCountOfBullets
        var newListAmmo = mutableListOf<Ammo>()
        for (item in range) {
            newListAmmo.add(bulletMaking())
        }
        listAmmo.addAll(newListAmmo)
    }

    fun addingBullets () {
        val count = minOf(fireType.countOfBullets, listAmmo.size)
        val listAmmoAttack = mutableListOf<Ammo>()
        val range: IntRange = 1..count
        for (item in range) {
            listAmmoAttack.add(listAmmo[0])
            listAmmo.removeAt(0)
        }
    }

    object Weapons {
        val UZI = object: AbstractWeapon (maxCountOfBullets = 18, fireType = FireType.Queue) {}
        val SAW = object: AbstractWeapon (maxCountOfBullets = 50, fireType = FireType.Queue) {}
        val M1911 = object: AbstractWeapon (maxCountOfBullets = 22, fireType = FireType.Single) {}
        val OICW = object: AbstractWeapon (maxCountOfBullets = 14, fireType = FireType.Single) {}
    }
}