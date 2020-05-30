package com.skillbox.homework4.Mechanics

abstract class AbstractWeapon (val maxCountOfBullets: Int, val fireType: FireType) {
    var listAmmo = mutableListOf<Ammo>()
    var emptyClip = listAmmo.isEmpty()

    //Создает патроны

    abstract fun bulletMaking(): Ammo


    // Перезарядка оружия
    open fun recharge() {
        val range: IntRange = 1 .. maxCountOfBullets
        var newListAmmo = mutableListOf<Ammo>()
        for (item in range) {
            newListAmmo.add(bulletMaking())
        }
        listAmmo = newListAmmo
        emptyClip = false
    }

    // Получение патронов для выстеров
    fun addingBullets (): MutableList<Ammo> {
        val count = minOf(fireType.countOfBullets, listAmmo.size)
        val listAmmoAttack = mutableListOf<Ammo>()
        val range: IntRange = 1..count
        for (item in range) {
            listAmmoAttack.add(listAmmo[0])
            listAmmo.removeAt(0)
        }
        if (listAmmo.isEmpty()) {
            emptyClip = true
        }
        return listAmmoAttack
    }

    object Weapons {
        val UZI = object : AbstractWeapon(maxCountOfBullets = 18, fireType = FireType.Queue) {
            override fun bulletMaking(): Ammo {
                return Ammo.ORDINARY
            }
        }
        val SAW = object : AbstractWeapon(maxCountOfBullets = 50, fireType = FireType.Queue) {
            override fun bulletMaking(): Ammo {
                return Ammo.ORDINARY
            }
        }
        val M1911 = object : AbstractWeapon(maxCountOfBullets = 22, fireType = FireType.Single) {
            override fun bulletMaking(): Ammo {
                return Ammo.STRENGTHENED
            }
        }
        val OICW = object : AbstractWeapon(maxCountOfBullets = 14, fireType = FireType.Single) {
            override fun bulletMaking(): Ammo {
                return Ammo.CRITICAL
            }
        }
    }
}