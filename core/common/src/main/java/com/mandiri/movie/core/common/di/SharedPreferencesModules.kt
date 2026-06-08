/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SharedPreferencesModules.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModules {

//    @Singleton
//    @Provides
//    fun providesSharedPreferences(context: Application): SharedPreferences {
//        return context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
//    }

}