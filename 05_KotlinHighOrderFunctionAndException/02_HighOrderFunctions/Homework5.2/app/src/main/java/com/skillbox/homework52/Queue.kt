package com.skillbox.homework51

class Queue <T> {
    private val queue: MutableList<T> = mutableListOf()
    private var defaultFilter: (T) -> Boolean = {it.toString().length < 3}
    fun bool (x: Int) = x % 2 != 0

    fun getQueue(): MutableList<T> {
        return queue
    }

    fun enqueue(item: T) {
        queue.add(item)
    }

    fun dequeue(): T? {
        val a = queue.firstOrNull()
        if (a != null) queue.removeAt(0)
        return a
    }

    fun filter (filt: (T) -> Boolean = defaultFilter): Queue<T> {
        val newObj = Queue<T>()
        for (item in queue.indices) {
            if (filt(queue[item])) newObj.enqueue(queue[item])
        }
        return newObj
    }

    override fun toString(): String {
        return "Queue($queue)"
    }

}

