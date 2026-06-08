/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ResultCallback.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.util.network

sealed interface ResultCallback<out T> {
    data class Success<T>(val data: T) : ResultCallback<T>
    data class Error(val message: String) : ResultCallback<Nothing>
    object Loading : ResultCallback<Nothing>
}