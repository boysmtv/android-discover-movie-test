/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ApiMovieResources.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.network

import io.ktor.resources.Resource

@Resource("movie/popular")
class PopularMovie

@Resource("movie/top_rated")
class TopRatedMovie

@Resource("movie/upcoming")
class UpComingMovie

@Resource("movie/now_playing")
class NowPlayingMovie

@Resource("movie")
class DetailMovie

@Resource("movie")
class DetailVideos

@Resource("search/movie")
class SearchMovie

@Resource("movie")
class CreditsMovie