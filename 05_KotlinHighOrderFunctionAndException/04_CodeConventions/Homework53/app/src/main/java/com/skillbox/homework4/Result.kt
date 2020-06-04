package com.skillbox.homework4

import com.skillbox.homework4.Battle.Battle

fun main() {
    println("Введите количество бойцов в команде:")
        val numberInTeam: Int = readLine()?.toIntOrNull() ?: return println(
                """
        Введено не целое число!
    """.trimIndent()
        )

    val battle = Battle(numberInTeam)
    var round = 0
    do {
        round ++
        println()
        println("Ход $round")
        battle.statusBattle()
        battle.shuffled()
    } while (battle.theBattleContinues)
}
