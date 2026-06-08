/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SearchPagingSource.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mandiri.movie.core.common.util.network.pagingSucceeded
import com.mandiri.movie.core.data.repository.MovieRepository
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.model.MovieModel
import com.mandiri.movie.core.model.MovieSearchModel
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.extension.replaceIfNull

class SearchPagingSource(
    private val repository: MovieRepository,
    private val searchModel: MovieSearchModel
) : PagingSource<Int, MovieDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDataModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.minus(Constant.ONE)
                ?: state.closestPageToPosition(it)?.nextKey?.plus(Constant.ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDataModel> {
        val page = params.key.replaceIfNull(1)

        return repository.searchMovie(page, searchModel)
            .pagingSucceeded { response -> loadResult(response = response, page = page) }
    }

    private fun loadResult(response: MovieModel, page: Int) =
        LoadResult.Page(
            data = response.results,
            prevKey = if (page != Constant.ONE) page.minus(Constant.ONE) else null,
            nextKey = if (page != response.totalPages) page.plus(Constant.ONE) else null
        )
}