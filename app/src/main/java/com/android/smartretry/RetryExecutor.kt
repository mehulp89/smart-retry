package com.android.smartretry

import kotlinx.coroutines.delay

object RetryExecutor {

    suspend fun <T> execute(
        policy: RetryPolicy = RetryPolicy(),
        block: suspend () -> T
    ): T {

        var currentDelay = policy.initialDelay

        repeat(policy.maxRetries - 1) {

            try {
                return block()
            } catch (e: Exception) {
                delay(currentDelay)
                currentDelay = (currentDelay * policy.factor).toLong()
                    .coerceAtMost(policy.maxDelay)
            }

        }

        return block()
    }
}