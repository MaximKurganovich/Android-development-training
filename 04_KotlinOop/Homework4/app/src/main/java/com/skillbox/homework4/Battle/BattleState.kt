package com.skillbox.homework4.Battle

sealed class BattleState {
    class Progress (team1: Team, team2: Team): BattleState () {
        init {
            println("""
                
                Первая команда:
                Количество живых бойцов = ${team1.teamWarrior.count{!it.isKilled}}
                Суммарное количество HP = ${team1.teamWarrior.sumBy { it.hp }}

                Вторая команда:
                Количество живых бойцов = ${team2.teamWarrior.count{!it.isKilled}}
                Суммарное количество HP = ${team2.teamWarrior.sumBy { it.hp }}
            """.trimIndent())
        }
    }

    class TeamOneWin (team1: Team) : BattleState() {
        init {
            println()
            println(
                """
                Победила первая команда:    
                Количество живых бойцов = ${team1.teamWarrior.count { !it.isKilled }}
                Суммарное количество HP = ${team1.teamWarrior.sumBy { it.hp }}
            """.trimIndent()
            )
        }
    }

    class TeamTwoWin(team2: Team) : BattleState() {

        init {
            println()
            println(
                """
                Победила вторая команда:    
                Количество живых бойцов = ${team2.teamWarrior.count { !it.isKilled }}
                Суммарное количество HP = ${team2.teamWarrior.sumBy { it.hp }}
            """.trimIndent()
            )
        }
    }

    object Draw : BattleState() {
        init {
            println()
            println("Ничья")
        }
    }
}