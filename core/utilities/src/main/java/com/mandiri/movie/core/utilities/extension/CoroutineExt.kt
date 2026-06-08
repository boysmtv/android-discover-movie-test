/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: CoroutineExt.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.utilities.extension

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

fun runSafeLaunch(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineScope(SupervisorJob() + dispatcher).launch {
        try {
            block()
        } catch (e: CancellationException) {
            Timber.tag("runSafeLaunch").e("Coroutine CancellationException error : ${e.message}")
        } catch (e: Exception) {
            Timber.tag("runSafeLaunch").e("Coroutine Exception error : ${e.message}")
        }
    }
}