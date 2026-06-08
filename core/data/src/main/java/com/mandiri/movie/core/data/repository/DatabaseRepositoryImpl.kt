/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DatabaseRepositoryImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.data.db.dao.UserDao
import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.db.FavouriteEntity
import com.mandiri.movie.core.model.db.HeartbeatEntity
import com.mandiri.movie.core.model.db.UserEntity
import com.mandiri.movie.core.utilities.Constant.DATA_IS_EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : DatabaseRepository {

    override fun getUser(): Flow<Result<List<UserEntity>>> =
        flow {
            emit(Result.Loading)
            val value = userDao.getUser()
            if (value.isNotEmpty()) {
                emit(Result.Success(value))
            } else {
                emit(Result.Error(Exception(DATA_IS_EMPTY)))
            }
        }.flowOn(Dispatchers.IO)

    override fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>> =
        flow {
            emit(Result.Loading)
            val value = userDao.getHeartbeat()
            if (value.isNotEmpty()) {
                emit(Result.Success(value))
            } else {
                emit(Result.Error(Exception(DATA_IS_EMPTY)))
            }
        }.flowOn(Dispatchers.IO)

    override fun getFavourite(): Flow<Result<List<FavouriteEntity>>> =
        flow {
            emit(Result.Loading)
            val value = userDao.getFavorite()
            if (value.isNotEmpty()) {
                emit(Result.Success(value))
            } else {
                emit(Result.Error(Exception(DATA_IS_EMPTY)))
            }
        }.flowOn(Dispatchers.IO)

    override fun addUser(data: UserEntity): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val value = userDao.addUser(data)
            if (value > 0) {
                emit(Result.Success("Success"))
            } else {
                emit(Result.Error(Exception(DATA_IS_EMPTY)))
            }
        }.flowOn(Dispatchers.IO)


    override fun addHeartbeat(data: HeartbeatEntity): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val value = userDao.addHeartbeat(data)
            if (value > 0) {
                emit(Result.Success("Success"))
            } else {
                emit(Result.Error(Exception(DATA_IS_EMPTY)))
            }
        }.flowOn(Dispatchers.IO)

    override fun addFavourite(data: FavouriteEntity): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val value = userDao.addFavorite(data)
            if (value > 0) {
                emit(Result.Success("Success"))
            } else {
                emit(Result.Error(Exception(DATA_IS_EMPTY)))
            }
        }.flowOn(Dispatchers.IO)

}