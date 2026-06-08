/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
@JsonClass(generateAdapter = true)
data class HeartbeatModel(
    @Json(name = "deviceId") var deviceId: String? = null,
    @Json(name = "deviceName") var deviceName: String? = null,
    @Json(name = "user") var user: UserModel? = null,
    @Json(name = "location") var location: LocationModel? = null,
    @Json(name = "createdAt") var createdAt: String? = null,
)