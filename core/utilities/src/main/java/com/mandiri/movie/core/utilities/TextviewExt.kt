/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: TextviewExt.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.utilities

import android.widget.TextView

fun TextView.setTextAnimation(text: String, duration: Long = 200, completion: (() -> Unit)? = null) {
    fadOutAnimation(duration) {
        this.text = text
        fadInAnimation(duration) {
            completion?.let {
                it()
            }
        }
    }
}