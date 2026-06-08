/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: BaseDataDialogGeneral.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.ui.dialog.base

import androidx.annotation.DrawableRes

data class BaseDataDialogGeneral(
    val title: String? = "",
    val message: String? = "",
    @DrawableRes val icon: Int? = null,
    val textPrimaryButton: String?,
    var secondaryIsVisible: Boolean? = false,
    val isCancelable: Boolean = true,
    val dismissOnAction: Boolean = false,
    val visibleBackToSplash: Boolean = true,
)
