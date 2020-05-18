package com.skillbox.homework4.Battle

sealed class BattleState {
    class Progress (team1: Team, team2: Team): BattleState () {
        var sumHPTeam1 = team1.teamWarrior.sumBy { it.hp }
        var sumHPTeam2 = team2.teamWarrior.sumBy { it.hp }

        fun countNumberOfLiveFighters (team: Team): Int {
            val range = 0 until team.teamWarrior.size
            var count = 0
            for (item in range) {
                if (team.teamWarrior[item].hp > 0) count++
            }
            return count
        }
        init {
            println("""
                
                Первая команда:
                Количество живых бойцов = ${countNumberOfLiveFighters(team1)}
                Суммарное количество HP = $sumHPTeam1

                Вторая команда:
                Количество живых бойцов = ${countNumberOfLiveFighters(team2)}
                Суммарное количество HP = $sumHPTeam2
            """.trimIndent())
        }
    }

    object TeamOneWin : BattleState() {
        init {
            println()
            println("Победила первая команда")
        }
    }

    object TeamTwoWin : BattleState() {
        init {
            println()
            println("Победила вторая команда")
        }
    }

    object Draw : BattleState() {
        init {
            println()
            println("Ничья")
        }
    }
}