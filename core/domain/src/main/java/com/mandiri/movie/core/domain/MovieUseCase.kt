/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieUseCase.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain

import androidx.paging.PagingData
import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.CreditsModel
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.model.MovieDetailModel
import com.mandiri.movie.core.model.MovieModel
import com.mandiri.movie.core.model.MovieSearchModel
import com.mandiri.movie.core.model.VideoDetailModel
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.MovieCategories
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getBanner(page: Int): Flow<Result<MovieModel>>

    fun getMovie(categories: MovieCategories): Flow<PagingData<MovieDataModel>>

    fun getDetailMovie(movieId: String, language: String = Constant.MOVIE_LANGUAGE): Flow<Result<MovieDetailModel>>

    fun getDetailVideo(movieId: String, language: String = Constant.MOVIE_LANGUAGE): Flow<Result<VideoDetailModel>>

    fun searchMovie(searchModel: MovieSearchModel): Flow<PagingData<MovieDataModel>>

    fun getCredits(movieId: String): Flow<Result<CreditsModel>>

}