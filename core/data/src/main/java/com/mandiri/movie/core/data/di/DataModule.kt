/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DataModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.di

import com.mandiri.movie.core.data.repository.DatabaseRepository
import com.mandiri.movie.core.data.repository.DatabaseRepositoryImpl
import com.mandiri.movie.core.data.repository.HeartbeatRepository
import com.mandiri.movie.core.data.repository.HeartbeatRepositoryImpl
import com.mandiri.movie.core.data.repository.MovieRepository
import com.mandiri.movie.core.data.repository.MovieRepositoryImpl
import com.mandiri.movie.core.data.repository.UserRepository
import com.mandiri.movie.core.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository

    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    fun bindHeartbeatRepository(
        heartbeatRepositoryImpl: HeartbeatRepositoryImpl,
    ): HeartbeatRepository

    @Binds
    fun bindDatabaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl,
    ): DatabaseRepository

}