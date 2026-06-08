/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieRepository.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.CreditsModel
import com.mandiri.movie.core.model.MovieDetailModel
import com.mandiri.movie.core.model.MovieModel
import com.mandiri.movie.core.model.MovieSearchModel
import com.mandiri.movie.core.model.VideoDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopular(page: Int): Result<MovieModel>

    suspend fun getTopRated(page: Int): Result<MovieModel>

    suspend fun getUpComing(page: Int): Result<MovieModel>

    fun getBanner(page: Int): Flow<Result<MovieModel>>

    fun getDetailMovie(movieId: String, language: String): Flow<Result<MovieDetailModel>>

    fun getDetailVideos(movieId: String, language: String): Flow<Result<VideoDetailModel>>

    suspend fun searchMovie(page: Int, searchModel: MovieSearchModel) : Result<MovieModel>

    fun getCredits(movieId: String) : Flow<Result<CreditsModel>>
}