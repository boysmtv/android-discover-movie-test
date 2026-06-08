/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatRepositoryImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.util.network.execute
import com.mandiri.movie.core.model.HeartbeatModel
import com.mandiri.movie.core.network.source.HeartbeatDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HeartbeatRepositoryImpl @Inject constructor(
    private val network: HeartbeatDataSource
) : HeartbeatRepository {

    override fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.storeHeartbeatToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

}