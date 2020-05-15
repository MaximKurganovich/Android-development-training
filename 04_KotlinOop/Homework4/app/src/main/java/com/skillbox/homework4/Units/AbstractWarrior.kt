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

    var HP: Int = maxHP

    override fun attack (warrior: Warrior): Int {
        return if (weapon.emptyClip) {
            weapon.recharge()
            0
        } else {
            var damage = 0
            val ammo = weapon.addingBullets() as MutableList<Ammo>
            val range: IntRange = 0..ammo.size
            for (item in range) {
                if (Random.nextInt(1..100) <= accuracy - warrior.chanceToDodge) {
                    damage += weapon.bulletMaking().takingCurrentDamage(ammo[item])
                }
            }
            damage
        }

    }

    override fun takeDamage(damage: Int) {
        HP -= damage
    }
}