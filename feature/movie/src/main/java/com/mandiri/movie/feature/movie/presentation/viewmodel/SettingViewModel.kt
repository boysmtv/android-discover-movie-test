/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SettingViewModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.movie.core.common.util.event.DataStoreCacheEvent
import com.mandiri.movie.core.common.util.JsonUtil
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil,
) : ViewModel() {

    fun fetchDataAuth() =
        flow {
            val data = dataStore.getString(PreferenceConstants.Authorization.PREF_USER).getOrNull().orEmpty()
            if (data.isNotEmpty() && data.isNotBlank()) {
                emit(
                    DataStoreCacheEvent.FetchSuccess(
                        jsonUtil.fromJson<UserModel>(data)
                    )
                )
            } else {
                emit(DataStoreCacheEvent.FetchError)
            }
        }

    fun fetchDataTokenFcm() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_FCM_TOKEN
                    ).getOrNull().orEmpty()
                )
            )
        }

    fun storeTokenToDataStore(token: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    fun clearPreferences(token: String) {
        viewModelScope.launch {
            dataStore.clearPreferences()
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
        }
    }

}