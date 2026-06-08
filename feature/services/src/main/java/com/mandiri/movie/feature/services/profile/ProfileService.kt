/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ProfileService.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.profile

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mandiri.movie.core.common.util.JsonUtil
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import com.mandiri.movie.core.domain.UserUseCase
import com.mandiri.movie.core.utilities.Constant.THREAD_SLEEP_TIMER_2_SECOND
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.annotation.Nullable
import javax.inject.Inject

@AndroidEntryPoint
class ProfileService : Service() {

    private var threadProfile: ThreadProfile = ThreadProfile()

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    lateinit var preferences: DataStorePreferences

    @Inject
    lateinit var userUseCase: UserUseCase

    private var isServiceRunning = true

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    private val threadSleepTimer = THREAD_SLEEP_TIMER_2_SECOND

    override fun onCreate() {
        super.onCreate()

        threadProfile.initComponent(
            context = this@ProfileService,
            jsonUtil = jsonUtil,
            preferences = preferences,
            useCase = userUseCase
        )

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                while (isServiceRunning) {
                    try {
                        threadProfile.getTokenFirebase()
                        Thread.sleep(threadSleepTimer)
                        setupCheckToken()
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return START_STICKY
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun setupCheckToken() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                if (threadProfile.getUser().isNotEmpty() && threadProfile.getToken().isNotEmpty()) {
                    isServiceRunning = false
                    stopSelf()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

}