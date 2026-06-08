/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: BaseResponse.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code") var code: Int? = null,
    @SerialName("status") var status: String? = null,
    @SerialName("data") var data: T? = null
)