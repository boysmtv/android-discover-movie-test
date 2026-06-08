package com.mandiri.movie.core.network.source

import com.mandiri.movie.core.model.CreditsModel
import com.mandiri.movie.core.model.MovieDetailModel
import com.mandiri.movie.core.model.MovieModel
import com.mandiri.movie.core.model.MovieSearchModel
import com.mandiri.movie.core.model.VideoDetailModel
import com.mandiri.movie.core.network.CreditsMovie
import com.mandiri.movie.core.network.DetailMovie
import com.mandiri.movie.core.network.DetailVideos
import com.mandiri.movie.core.network.KtorClient
import com.mandiri.movie.core.network.NowPlayingMovie
import com.mandiri.movie.core.network.PopularMovie
import com.mandiri.movie.core.network.SearchMovie
import com.mandiri.movie.core.network.TopRatedMovie
import com.mandiri.movie.core.network.UpComingMovie
import com.mandiri.movie.core.utilities.Constant.QUERY_PARAM_LANGUAGE
import com.mandiri.movie.core.utilities.Constant.QUERY_PARAM_PAGE
import com.mandiri.movie.core.utilities.Constant.QUERY_PARAM_QUERY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : MovieDataSource {

    override suspend fun getPopular(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = PopularMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getTopRated(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = TopRatedMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getUpComing(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = UpComingMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getNowPlaying(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = NowPlayingMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getDetailMovie(movieId: String, language: String): MovieDetailModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = DetailMovie(),
                query = mapOf(
                    QUERY_PARAM_LANGUAGE to language
                ),
                path = movieId
            )
        }
    }

    override suspend fun getDetailVideos(movieId: String, language: String): VideoDetailModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = DetailVideos(),
                query = mapOf(
                    QUERY_PARAM_LANGUAGE to language
                ),
                path = "$movieId/videos",
            )
        }
    }

    override suspend fun searchMovie(page: Int, searchModel: MovieSearchModel): MovieModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = SearchMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString(),
                    QUERY_PARAM_QUERY to searchModel.title,
                )
            )
        }
    }

    override suspend fun getCredits(movieId: String): CreditsModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = CreditsMovie(),
                query = emptyMap(),
                path = "$movieId/credits"
            )
        }
    }

}