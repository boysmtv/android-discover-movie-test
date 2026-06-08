/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatReceiver.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.heartbeat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class HeartbeatReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, HeartbeatService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            }
        }
    }

}
