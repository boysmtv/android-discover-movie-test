/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DatabaseModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.di

import android.content.Context
import androidx.room.Room
import com.mandiri.movie.core.common.data.db.UserDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDB(@ApplicationContext context : Context) : UserDB {
        return Room.databaseBuilder(context, UserDB::class.java, "UserDB").build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: UserDB) = db.getUserDao()

}