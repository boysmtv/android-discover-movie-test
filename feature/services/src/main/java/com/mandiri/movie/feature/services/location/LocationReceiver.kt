/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: LocationReceiver.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.services.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val background = Intent(context, LocationService::class.java)
        context.startService(background)
    }

}
