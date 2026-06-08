/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieSearchModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model

import com.mandiri.movie.core.utilities.Constant

data class MovieSearchModel(
    val title: String = Constant.EMPTY_STRING
)