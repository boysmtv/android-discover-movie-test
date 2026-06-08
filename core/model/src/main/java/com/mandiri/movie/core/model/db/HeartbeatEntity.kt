/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatEntity.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeartbeatEntity(
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val body: String
)