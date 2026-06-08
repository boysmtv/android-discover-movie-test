/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: PreferencesModule.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import com.mandiri.movie.core.common.data.preferences.DataStorePreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.mandiri.movie.feature.auth.presentation.ui.preferences"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindPreferencesDataStoreRepository(
        preferencesRepositoryImpl: DataStorePreferencesImpl
    ): DataStorePreferences

    companion object {
        @Provides
        @Singleton
        fun providePreferencesDataStoreModule(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.preferencesDataStore
        }
    }
}