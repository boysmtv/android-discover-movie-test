/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HomeViewModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.domain.MovieUseCase
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.model.MovieModel
import com.mandiri.movie.core.utilities.MovieCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<MovieDataModel>> = movieUseCase.getMovie(MovieCategories.POPULAR)
        .cachedIn(viewModelScope)

    val topRatedMovies: Flow<PagingData<MovieDataModel>> = movieUseCase.getMovie(MovieCategories.TOP_RATED)
        .cachedIn(viewModelScope)

    val upComingMovies: Flow<PagingData<MovieDataModel>> = movieUseCase.getMovie(MovieCategories.UP_COMING)
        .cachedIn(viewModelScope)

    private val _nowPlayingMovies: MutableStateFlow<Result<MovieModel>> = MutableStateFlow(Result.Loading)
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    init {
        nowPlayingMovies()
    }

    fun nowPlayingMovies() {
        movieUseCase.getBanner(1)
            .onEach { _nowPlayingMovies.value = it }
            .launchIn(viewModelScope)
    }

}