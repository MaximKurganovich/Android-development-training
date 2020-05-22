package com.skillbox.homework51

sealed class Result<out T, R>


    data class Success <T, R>(val success: T): Result <T, R>()
    data class Error <T, R> (val error: R): Result<T, R>()

fun result (): Result<Int, String> {
    val success: Result<Int, String> = Success(42)
    val error: Result<Int, String> = Error("Error")
    return success
}


