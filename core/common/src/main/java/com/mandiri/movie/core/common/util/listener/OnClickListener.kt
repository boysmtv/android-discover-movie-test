/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: OnClickListener.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.util.listener

import androidx.viewbinding.ViewBinding
import com.mandiri.movie.core.model.CastModel
import com.mandiri.movie.core.model.CrewModel
import com.mandiri.movie.core.model.FavouriteDataModel
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.model.RecentDataModel

typealias OnClickMovie = (MovieDataModel) -> Unit

typealias OnClickCreditsCast = (CastModel) -> Unit

typealias OnClickCreditsCrew = (CrewModel) -> Unit

typealias OnClickFavourite = (FavouriteDataModel) -> Unit

typealias OnClickRecent = (RecentDataModel) -> Unit