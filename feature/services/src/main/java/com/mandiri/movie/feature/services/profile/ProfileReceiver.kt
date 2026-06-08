/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ProfileReceiver.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.profile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class ProfileReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            val serviceIntent = Intent(
                context,
                ProfileService::class.java
            )
            context!!.startForegroundService(serviceIntent)
        }
    }

}