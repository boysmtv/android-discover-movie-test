/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserDao.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mandiri.movie.core.model.db.FavouriteEntity
import com.mandiri.movie.core.model.db.HeartbeatEntity
import com.mandiri.movie.core.model.db.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeartbeat(heartbeat: HeartbeatEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favourite: FavouriteEntity): Long

    @Query("SELECT * FROM UserEntity")
    suspend fun getUser(): List<UserEntity>

    @Query("SELECT * FROM HeartbeatEntity")
    suspend fun getHeartbeat(): List<HeartbeatEntity>

    @Query("SELECT * FROM FavouriteEntity")
    suspend fun getFavorite(): List<FavouriteEntity>
}