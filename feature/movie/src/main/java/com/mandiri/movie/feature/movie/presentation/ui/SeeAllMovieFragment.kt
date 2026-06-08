/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SeeAllMovieFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.util.event.invokeDataStoreEvent
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.nav.navigator.MovieNavigator
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.MovieCategories
import com.mandiri.movie.core.utilities.extension.capitalize
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.feature.common.viewmodel.UserViewModel
import com.mandiri.movie.feature.movie.R
import com.mandiri.movie.feature.movie.adapter.SeeAllMovieAdapter
import com.mandiri.movie.feature.movie.databinding.FragmentSeeAllBinding
import com.mandiri.movie.feature.movie.presentation.viewmodel.HomeViewModel
import com.mandiri.movie.feature.movie.util.common.MovieLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeeAllMovieFragment : BaseFragment<FragmentSeeAllBinding>(FragmentSeeAllBinding::inflate) {

    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter

    private val viewModel: HomeViewModel by viewModels()

    private val userViewModel: UserViewModel by viewModels()

    private var userModel: UserModel = UserModel()

    private val args: SeeAllMovieFragmentArgs by navArgs()

    private var categories = Constant.EMPTY_STRING

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun setupView() {
        loadArguments()
        loadUser()
        setupAdapter()
        subscribeMovie()
        setupAdapterMovie()
    }

    private fun loadArguments() {
        categories = args.categories
        binding.tvSeeAllTitle.text = categories.replace("_", " ").capitalize()
    }

    private fun loadUser() {
        userViewModel.fetchUserFromDatastore().launch(this@SeeAllMovieFragment) { event ->
            invokeDataStoreEvent(event,
                isFetched = {
                    userModel = it
                },
                isError = {
                    showDialogGeneralError("Warning", "Failed to fetch your profile")
                }
            )
        }
    }

    private fun setupAdapter() {
        seeAllMovieAdapter = SeeAllMovieAdapter(
            dataStore = dataStorePreferences,
            jsonUtil = jsonUtil,
            onClickMovie = this::onMovieClicked,
            onClickFavourite = this::onMovieFavourite
        )
    }

    private fun subscribeMovie() = with(viewModel) {
        with(this@SeeAllMovieFragment) {
            when (categories) {
                MovieCategories.POPULAR.name -> {
                    popularMovies.launch(this) { seeAllMovieAdapter.submitData(it) }
                }

                MovieCategories.TOP_RATED.name -> {
                    topRatedMovies.launch(this) { seeAllMovieAdapter.submitData(it) }
                }

                MovieCategories.UP_COMING.name -> {
                    upComingMovies.launch(this) { seeAllMovieAdapter.submitData(it) }
                }
            }
        }
    }

    private fun setupAdapterMovie() =
        with(binding) {
            rvSeeAll.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
                adapter = seeAllMovieAdapter.withLoadStateHeaderAndFooter(
                    MovieLoadStateAdapter { seeAllMovieAdapter.retry() },
                    MovieLoadStateAdapter { seeAllMovieAdapter.retry() }
                )
            }

            seeAllMovieAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> {
                        viewAnimator.displayedChild = 0
                    }

                    is LoadState.NotLoading -> {
                        if (seeAllMovieAdapter.itemCount == 0) {
                            setViewBasedOnState(loadState, getString(R.string.empty_data_title))
                            viewAnimator.displayedChild = 2
                        } else viewAnimator.displayedChild = 1
                    }

                    is LoadState.Error -> {
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                        setViewBasedOnState(loadState, errorMessage.toString())
                    }
                }
            }
        }

    private fun setViewBasedOnState(
        loadState: CombinedLoadStates,
        message: String
    ) {
        with(binding) {
            viewCommonError.apply {
                errorMessage.text = message
                buttonRetry.apply {
                    isVisible = loadState.source.refresh is LoadState.Error
                    setOnClickListener { seeAllMovieAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }

    private fun onMovieFavourite(item: MovieDataModel) {
        // will be implement
    }

    private fun onMovieClicked(item: MovieDataModel) {
        movieNavigator.fromSeeAllMovieToDetailMovie(this, item)
    }

}