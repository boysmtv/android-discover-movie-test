/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserEntity.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val postId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)