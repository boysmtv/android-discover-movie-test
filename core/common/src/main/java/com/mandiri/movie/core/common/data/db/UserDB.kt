/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserDB.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mandiri.movie.core.common.data.db.dao.UserDao
import com.mandiri.movie.core.model.db.FavouriteEntity
import com.mandiri.movie.core.model.db.HeartbeatEntity
import com.mandiri.movie.core.model.db.UserEntity

@Database(
    entities = [UserEntity::class, HeartbeatEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDB : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}