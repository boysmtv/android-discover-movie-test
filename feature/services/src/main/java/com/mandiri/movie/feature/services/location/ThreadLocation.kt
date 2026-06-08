/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ThreadLocation.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.location

import android.content.Context
import com.mandiri.movie.core.common.util.JsonUtil
import com.mandiri.movie.core.common.util.ServiceUtil
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import com.mandiri.movie.core.model.LocationModel
import com.mandiri.movie.core.utilities.PreferenceConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ThreadLocation {

    private lateinit var context: Context

    private lateinit var jsonUtil: JsonUtil

    private lateinit var dataStore: DataStorePreferences

    fun initComponent(
        context: Context,
        jsonUtil: JsonUtil,
        preferences: DataStorePreferences,
    ) {
        this.context = context
        this.jsonUtil = jsonUtil
        this.dataStore = preferences
    }

    suspend fun getLocation(): String {
        return withContext(Dispatchers.IO) {
            dataStore.getString(
                PreferenceConstants.Authorization.PREF_LOCATION
            ).getOrNull().orEmpty()
        }
    }

    suspend fun storeLocation(locationModel: LocationModel) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_LOCATION,
                jsonUtil.toJson(locationModel)
            )
        }
    }

}