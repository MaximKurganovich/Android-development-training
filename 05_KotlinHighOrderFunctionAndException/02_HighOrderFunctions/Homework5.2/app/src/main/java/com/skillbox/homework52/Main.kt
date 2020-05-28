package com.skillbox.homework51

fun main () {
    val example1 = Queue<Int>()
    example1.enqueue(5)
    example1.enqueue(50)
    example1.enqueue(501)

    println(example1.filter({it < 50}))
    println(example1.filter { it.toString().length <= 2 })
    println(example1.filter())
    println()

    val example2 = Queue<String>()
    example2.enqueue("first")
    example2.enqueue("second")
    example2.enqueue("third")

    println(example2.filter { it.length < 2 })
    println(example2.filter { it.contains("i") })
    println(example2.filter())





}