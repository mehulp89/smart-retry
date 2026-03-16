package com.android.smartretry

import kotlinx.coroutines.delay

class SmartRetry(
    private val maxAttempts: Int = 3,
    private val initialDelay: Long = 1000,
    private val maxDelay: Long = 10000,
    private val factor: Double = 2.0
) {

    suspend fun <T> execute(block: suspend () -> T): T {
        var currentDelay = initialDelay

        repeat(maxAttempts - 1) { attempt ->
            try {
                return block()
            } catch (e: Exception) {
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
            }
        }

        return block()
    }
}