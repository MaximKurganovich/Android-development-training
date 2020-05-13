package com.skillbox.homework4

sealed class FireType(val countOfBullets: Int) {
    object Single: FireType(countOfBullets = 1)
    object Queue: FireType(countOfBullets = 5)
}