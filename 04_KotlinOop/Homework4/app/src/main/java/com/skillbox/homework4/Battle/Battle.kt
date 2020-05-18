package com.skillbox.homework4.Battle

import com.skillbox.homework4.Units.AbstractWarrior

class Battle (numberInTeam:Int) {
    val team1: Team = Team(numberInTeam)
    val team2: Team = Team(numberInTeam)
    var theBattleContinues = true

    fun statusBattle (): BattleState {
        return if (team1.teamWarrior.sumBy { it.hp } <= 0 && team2.teamWarrior.sumBy { it.hp } <= 0) {
            theBattleContinues = false
            BattleState.Draw
        } else if (team2.teamWarrior.sumBy { it.hp } <= 0) {
            theBattleContinues = false
            BattleState.TeamOneWin
        } else if (team1.teamWarrior.sumBy { it.hp } <= 0) {
            theBattleContinues = false
            BattleState.TeamTwoWin
        } else BattleState.Progress (team1, team2)
    }

    fun shuffled () {
        val survivingTeam1: MutableList<AbstractWarrior> = mutableListOf()
        val survivingTeam2: MutableList<AbstractWarrior> = mutableListOf()
        var range = 0 until team1.teamWarrior.size
        for (item in range) {
            if (team1.teamWarrior[item].hp > 0) survivingTeam1.add(team1.teamWarrior[item])
        }
        for (item in range) {
            if (team2.teamWarrior[item].hp > 0) survivingTeam2.add(team2.teamWarrior[item])
        }
        range = 0 until maxOf (team1.teamWarrior.size, team2.teamWarrior.size)
        for (item in range) {
            if (survivingTeam1.getOrNull(item) != null && survivingTeam2.getOrNull(item) != null) {
            survivingTeam1[item].attack(survivingTeam2[item])
            survivingTeam2[item].attack(survivingTeam1[item])}
        }
    }

    override fun toString(): String {
        return "theBattleContinues = $theBattleContinues"
    }
}