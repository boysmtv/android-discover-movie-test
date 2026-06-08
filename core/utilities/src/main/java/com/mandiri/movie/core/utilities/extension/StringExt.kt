/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: StringExt.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.utilities.extension

import java.util.Locale

fun String.capitalize(): String {
    return this.trim().split("\\s+".toRegex())
        .joinToString(" ") { first ->
            first.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            }
        }
}

fun String.isAvailable(): String {
    return if (this.isNotEmpty()) "available" else "not available"
}