/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: DomainModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain.di

import com.mandiri.movie.core.domain.DatabaseUseCase
import com.mandiri.movie.core.domain.DatabaseUseCaseImpl
import com.mandiri.movie.core.domain.HeartbeatUseCase
import com.mandiri.movie.core.domain.HeartbeatUseCaseImpl
import com.mandiri.movie.core.domain.MovieUseCase
import com.mandiri.movie.core.domain.MovieUseCaseImpl
import com.mandiri.movie.core.domain.UserUseCase
import com.mandiri.movie.core.domain.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindMovieUseCase(
        movieUseCaseImpl: MovieUseCaseImpl,
    ): MovieUseCase

    @Binds
    fun bindUserUseCase(
        userUseCaseImpl: UserUseCaseImpl,
    ): UserUseCase

    @Binds
    fun bindHeartbeatUseCase(
        heartbeatUseCaseImpl: HeartbeatUseCaseImpl
    ): HeartbeatUseCase

    @Binds
    fun bindDatabaseUseCase(
        databaseUseCaseImpl: DatabaseUseCaseImpl
    ): DatabaseUseCase

}