package com.vaslufi.castles.common

/**
 * A bit cleaner implementation of Kotlin's Result type that can be used as the return type for
 * any use case or service function that may fail. This class is intended to make error handling
 * cleaner than using try-catch constructs everywhere.
 */
sealed class Result<T> {
    /**
     * Returned when the operation succeeded. Result is accessible in [value].
     */
    data class Success<T>(val value: T) : Result<T>()

    /**
     * Returned when the operation failed. Failure reason is in [cause].
     */
    data class Failure<T>(val cause: Throwable) : Result<T>()
}
