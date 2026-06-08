/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DatabaseUseCase.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.db.FavouriteEntity
import com.mandiri.movie.core.model.db.HeartbeatEntity
import com.mandiri.movie.core.model.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseUseCase {

    fun getUser(): Flow<Result<List<UserEntity>>>

    fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>>

    fun getFavourite(): Flow<Result<List<FavouriteEntity>>>

}