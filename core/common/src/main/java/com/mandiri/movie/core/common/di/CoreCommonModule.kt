/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: CoreCommonModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreCommonModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

//    @Singleton
//    @Provides
//    fun provideCorePrefManager(@ApplicationContext context: Context) = CorePrefManager(context)

}