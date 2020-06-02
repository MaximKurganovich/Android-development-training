package com.skillbox.homework4.Units

import com.skillbox.homework4.Mechanics.AbstractWeapon
import com.skillbox.homework4.Mechanics.Ammo
import kotlin.random.Random
import kotlin.random.nextInt

abstract class AbstractWarrior (
    val maxHP: Int,
    override val chanceToDodge: Int,
    val accuracy: Int,
    val weapon: AbstractWeapon
): Warrior {

    var hp: Int = maxHP
    override var isKilled: Boolean = false

    override fun attack (warrior: Warrior) {
        var damage = 0
            val ammo = exceptionNoAmmo()
            val range: IntRange = 0 until ammo.size
            for (item in range) {
                if (Random.nextInt(1..100) <= accuracy - warrior.chanceToDodge) {
                    damage += weapon.bulletMaking().takingCurrentDamage(ammo[item])
                }
            }
        warrior.takeDamage(damage = damage)
    }

    override fun takeDamage(damage: Int) {
        hp -= damage
        if (hp <= 0) {
            hp = 0
            isKilled = true
        }
    }

    private fun exceptionNoAmmo (): MutableList<Ammo> {
        return try {
            weapon.addingBullets()
        } catch (t: NoAmmoException) {
            weapon.recharge()
            weapon.addingBullets()
        }
    }

}

class NoAmmoException: Exception ()
