package com.skillbox.homework51

fun main () {
    val abs = Queue<Int>()
    abs.enqueue(5)
    abs.enqueue(50)
    abs.enqueue(501)

    println(abs.filter({it < 50}))
    println(abs.filter { it.toString().length <= 2 })



}