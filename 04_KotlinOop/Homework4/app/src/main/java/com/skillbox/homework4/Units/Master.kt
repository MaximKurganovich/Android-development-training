package com.skillbox.homework4.Units

import com.skillbox.homework4.Mechanics.AbstractWeapon

class Master: AbstractWarrior(maxHP = 250, chanceToDodge = 10, accuracy = 90, weapon = AbstractWeapon.Weapons.OICW) {
    override var isKilled: Boolean = false
}