/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatSourceModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.network.di

import com.mandiri.movie.core.network.source.HeartbeatDataSource
import com.mandiri.movie.core.network.source.HeartbeatDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HeartbeatSourceModule {

    @Binds
    fun binds(impl: HeartbeatDataSourceImpl): HeartbeatDataSource

}