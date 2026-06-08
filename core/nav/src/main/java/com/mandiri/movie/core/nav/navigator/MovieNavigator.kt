/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieNavigator.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.nav.navigator

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.utilities.MovieCategories
import com.mandiri.movie.feature.movie.presentation.ui.DetailFragmentDirections
import com.mandiri.movie.feature.movie.presentation.ui.HomeFragmentDirections
import com.mandiri.movie.feature.movie.presentation.ui.SearchFragmentDirections
import com.mandiri.movie.feature.movie.presentation.ui.SeeAllMovieFragmentDirections

class MovieNavigator {

    private fun NavController.safeNavigate(action: NavController.() -> Unit) {
        try {
            this.action()
        } catch (e: IllegalArgumentException) {
            Log.w("MovieNavigator", "Navigation blocked: ${e.message}")
        }
    }

    fun fromHomeToDetailMovie(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).safeNavigate {
            this.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id.toString())
            )
        }
    }

    fun fromHomeToSeeAllMovie(fragment: Fragment, categories: MovieCategories) {
        findNavController(fragment).safeNavigate {
            this.navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllMovieFragment(categories.name)
            )
        }
    }

    fun fromSeeAllMovieToDetailMovie(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).safeNavigate {
            this.navigate(
                SeeAllMovieFragmentDirections.actionSeeAllMovieFragmentToDetailFragment(item.id.toString())
            )
        }
    }

    fun fromSearchToDetail(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).safeNavigate {
            this.navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.id.toString())
            )
        }
    }

    fun fromDetailToVideos(fragment: Fragment, movieKey: String) {
        findNavController(fragment).safeNavigate {
            this.navigate(
                DetailFragmentDirections.actionDetailFragmentToVideoFragment(movieKey)
            )
        }
    }

    fun fromDetailToSeeAllCredits(fragment: Fragment, movieId: String) {
        findNavController(fragment).safeNavigate {
            this.navigate(
                DetailFragmentDirections.actionDetailFragmentToSeeAllCreditsFragment(movieId)
            )
        }
    }

}
