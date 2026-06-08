/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DialogExtension.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.ui.util

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.showDialog(fragment: DialogFragment, tag: String) {
    if (this.findFragmentByTag(tag)?.isAdded != true) {
        fragment.showNow(this, tag)
    }
}

fun FragmentManager.showDialog(fragment: DialogFragment) {
    showDialog(fragment, fragment.tag ?: fragment::class.java.simpleName)
}