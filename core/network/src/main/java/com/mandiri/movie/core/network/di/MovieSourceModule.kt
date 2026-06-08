package com.mandiri.movie.core.network.di

import com.mandiri.movie.core.network.source.MovieDataSource
import com.mandiri.movie.core.network.source.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MovieSourceModule {

    @Binds
    fun MovieDataSourceImpl.binds(): MovieDataSource

}