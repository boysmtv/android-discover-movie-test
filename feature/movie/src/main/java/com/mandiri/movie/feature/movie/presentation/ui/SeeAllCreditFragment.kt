/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SeeAllCreditFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.feature.movie.adapter.DetailCreditsAdapter
import com.mandiri.movie.feature.movie.databinding.FragmentSeeAllCreditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllCreditFragment : BaseFragment<FragmentSeeAllCreditBinding>(FragmentSeeAllCreditBinding::inflate) {

    private val args: SeeAllCreditFragmentArgs by navArgs()

    private var movieId = Constant.EMPTY_STRING

    override fun setupView() {
        loadArguments()
        setupVpCredits()
    }

    private fun loadArguments() {
        movieId = args.movieId
    }

    private fun setupVpCredits() = with(binding) {
        val creditsAdapter = DetailCreditsAdapter(activity)
        creditsAdapter.addFragment(
            fragment = SeeAllCreditsViewFragment(
                movieId = movieId,
                creditsCategory = CreditsCategory.CAST,
            ),
            title = "Cast"
        )
        creditsAdapter.addFragment(
            fragment = SeeAllCreditsViewFragment(
                movieId = movieId,
                creditsCategory = CreditsCategory.CREW,
            ),
            title = "Director & Crew"
        )

        tlSeeAllCredits.apply {
            tabGravity = TabLayout.GRAVITY_CENTER
        }
        vpSeeAllCredits.apply {
            adapter = creditsAdapter
            currentItem = Constant.ZERO
            isUserInputEnabled = Constant.FALSE
        }
        TabLayoutMediator(tlSeeAllCredits, vpSeeAllCredits) { tab, position ->
            tab.text = creditsAdapter.getTabTitle(position)
        }.attach()
    }

}
