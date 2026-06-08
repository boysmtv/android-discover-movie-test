/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatRepository.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.HeartbeatModel
import kotlinx.coroutines.flow.Flow

interface HeartbeatRepository {

    // start region to firestore
    // ===============================================================

    fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

}