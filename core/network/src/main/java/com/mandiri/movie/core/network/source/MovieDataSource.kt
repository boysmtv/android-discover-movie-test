/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieDataSource.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.network.source

import com.mandiri.movie.core.model.CreditsModel
import com.mandiri.movie.core.model.MovieDetailModel
import com.mandiri.movie.core.model.MovieModel
import com.mandiri.movie.core.model.MovieSearchModel
import com.mandiri.movie.core.model.VideoDetailModel

interface MovieDataSource {

    suspend fun getPopular(page: Int): MovieModel

    suspend fun getTopRated(page: Int): MovieModel

    suspend fun getUpComing(page: Int): MovieModel

    suspend fun getNowPlaying(page: Int): MovieModel

    suspend fun getDetailMovie(movieId: String, language: String): MovieDetailModel

    suspend fun getDetailVideos(movieId: String, language: String): VideoDetailModel

    suspend fun searchMovie(page: Int, searchModel: MovieSearchModel): MovieModel

    suspend fun getCredits(movieId: String): CreditsModel

}