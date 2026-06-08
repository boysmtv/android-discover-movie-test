/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MyBackgroundService.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.sample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mandiri.movie.core.utilities.Constant.THREAD_SLEEP_TIMER_2_SECOND

class MyBackgroundService : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                try {
                    Thread.sleep(THREAD_SLEEP_TIMER_2_SECOND)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}