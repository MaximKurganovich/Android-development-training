package com.skillbox.homework51

class Queue <T> {
    val queue: MutableList<T> = mutableListOf()

    fun enqueue(item: T) {
        queue.add(item)
    }

    fun dequeue(): T? {
        val a = queue.firstOrNull()
        if (a != null) queue.removeAt(0)
        return a
    }
}