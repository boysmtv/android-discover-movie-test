/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ThreadHeartbeat.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.heartbeat

import android.content.Context
import com.mandiri.movie.core.common.util.JsonUtil
import com.mandiri.movie.core.common.util.ServiceUtil
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import com.mandiri.movie.core.domain.HeartbeatUseCase
import com.mandiri.movie.core.model.HeartbeatModel
import com.mandiri.movie.core.model.LocationModel
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.utilities.PreferenceConstants
import com.mandiri.movie.core.common.util.TransactionUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ThreadHeartbeat {

    private val tag = this::class.java.simpleName

    private lateinit var context: Context

    private lateinit var jsonUtil: JsonUtil

    private lateinit var dataStore: DataStorePreferences

    private lateinit var useCase: HeartbeatUseCase

    private lateinit var serviceUtil: ServiceUtil

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    fun initComponent(
        context: Context,
        jsonUtil: JsonUtil,
        preferences: DataStorePreferences,
        useCase: HeartbeatUseCase
    ) {
        this.context = context
        this.jsonUtil = jsonUtil
        this.dataStore = preferences
        this.useCase = useCase

        serviceUtil = ServiceUtil(context)
    }

    fun storeHeartbeat(heartbeatModel: HeartbeatModel) {
        heartbeatModel.apply {
            user = runBlocking { getUser() }
        }

        coroutineScope.launch {
            useCase.storeHeartbeatToFirestore(
                id = TransactionUtil.generateTransactionID(),
                model = heartbeatModel,
                onLoad = { },
                onSuccess = { },
                onError = { }
            ).collect()
        }
    }

    suspend fun getLocation(): LocationModel = withContext(Dispatchers.IO) {
        val data = dataStore.getString(PreferenceConstants.Authorization.PREF_LOCATION).getOrNull().orEmpty()
        if (data.isNotEmpty() && data.isNotBlank())
            jsonUtil.fromJson<LocationModel>(data) ?: LocationModel()
        else LocationModel()
    }

    private suspend fun getUser(): UserModel = withContext(Dispatchers.IO) {
        val data = dataStore.getString(PreferenceConstants.Authorization.PREF_USER).getOrNull().orEmpty()
        if (data.isNotEmpty() && data.isNotBlank())
            jsonUtil.fromJson<UserModel>(data) ?: UserModel()
        else UserModel()
    }

    private suspend fun storeToPreferences(message: String) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_HEARTBEAT,
                message
            )
        }
    }

}