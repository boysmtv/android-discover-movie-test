/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: BaseDataDialog.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.ui.dialog.base

import androidx.annotation.DrawableRes
import com.mandiri.movie.core.utilities.Constant

class BaseDataDialog(
    val title: String,
    val content: String,
    val secondaryButtonShow: Boolean,
    val secondaryButtonText: String,
    val primaryButtonShow: Boolean,
    val primaryButtonText: String,
    @DrawableRes val primaryButtonIcon: Int? = null,
    @DrawableRes val secondaryButtonIcon: Int? = null,
    @DrawableRes val icon: Int? = null,
    val buttonWithIconShow: Boolean = false,
    val buttonWithIconText: String = Constant.EMPTY_STRING,
    val isIconShow: Boolean = false
)