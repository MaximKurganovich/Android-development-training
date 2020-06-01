package com.skillbox.homework4.Battle

import com.skillbox.homework4.Units.NoAmmoException
import kotlin.random.Random
import kotlin.random.nextInt

class Battle (numberInTeam:Int) {
    val team1: Team = Team(numberInTeam)
    val team2: Team = Team(numberInTeam)
    var theBattleContinues = true

    fun statusBattle (): BattleState {
        val sumHPTeam1 = team1.teamWarrior.sumBy { it.hp }
        val sumHPTeam2 = team2.teamWarrior.sumBy { it.hp }
        return if (sumHPTeam1 <= 0 && sumHPTeam2 <= 0) {
            theBattleContinues = false
            BattleState.Draw
        } else if (sumHPTeam2 <= 0) {
            theBattleContinues = false
            BattleState.TeamOneWin(team1)
        } else if (sumHPTeam1 <= 0) {
            theBattleContinues = false
            BattleState.TeamTwoWin(team2)
        } else BattleState.Progress (team1, team2)
    }

    fun shuffled () {
        val survivingTeam1= team1.teamWarrior.filter { !it.isKilled }
        val survivingTeam2 = team2.teamWarrior.filter { !it.isKilled }
        val range = 0 until maxOf (team1.teamWarrior.size, team2.teamWarrior.size)
        for (item in range) {
            if (survivingTeam1.getOrNull(item) != null && survivingTeam2.getOrNull(item) != null) {
                    survivingTeam1[item].attack(survivingTeam2[Random.nextInt(survivingTeam2.indices)])
                    survivingTeam2[item].attack(survivingTeam1[Random.nextInt(survivingTeam1.indices)])
                }

        }
    }

    override fun toString(): String {
        return "theBattleContinues = $theBattleContinues"
    }
}