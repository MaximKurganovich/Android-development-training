package com.skillbox.homework4.Battle

import com.skillbox.homework4.Units.*
import kotlin.random.Random
import kotlin.random.nextInt

class Team (val count: Int) {
    val teamWarrior: MutableList<AbstractWarrior> = mutableListOf()

    private fun createTeam () {
        val range = 0 until count
        for (item in range) {
            when (Random.nextInt(1..100)) {
                in 1..40 -> teamWarrior.add(Newbie())
                in 41..65 -> teamWarrior.add(Experienced())
                in 66..85 -> teamWarrior.add(Veteran())
                else -> teamWarrior.add(Master())
            }
        }
    }
    init {createTeam()}
}