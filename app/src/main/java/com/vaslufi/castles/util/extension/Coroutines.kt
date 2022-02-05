package com.vaslufi.castles.util.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Syntactic sugar to collect a flow in the specified [scope]. Using this can avoid situations where there's extra code written
 * after a collect call (which calls tend to never complete normally).
 */
inline fun <T> Flow<T>.collectIn(scope: CoroutineScope, crossinline action: suspend (value: T) -> Unit): Job =
    scope.launch { this@collectIn.collect(action) }
