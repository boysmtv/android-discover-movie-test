/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieCallback.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.callback

import androidx.recyclerview.widget.DiffUtil
import com.mandiri.movie.core.model.MovieDataModel

class MovieCallback : DiffUtil.ItemCallback<MovieDataModel>() {
    override fun areItemsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem == newItem
    }
}