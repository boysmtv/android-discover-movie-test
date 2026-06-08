/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DatabaseRepository.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.db.FavouriteEntity
import com.mandiri.movie.core.model.db.HeartbeatEntity
import com.mandiri.movie.core.model.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getUser(): Flow<Result<List<UserEntity>>>

    fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>>

    fun getFavourite(): Flow<Result<List<FavouriteEntity>>>

    fun addUser(data: UserEntity): Flow<Result<String>>

    fun addHeartbeat(data: HeartbeatEntity): Flow<Result<String>>

    fun addFavourite(data: FavouriteEntity): Flow<Result<String>>

}