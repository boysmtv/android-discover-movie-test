/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    @SerialName("page") var page: Int = 0,
    @SerialName("results") var results: List<MovieDataModel> = arrayListOf(),
    @SerialName("total_pages") var totalPages: Int = 0,
    @SerialName("total_results") var totalResults: Int = 0
)
