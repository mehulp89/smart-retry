package com.android.smartretry

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val result = RetryExecutor.execute {

        println("Trying network call")

        if(Math.random() < 0.7){
            throw RuntimeException("Network failed")
        }

        "Success"

    }

    println(result)
}