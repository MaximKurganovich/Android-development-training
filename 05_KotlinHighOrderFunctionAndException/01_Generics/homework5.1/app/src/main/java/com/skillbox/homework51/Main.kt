package com.skillbox.homework51

import kotlin.math.round

fun main () {
    // exercise #1
    val list = listOf<Number>( 125.54f, Long.MAX_VALUE + 2.1, 53, 248.0, 871236L, -36, 124f, Int.MAX_VALUE - 1, Long.MAX_VALUE-4)
    val (realNumbers, wholeNumbers) =  genericF(list).partition { it is Double || it is Float }
    println("Четные вещественные числа: $realNumbers")
    println("Четные целые числа: $wholeNumbers")

    // exercise #2
    var a = Queue<Number>()
    println()
    println(a.dequeue())
    for (i in 0..15) {
        a.enqueue(i)
    }
    println(a.dequeue())
    println(a.dequeue())

    // exercise #3
    val valueNumString: Result<Number, String> = result()
    val valueAnyString: Result<Any, String> = result()
//    val valueIntChar: Result<Int, CharSequence> = result()
//    val valueIntAny: Result<Int, Any> = result()
    
}

fun <T: Number> genericFilter (list: List<T>): List<T> {
    var (realNumbers, wholeNumbers: List<T>) = list.partition { it is Double || it is Float }
    realNumbers = realNumbers.filter { it.toDouble() % 2 == 0.0 }
    wholeNumbers = wholeNumbers.filter { it.toLong() % 2 == 0L }
    return wholeNumbers + realNumbers
}

fun <T:Number> genericF(list: List<T>) : List<T> {
    val evenList: MutableList<T> = mutableListOf()
    for (x in list) {
        if (x == Long.MAX_VALUE) {
            continue
        }
        val l = x.toLong()
        if (l < Long.MAX_VALUE) {
            if (round(x.toDouble()) == x.toDouble() && l % 2 == 0L) {
                evenList.add(x)
            }
        } else {
            evenList.add(x)
        }
    }
    return evenList
}