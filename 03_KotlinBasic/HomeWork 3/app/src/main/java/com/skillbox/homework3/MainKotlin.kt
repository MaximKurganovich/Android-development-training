package com.skillbox.homework3

import kotlin.math.abs

fun main () {
    // Пункт 1
    println ("Введите число больше 0:")
    var N: Int? = readLine()?.toIntOrNull()
    var numberCycle = true
    while (numberCycle) {
        if (N == null || N < 1) {
            println(
                """
            Введено не число или число меньше 1
            Введите положительное число
            """.trimIndent()
            )
            N = readLine()?.toIntOrNull()
        } else numberCycle = false
    }

    // Пункт 3
    val numberList = mutableListOf<Int>()
    numberList.addAll(keyboardInput(N!!))


    // Пункт 5
    val range: IntProgression = 0 until numberList.size
    var counter = 0
    for (item in range) {
        if (numberList[item] > 0) counter++
    }
    println()
    println("Количество положительных чисел в списке = $counter")

    // Пункт 6
    println("Четные числа в списке ${numberList.filterNot { it % 2 == 1 || it % 2 == -1}}")

    // Пункт 7
    val numberSet = mutableSetOf<Int>()
    numberSet.addAll(numberList)
    println()
    println("Количество введенных уникальных чисел = ${numberSet.size}")

    //Пункт 8
    println("Сумма всех введенных чисел = ${numberList.sum()}")


    //Пункт 10
    val numberMap = mutableMapOf<Int, Int>()
    for (item in range) {
        numberMap[numberList[item]] = gcdRecursion(numberList.sum(), numberList[item])
    }

    //Пункт 11
    for (item in range) {
        println("Число = ${numberList[item]}, сумма = ${numberList.sum()}, НОД = ${numberMap[numberList[item]]} ")
    }


}

// Пункт 2-4
fun keyboardInput (N:Int): List<Int> {
    val range: IntProgression = 1..N
    val numberList = mutableListOf<Int>()
    for (item in range) {
        println("Введите число для списка")
        var number: Int? = readLine()?.toIntOrNull()
        if (number != null) {
            println("Введеное число  = $number")
            println("Осталось ввести чисел ${N-item}")
            numberList.add(number)
        } else {
            println("Введено не число")
            println("Строка игнорируется")
            println("Осталось ввести чисел ${N-item}")
        }
    }
    return numberList
}

//Пункт 9
tailrec fun gcdRecursion (a: Int, b:Int): Int {
    if (a == 0) {
        return abs(b)
    }
    if (b == 0) {
        return abs(a)
    }
    return gcdRecursion(abs(b), abs(a%b))
}