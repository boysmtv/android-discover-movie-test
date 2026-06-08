/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: NavigatorModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.nav.di

import com.mandiri.movie.core.nav.navigator.ParentNavigator
import com.mandiri.movie.core.nav.navigator.MovieNavigator
import com.mandiri.movie.core.nav.navigator.MenuNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Singleton
    @Provides
    fun provideMovieNavigator(): MovieNavigator = MovieNavigator()

    @Singleton
    @Provides
    fun provideAuthNavigator(): ParentNavigator = ParentNavigator()

    @Singleton
    @Provides
    fun provideParentNavigator(): MenuNavigator = MenuNavigator()

}
