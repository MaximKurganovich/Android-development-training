package com.skillbox.homework51

fun main () {
    // exercise #1
    val list = listOf<Number>(125.54f, 53, 248.0, 871236L, -36)
    val (realNumbers, wholeNumbers) =  genericFilter(list).partition { it is Double || it is Float }
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
    var evenNumbers: List<T>
    val number = mutableListOf<T>()
    var range = list.indices
    var translator: Long
    for (item in range) {
        if (list[item] is Double || list[item] is Float ) {
            translator = list[item].toLong()
            if (translator.toDouble() == list[item] || translator.toFloat() == list[item]) {
                number.add(list[item])
            }
        } else number.add(list[item])
    }
    evenNumbers = number.filter { it.toLong() % 2 == 0.toLong() }
    return evenNumbers
}