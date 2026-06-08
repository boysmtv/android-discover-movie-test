/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatUseCaseImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.data.repository.HeartbeatRepository
import com.mandiri.movie.core.model.HeartbeatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeartbeatUseCaseImpl @Inject constructor(
    private val repository: HeartbeatRepository
) : HeartbeatUseCase {

    override fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.storeHeartbeatToFirestore(id, model, onLoad, onSuccess, onError)
    }

}