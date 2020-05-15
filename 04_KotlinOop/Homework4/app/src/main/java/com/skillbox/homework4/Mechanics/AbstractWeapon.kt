package com.skillbox.homework4.Mechanics

abstract class AbstractWeapon (val maxCountOfBullets: Int, val fireType: FireType) {
    private var listAmmo = mutableListOf<Ammo>()
    var emptyClip = listAmmo.isEmpty()

    //Создает патроны

    abstract fun bulletMaking(): Ammo


    // Перезарядка оружия
    fun recharge() {
        val range: IntRange = 1 .. maxCountOfBullets
        var newListAmmo = mutableListOf<Ammo>()
        for (item in range) {
            newListAmmo.add(bulletMaking())
        }
        listAmmo = newListAmmo
    }

    // Получение патронов для выстеров
    fun addingBullets (): List<Ammo> {
        val count = minOf(fireType.countOfBullets, listAmmo.size)
        val listAmmoAttack = mutableListOf<Ammo>()
        val range: IntRange = 1..count
        for (item in range) {
            listAmmoAttack.add(listAmmo[0])
            listAmmo.removeAt(0)
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