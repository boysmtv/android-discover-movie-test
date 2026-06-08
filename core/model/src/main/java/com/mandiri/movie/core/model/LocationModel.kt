/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: LocationModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(
    @SerialName("latitude") var latitude: Double = 0.0,
    @SerialName("longitude") var longitude: Double = 0.0,
)