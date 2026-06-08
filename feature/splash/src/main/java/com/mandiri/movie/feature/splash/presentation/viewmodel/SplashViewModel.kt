/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SplashViewModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mandiri.movie.core.common.util.JsonUtil
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil
) : ViewModel() 