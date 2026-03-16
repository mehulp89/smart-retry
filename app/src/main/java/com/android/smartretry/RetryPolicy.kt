package com.android.smartretry

data class RetryPolicy(
    val maxRetries: Int = 3,
    val initialDelay: Long = 1000,
    val maxDelay: Long = 10000,
    val factor: Double = 2.0
)