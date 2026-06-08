/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DatabaseUseCaseImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.data.repository.DatabaseRepository
import com.mandiri.movie.core.model.db.FavouriteEntity
import com.mandiri.movie.core.model.db.HeartbeatEntity
import com.mandiri.movie.core.model.db.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseUseCaseImpl @Inject constructor(
    private val repository: DatabaseRepository
) : DatabaseUseCase {

    override fun getUser(): Flow<Result<List<UserEntity>>> = repository.getUser()

    override fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>> = repository.getHeartbeat()

    override fun getFavourite(): Flow<Result<List<FavouriteEntity>>> = repository.getFavourite()

}