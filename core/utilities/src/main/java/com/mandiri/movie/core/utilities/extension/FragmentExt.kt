/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: FragmentExt.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.utilities.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

fun Fragment.replaceFragment(
    container: FragmentContainerView,
    fragment: Fragment
) {
    childFragmentManager.beginTransaction()
        .replace(container.id, fragment)
        .commit()
}